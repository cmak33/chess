package com.chess.chessapplication.models.move;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.board.TurnSwitcher;
import com.chess.chessapplication.models.figures.realisation.FigureWithMoveIndicator;

import java.util.ArrayList;
import java.util.List;

public class MoveRecorder {
    private final Board board;
    private final TurnSwitcher turnSwitcher;
    private final List<Move> moves;

    public MoveRecorder(Board board, TurnSwitcher turnSwitcher){
        this.board = board;
        this.turnSwitcher = turnSwitcher;
        moves = new ArrayList<>();
    }

    public void makeMove(Move move){
      makeMoveForward(move);
      moves.add(move);
      turnSwitcher.switchToNextPlayerTurn();
      if((move.getMainFigureMovement().figure()) instanceof FigureWithMoveIndicator){
          ((FigureWithMoveIndicator)move.getMainFigureMovement().figure()).setWasMoved(true);
      }
    }

    private void makeMoveForward(Move move){
        move.getBeatenFigures().forEach(figure->board.removeFigure(figure));
        move.getAddedFigures().forEach(figure -> board.addFigure(figure));
        move.getMovements().forEach(movement-> movement.figure().setPoint(movement.endPoint()));
    }

    public void returnToPreviousMove(){
        Move moveToCancel = popMove();
        makeMoveBack(moveToCancel);
        turnSwitcher.switchToPreviousPlayerTurn();
    }

    private Move popMove(){
        Move move = moves.get(moves.size()-1);
        moves.remove(move);
        if((move.getMainFigureMovement().figure()) instanceof FigureWithMoveIndicator){
            ((FigureWithMoveIndicator)move.getMainFigureMovement().figure()).setWasMoved(true);
        }
        return move;
    }

    private void makeMoveBack(Move move){
        move.getBeatenFigures().forEach(figure->board.addFigure(figure));
        move.getAddedFigures().forEach(figure -> board.removeFigure(figure));
        move.getMovements().forEach(movement-> movement.figure().setPoint(movement.startPoint()));
    }

    public boolean isPossibleReturnToPreviousMove(){
        return moves.size() > 0;
    }



}
