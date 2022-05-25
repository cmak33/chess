package com.chess.chessapplication.models.move;

import com.chess.chessapplication.models.figures.abstract_figure.Figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Move {
    private final FigureMovement mainFigureMovement;
    private final List<Figure> beatenFigures;

    public List<Figure> getAddedFigures() {
        return addedFigures;
    }

    private final List<Figure> addedFigures;
    private final List<FigureMovement> movements;

    public Move(FigureMovement mainFigureMovement,List<Figure> beatenFigures){
        this.mainFigureMovement = mainFigureMovement;
        movements = new ArrayList<>(Collections.singleton(mainFigureMovement));
        this.beatenFigures = new ArrayList<>(beatenFigures);
        addedFigures = new ArrayList<>();
    }

    public Move(FigureMovement mainFigureMovement){
        this(mainFigureMovement,new ArrayList<>());
    }

    public Move(FigureMovement mainFigureMovement,Figure beatenFigure){
        this(mainFigureMovement,new ArrayList<>(List.of(beatenFigure)));
    }

    public FigureMovement getMainFigureMovement() {
        return mainFigureMovement;
    }

    public List<Figure> getBeatenFigures() {
        return beatenFigures;
    }

    public void addFigure(Figure figure){
        addedFigures.add(figure);
    }

    public void addBeatenFigure(Figure figure){
        beatenFigures.add(figure);
    }

    public List<FigureMovement> getMovements() {
        return movements;
    }

    public void addMovement(FigureMovement movement){
        movements.add(movement);
    }

}
