package com.chess.chessapplication.models.figures.abstract_figure;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.move.FigureMovement;
import com.chess.chessapplication.models.move.Move;

import java.util.List;
import java.util.Optional;

public abstract class Figure {
    private final Color color;
    private Point point;

    public Figure(Color color,Point point){
        this.color = color;
        setPoint(point);
    }

    public Color getColor() {
        return color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Optional<Move> findMoveToPoint(Board board,Point destination){
        return findPossibleMoves(board).stream().filter(x->x.getMainFigureMovement().endPoint().equals(destination)).findFirst();
    }

    public List<Move> findPossibleMoves(Board board){
        return findReachableMoves(board)
                .stream()
                .filter(board::isMoveAppropriate)
                .toList();
    }

    public abstract List<Move> findReachableMoves(Board board);

    protected Optional<Move> findMove(int offsetX,int offsetY,Board board){
        Optional<Point> point = board.findPoint(getPoint().x()+offsetX, getPoint().y()+offsetY);
        Optional<Move> move = Optional.empty();
        if(point.isPresent()){
            Optional<Figure> beatenFigure = board.findFigureByPoint(point.get());
            if(beatenFigure.isEmpty() || beatenFigure.get().getColor()!=getColor()){
                FigureMovement figureMovement = new FigureMovement(this,getPoint(),point.get());
                if(beatenFigure.isPresent()){
                    move = Optional.of(new Move(figureMovement,beatenFigure.get()));
                } else{
                    move = Optional.of(new Move(figureMovement));
                }

            }
        }
        return move;
    }
}
