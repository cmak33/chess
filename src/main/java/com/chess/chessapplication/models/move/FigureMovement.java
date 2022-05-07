package com.chess.chessapplication.models.move;

import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;

public record FigureMovement(Figure figure, Point startPoint, Point endPoint){ }
