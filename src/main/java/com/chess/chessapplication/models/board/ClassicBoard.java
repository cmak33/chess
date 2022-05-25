package com.chess.chessapplication.models.board;

import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;
import com.chess.chessapplication.models.figures.realisation.*;

import java.util.*;

public class ClassicBoard extends Board{
    private final static int SIZE = 8;

    public ClassicBoard(Color firstPlayer, Color secondPlayer){
        super(new ArrayList<>(List.of(firstPlayer,secondPlayer)));
        points = createDefaultPoints();
        initializeDefaultFigures();
    }

    public static int getSize(){
        return SIZE;
    }

    public Color getCurrentTurn(){
        return turnSwitcher.getCurrentColor();
    }

    private List<Point> createDefaultPoints(){
        List<Point> points = new ArrayList<>();
        for(int x = 0;x<SIZE;x++){
            for(int y = 0 ;y<SIZE;y++){
                points.add(new Point(x,y));
            }
        }
        return points;
    }

    public void initializeDefaultFigures(){
        activeFigures = createDefaultFigures();
    }

    private HashMap<Color, List<Figure>> createDefaultFigures(){
        HashMap<Color,List<Figure>> figures = new HashMap<>();
        List<Figure> firstColorFigures = spawnPawnRow(turnSwitcher.players.get(0),1);
        List<Figure> secondColorFigures = spawnPawnRow(turnSwitcher.players.get(1),SIZE-2);
        figures.put(turnSwitcher.players.get(0),firstColorFigures);
        figures.put(turnSwitcher.players.get(1),secondColorFigures);
        firstColorFigures.add(new Rook(turnSwitcher.players.get(0),findPoint(0,0).get()));
        firstColorFigures.add(new Rook(turnSwitcher.players.get(0),findPoint(SIZE-1,0).get()));
        firstColorFigures.add(new Knight(turnSwitcher.players.get(0),findPoint(1,0).get()));
        firstColorFigures.add(new Knight(turnSwitcher.players.get(0),findPoint(SIZE-2,0).get()));
        firstColorFigures.add(new Bishop(turnSwitcher.players.get(0),findPoint(2,0).get()));
        firstColorFigures.add(new Bishop(turnSwitcher.players.get(0),findPoint(SIZE-3,0).get()));
        firstColorFigures.add(new Queen(turnSwitcher.players.get(0),findPoint(3,0).get()));
        firstColorFigures.add(new King(turnSwitcher.players.get(0),findPoint(4,0).get()));
        secondColorFigures.add(new Rook(turnSwitcher.players.get(1),findPoint(0,SIZE-1).get()));
        secondColorFigures.add(new Rook(turnSwitcher.players.get(1),findPoint(SIZE-1,SIZE-1).get()));
        secondColorFigures.add(new Knight(turnSwitcher.players.get(1),findPoint(1,SIZE-1).get()));
        secondColorFigures.add(new Knight(turnSwitcher.players.get(1),findPoint(SIZE-2,SIZE-1).get()));
        secondColorFigures.add(new Bishop(turnSwitcher.players.get(1),findPoint(2,SIZE-1).get()));
        secondColorFigures.add(new Bishop(turnSwitcher.players.get(1),findPoint(SIZE-3,SIZE-1).get()));
        secondColorFigures.add(new Queen(turnSwitcher.players.get(1),findPoint(3,SIZE-1).get()));
        secondColorFigures.add(new King(turnSwitcher.players.get(1),findPoint(4,SIZE-1).get()));
        return figures;
    }



    private List<Figure> spawnPawnRow(Color color,int row){
        List<Figure> pawns = new ArrayList<>();
        for(int x=0;x<SIZE;x++){
            Point point = findPoint(x,row).get();
            Pawn pawn = new Pawn(color,point);
            pawns.add(pawn);
        }
        return pawns;
    }

    @Override
    protected boolean isBoardStateAppropriate() {
        return findFiguresByColor(turnSwitcher.getCurrentColor()).stream()
                .noneMatch(figure->figure.findReachableMoves(this).stream()
                .anyMatch(move->move.getBeatenFigures().size()>0 && move.getBeatenFigures().stream().anyMatch(f->f.getClass().equals(King.class))));
    }

    @Override
    public boolean isGameOver() {
        return activeFigures.get(turnSwitcher.getCurrentColor()).stream().noneMatch(figure->!figure.findPossibleMoves(this).isEmpty());
    }
}
