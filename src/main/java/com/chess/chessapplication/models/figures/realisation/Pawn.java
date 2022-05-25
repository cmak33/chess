package com.chess.chessapplication.models.figures.realisation;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends FigureWithMoveIndicator {
    private final int offsetY;


    public Pawn(Color color, Point point) {
        super(color, point);
        offsetY = color.equals(Color.WHITE)?1:-1;
    }

    @Override
    public List<Move> findReachableMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Optional<Move> possibleMove;
        for(int x = -1;x<2;x++){
            possibleMove = findMove(x,offsetY,board);
            if(possibleMove.isPresent() && isMoveCorrect(possibleMove.get(),x)){
                if(board.findPoint(possibleMove.get().getMainFigureMovement().endPoint().x(),possibleMove.get().getMainFigureMovement().endPoint().y()+offsetY).isEmpty()) {
                    possibleMove.get().addFigure(new Queen(getColor(), possibleMove.get().getMainFigureMovement().endPoint()));
                    possibleMove.get().addBeatenFigure(this);
                }
                moves.add(possibleMove.get());
            }
        }
        if(!wasMoved){
            possibleMove = findMove(0,offsetY*2,board);
            if(possibleMove.isPresent() && isMoveCorrect(possibleMove.get(),0)){
                moves.add(possibleMove.get());
            }
        }
        return moves;
    }

    private boolean isMoveCorrect(Move move,int offsetX){
        return (offsetX==0) == (move.getBeatenFigures().size()==0);
    }
}
