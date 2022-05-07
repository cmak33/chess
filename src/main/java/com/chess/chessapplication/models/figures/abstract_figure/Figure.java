package com.chess.chessapplication.models.figures.abstract_figure;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.move.Move;

import java.util.List;

public abstract class Figure {
    private final Color color;
    private Point point;

    public Figure(Color color){
        this.color = color;
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

    public List<Move> findPossibleMoves(Board board, Point endPoint){
        return findReachableMoves(board,endPoint)
                .stream()
                .filter(board::isMoveAppropriate)
                .toList();
    }

    protected abstract List<Move> findReachableMoves(Board board,Point endPoint);


}
