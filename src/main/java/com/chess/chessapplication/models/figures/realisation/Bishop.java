package com.chess.chessapplication.models.figures.realisation;

import com.chess.chessapplication.models.board.Board;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;
import com.chess.chessapplication.models.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends Figure {


    public Bishop(Color color, Point point) {
        super(color, point);
    }

    @Override
    public List<Move> findReachableMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Optional<Move> possibleMove;
        int offsetX;
        int offsetY;
        for(int x = -1;x<2;x++){
            for(int y = -1;y<2;y++){
                if(x!=0 && y!=0){
                    offsetX = x;
                    offsetY = y;
                    possibleMove = findMove(offsetX,offsetY,board);
                    while(possibleMove.isPresent()){
                        moves.add(possibleMove.get());
                        offsetX+=x;
                        offsetY+=y;
                        if(possibleMove.get().getBeatenFigures().size()!=0){
                            break;
                        }
                        possibleMove = findMove(offsetX,offsetY,board);
                    }
                }
            }
        }
        return moves;
    }
}
