package com.chess.chessapplication.board;

import com.chess.chessapplication.figures.abstract_figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class Move{
    private final List<CellState> changedCells;

    public Move(){
        changedCells = new ArrayList<>();
    }

    public List<CellState> getChangedCells() {
        return changedCells;
    }

    public void addState(Point point,Figure oldFigure,Figure newFigure){
        changedCells.add(new CellState(point,oldFigure,newFigure));
    }

}
