package com.chess.chessapplication.models.figures.realisation;

import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;

public abstract class FigureWithMoveIndicator extends Figure {
    protected boolean wasMoved = false;

    public FigureWithMoveIndicator(Color color, Point point) {
        super(color, point);
    }

    public void setWasMoved(boolean value){
        wasMoved = value;
    }
}
