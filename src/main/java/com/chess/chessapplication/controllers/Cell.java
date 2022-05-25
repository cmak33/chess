package com.chess.chessapplication.controllers;

import com.chess.chessapplication.models.common.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Cell {
    private final Point point;

    public ImageView getImageView() {
        return imageView;
    }

    private final ImageView imageView;


    public Cell(Point point, ImageView imageView,OnCellClick onClick){
        this.point = point;
        this.imageView = imageView;
        setOnClick(onClick);
    }

    private void setOnClick(OnCellClick onClick){
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent event)->onClick.onClick(this));
    }

    public Point getPoint() {
        return point;
    }

    public void setImage(Image image){
        image = image==null?new Image("D:\\JavaProjects\\chessApp\\src\\main\\resources\\images\\img.png"):image;
        imageView.setImage(image);
    }

}
