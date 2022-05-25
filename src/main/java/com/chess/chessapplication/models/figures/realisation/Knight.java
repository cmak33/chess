package com.chess.chessapplication.models.figures.realisation;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;
import com.chess.chessapplication.models.move.FigureMovement;
import com.chess.chessapplication.models.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Knight extends Figure {


    public Knight(Color color, Point point) {
        super(color, point);
    }

    @Override
    public List<Move> findReachableMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Optional<Move> possibleMove;
        for(int x = -2;x<3;x++){
            if(x!=0){
                int y =3-Math.abs(x);
                possibleMove = findMove(x,y,board);
                possibleMove.ifPresent(moves::add);
                possibleMove = findMove(x,-y,board);
                possibleMove.ifPresent(moves::add);
            }
        }
        return moves;
    }
}
