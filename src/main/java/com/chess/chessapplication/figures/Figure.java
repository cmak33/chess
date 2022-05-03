package com.chess.chessapplication.figures;

import com.chess.chessapplication.board.Board;
import com.chess.chessapplication.board.Point;

import java.util.List;

public abstract class Figure {
    private final Color color;
    private Point point;

    public Figure(Color color,Point point){
        this.color = color;
        this.point = point;
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

    public abstract boolean canMove(Point destination, Board board);

    public abstract List<Point> findPossibleMoves(Board board);
}
