package com.chess.chessapplication.figures;

import com.chess.chessapplication.board.Board;
import com.chess.chessapplication.board.Point;

import java.util.List;

public abstract class Figure {
    private final Color color;

    public Figure(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean canMove(Point start,Point destination, Board board);

    public abstract List<Point> findPossibleMoves(Point start,Board board);
}
