package com.chess.chessapplication.board;

import com.chess.chessapplication.figures.abstract_figure.Figure;

public class Cell {
    private Figure figure;
    private final Point point;

    public Cell(Point point){
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

}
