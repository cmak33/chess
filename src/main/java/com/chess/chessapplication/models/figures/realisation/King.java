package com.chess.chessapplication.models.figures.realisation;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.move.FigureMovement;
import com.chess.chessapplication.models.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class King extends FigureWithMoveIndicator {


    public King(Color color, Point point) {
        super(color, point);
    }

    @Override
    public List<Move> findReachableMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Optional<Move> possibleMove;
        for(int x = -1;x<2;x++){
            for(int y = -1;y<2;y++){
                possibleMove = findMove(x,y,board);
                possibleMove.ifPresent(moves::add);
            }
        }
        return moves;
    }

    @Override
    public Optional<Move> findMoveToPoint(Board board,Point destination){
        List<Move> moves = findCastlingMoves(board);
        moves.addAll(findPossibleMoves(board));
        return moves.stream().filter(x->x.getMainFigureMovement().endPoint().equals(destination)).findFirst();
    }

    public List<Move> findCastlingMoves(Board board){
        List<Move> moves = new ArrayList<>();
        findRightCastling(board).ifPresent(x->moves.add(x));
        findLeftCastling(board).ifPresent(x->moves.add(x));
        return moves;
    }

    private Optional<Move> findLeftCastling(Board board){
        Optional<Move> move = Optional.empty();
        if(!wasMoved) {
            Optional<Rook> rook = board.getActiveFigures().get(getColor()).stream().filter(x -> x.getClass().equals(Rook.class) && !((Rook) x).wasMoved && x.getPoint().x() == 0).map(x -> (Rook) x).findFirst();
            if(rook.isPresent() && isHorizontalDirectionClear(board,-1)){
                FigureMovement mainMovement = new FigureMovement(this,getPoint(),board.findPoint(getPoint().x()-2, getPoint().y() ).get());
                move = Optional.of(new Move(mainMovement));
                move.get().addMovement(new FigureMovement(rook.get(),rook.get().getPoint(),board.findPoint(getPoint().x()-1,getPoint().y()).get()));
            }
        }
        return move;
    }

    private Optional<Move> findRightCastling(Board board){
        Optional<Move> move = Optional.empty();
        if(!wasMoved) {
            Optional<Rook> rook = board.getActiveFigures().get(getColor()).stream().filter(x -> x.getClass().equals(Rook.class) && !((Rook) x).wasMoved && x.getPoint().x() != 0).map(x -> (Rook) x).findFirst();
            if(rook.isPresent() && isHorizontalDirectionClear(board,1)){
                FigureMovement mainMovement = new FigureMovement(this,getPoint(),board.findPoint(getPoint().x()+2, getPoint().y() ).get());
                move = Optional.of(new Move(mainMovement));
                move.get().addMovement(new FigureMovement(rook.get(),rook.get().getPoint(),board.findPoint(getPoint().x()+1,getPoint().y()).get()));
            }
        }
        return move;
    }

    private boolean isHorizontalDirectionClear(Board board,int direction){
        boolean isClear = true;
        Optional<Point> point = board.findPoint(getPoint().x()+direction, getPoint().y());
        int count = 1;
        while(isClear && point.isPresent() && board.findPoint(point.get().x()+direction, getPoint().y()).isPresent()){
            int shift = count * direction;
            isClear =board.findFigureByPoint(point.get()).isEmpty() && !board.getColors().stream().filter(x->x!=getColor())
                    .anyMatch(color->board.getActiveFigures().get(color).stream().anyMatch(figure->figure.findReachableMoves(board).stream().anyMatch(move->move.getMainFigureMovement().endPoint().y()==getPoint().y() && move.getMainFigureMovement().endPoint().x()==(getPoint().x()+shift))));
            point = board.findPoint(point.get().x()+direction, getPoint().y());
            count++;
        }
        return isClear;
    }


    public boolean isInCheck(Board board){
        return board.getActiveFigures()
                .keySet()
                .stream()
                .filter(x->x!=getColor()).anyMatch(x->board.getActiveFigures().get(x).stream().anyMatch(figure->figure.findReachableMoves(board).stream().anyMatch(y->y.getBeatenFigures().equals(this))));
    }
}
