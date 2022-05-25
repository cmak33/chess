package com.chess.chessapplication.controllers;

import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;
import com.chess.chessapplication.models.figures.realisation.*;
import com.chess.chessapplication.models.propertiesReader.PropertiesReader;

import java.util.Properties;

public class ImagesNamesFinder {
    private Properties properties;

    public ImagesNamesFinder(String propertiesPath){
        properties = PropertiesReader.readProperties(propertiesPath).get();
    }

    public <T extends Figure> String findImageName(Color color, Class<T> type){
        return color.equals(Color.WHITE)?findWhiteFigure(type):findBlackFigure(type);
    }

    private <T extends Figure> String findBlackFigure(Class<T> type){
        String path = "";
        if(type.equals(Pawn.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\blackFigures\\blackPawn.png";
        } else if(type.equals(Bishop.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\blackFigures\\blackBishop.png";
        } else if(type.equals(King.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\blackFigures\\blackKing.png";
        } else if(type.equals(Knight.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\blackFigures\\blackKnight.png";
        } else if(type.equals(Queen.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\blackFigures\\blackQueen.png";
        } else if(type.equals(Rook.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\blackFigures\\blackRook.png";
        }
        return path;
    }

    private <T extends Figure> String findWhiteFigure(Class<T> type){
        String path = "";
        if(type.equals(Pawn.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\whiteFigures\\whitePawn.png";
        } else if(type.equals(Bishop.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\whiteFigures\\whiteBishop.png";
        } else if(type.equals(King.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\whiteFigures\\whiteKing.png";
        } else if(type.equals(Knight.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\whiteFigures\\whiteKnight.png";
        } else if(type.equals(Queen.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\whiteFigures\\whiteQueen.png";
        } else if(type.equals(Rook.class)){
            path = "D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\whiteFigures\\whiteRook.png";
        }
        return path;
    }




}
