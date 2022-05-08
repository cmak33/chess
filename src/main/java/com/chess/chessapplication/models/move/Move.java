package com.chess.chessapplication.models.move;

import com.chess.chessapplication.models.figures.abstract_figure.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Move {
    private final FigureMovement mainFigureMovement;
    private final Figure beatenFigure;
    private final List<FigureMovement> movements;

    public Move(FigureMovement mainFigureMovement,Figure beatenFigure){
        this.mainFigureMovement = mainFigureMovement;
        movements = new ArrayList<>(Collections.singleton(mainFigureMovement));
        this.beatenFigure = beatenFigure;
    }

    public Move(FigureMovement mainFigureMovement){
        this(mainFigureMovement,null);
    }

    public FigureMovement getMainFigureMovement() {
        return mainFigureMovement;
    }

    public Figure getBeatenFigure() {
        return beatenFigure;
    }

    public List<FigureMovement> getMovements() {
        return movements;
    }

    public void addMovement(FigureMovement movement){
        movements.add(movement);
    }

}
