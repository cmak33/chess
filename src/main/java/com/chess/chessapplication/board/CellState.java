package com.chess.chessapplication.board;

import com.chess.chessapplication.figures.abstract_figure.Figure;

public record CellState (Point point,
                        Figure oldFigure,
                        Figure newFigure){
}
