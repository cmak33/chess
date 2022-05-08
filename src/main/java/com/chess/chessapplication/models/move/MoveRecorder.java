package com.chess.chessapplication.models.move;

import com.chess.chessapplication.models.board.Board;

import java.util.ArrayList;
import java.util.List;

public class MoveRecorder {
    private final Board board;
    private final List<Move> moves;

    public MoveRecorder(Board board){
        this.board = board;
        moves = new ArrayList<>();
    }

    public void makeMove(Move move){
      makeMoveForward(move);
      moves.add(move);
      board.switchToNextPlayerTurn();
    }

    private void makeMoveForward(Move move){
        if(move.getBeatenFigure()!=null){
            board.removeFigure(move.getBeatenFigure());
        }
        move.getMovements().forEach(movement-> movement.figure().setPoint(movement.endPoint()));
    }

    public void returnToPreviousMove(){
        Move moveToCancel = popMove();
        makeMoveBack(moveToCancel);
        board.switchToPreviousPlayerTurn();
    }

    private Move popMove(){
        Move move = moves.get(moves.size()-1);
        moves.remove(move);
        return move;
    }

    private void makeMoveBack(Move move){
        if(move.getBeatenFigure()!=null){
            board.addFigure(move.getBeatenFigure());
        }
        move.getMovements().forEach(movement-> movement.figure().setPoint(movement.startPoint()));
    }

    public boolean isPossibleReturnToPreviousMove(){
        return moves.size() > 0;
    }



}
