package com.chess.chessapplication.models.board;

import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;
import com.chess.chessapplication.models.move.Move;
import com.chess.chessapplication.models.move.MoveRecorder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Board {
    private final MoveRecorder moveRecorder;
    protected TurnSwitcher turnSwitcher;
    protected List<Point> points;
    protected HashMap<Color,List<Figure>> activeFigures;

    public Board(List<Color> players){
        turnSwitcher = new TurnSwitcher(players);
        moveRecorder = new MoveRecorder(this,turnSwitcher);
    }


    public boolean isPointReachableForColor(Color color,Point point){
        return getActiveFigures().get(color).stream().anyMatch(figure->figure.findReachableMoves(this).stream().anyMatch(move->move.getMainFigureMovement().endPoint().equals(point)));
    }

    public List<Color> getColors(){
        return turnSwitcher.getPlayers();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void makeMove(Move move){
        moveRecorder.makeMove(move);
    }

    public Optional<Point> findPoint(int x, int y){
        return points.stream().filter(point -> point.x() == x && point.y() == y).findFirst();
    }

    public Optional<Figure> findFigureByPoint(Point point){
        return activeFigures.values().stream().flatMap(Collection::stream).filter(x->x.getPoint().equals(point)).findFirst();
    }

    public HashMap<Color, List<Figure>> getActiveFigures() {
        return activeFigures;
    }

    public void addFigure(Figure figure){
        findFiguresByColor(figure.getColor()).add(figure);
    }

    public void removeFigure(Figure figure){
        findFiguresByColor(figure.getColor()).remove(figure);
    }

    protected List<Figure> findFiguresByColor(Color color){
        return activeFigures.get(color);
    }

    public boolean isMoveAppropriate(Move move){
        moveRecorder.makeMove(move);
        boolean isAppropriate = isBoardStateAppropriate();
        moveRecorder.returnToPreviousMove();
        return isAppropriate;
    }

    protected abstract boolean isBoardStateAppropriate();

    public abstract boolean isGameOver();
}
