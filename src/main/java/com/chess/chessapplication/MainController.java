package com.chess.chessapplication;

import com.chess.chessapplication.controllers.Cell;
import com.chess.chessapplication.controllers.ImagesNamesFinder;
import com.chess.chessapplication.models.board.ClassicBoard;
import com.chess.chessapplication.models.common.Color;
import com.chess.chessapplication.models.common.Point;
import com.chess.chessapplication.models.figures.abstract_figure.Figure;
import com.chess.chessapplication.models.move.Move;
import com.chess.chessapplication.models.propertiesReader.PropertiesReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MainController {
    private ClassicBoard classicBoard;
    private Properties sizeProperties;
    private ImagesNamesFinder imagesNamesFinder;
    private List<Cell> cells;
    private Optional<Cell> chosenCell;
    private boolean isGameOver = false;


    @FXML
    private Pane mainPane;


    @FXML
    private Pane pausePane;

    @FXML
    void restart(ActionEvent event) {
        pausePane.setVisible(false);
        classicBoard.initializeDefaultFigures();
        setCellImages();
    }

    public MainController(){
        chosenCell = Optional.empty();
        classicBoard = new ClassicBoard(Color.WHITE,Color.BLACK);
        imagesNamesFinder = new ImagesNamesFinder("D:\\JavaProjects\\chessApp\\src\\main\\resources\\properties\\imagesPaths.properties");
        sizeProperties = PropertiesReader.readProperties("D:\\JavaProjects\\chessApp\\src\\main\\resources\\properties\\size.properties").get();
    }

    @FXML
    public void initialize(){
        cells = createCells();
        placeCells();
        setCellImages();
        pausePane.setVisible(false);
    }

    private void startGame(){
        classicBoard.initializeDefaultFigures();
        setCellImages();
    }

    private List<Cell> createCells(){
        List<Cell> cells = new ArrayList<>();
        Point cellPoint;
        ImageView imageView;
        for(int x = 0;x<ClassicBoard.getSize();x++){
            for(int y = 0;y<ClassicBoard.getSize();y++){
                imageView = new ImageView();
                imageView.setFitHeight(Integer.parseInt(sizeProperties.getProperty("figureSize")));
                imageView.setFitWidth(Double.parseDouble(sizeProperties.getProperty("figureSize")));
                mainPane.getChildren().add(imageView);
                cellPoint = classicBoard.findPoint(x,y).get();
                cells.add(new Cell(cellPoint,imageView,this::onCellClick));
            }
        }
        return cells;
    }

    private void setImageViewPositionByPoint(Point point,ImageView imageView){
        int x = Integer.parseInt(sizeProperties.getProperty("startX"))+point.x()*Integer.parseInt(sizeProperties.getProperty("figureSize"));
        int y = Integer.parseInt(sizeProperties.getProperty("startY"))-point.y()*Integer.parseInt(sizeProperties.getProperty("figureSize"));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    private void placeCells(){
        cells.forEach(cell->setImageViewPositionByPoint(cell.getPoint(),cell.getImageView()));
    }

    private void setCellImages(){
        cells.forEach(this::updateCellImage);
    }

    private void onCellClick(Cell cell){
        if(chosenCell.isPresent()){
            Figure figure = classicBoard.findFigureByPoint(chosenCell.get().getPoint()).get();
            if(classicBoard.findFigureByPoint(cell.getPoint()).isPresent() && classicBoard.findFigureByPoint(cell.getPoint()).get().getColor().equals(figure.getColor())){
                chosenCell = Optional.of(cell);
            } else {
                Optional<Move> move = figure.findMoveToPoint(classicBoard, cell.getPoint());
                if (move.isPresent()) {
                    classicBoard.makeMove(move.get());
                    move.get().getMovements().forEach(movement ->
                    {
                        updateCellImage(findCellByPoint(movement.startPoint()));
                        updateCellImage(findCellByPoint(movement.endPoint()));
                    });
                }
                chosenCell = Optional.empty();
                if(classicBoard.isGameOver()){
                    onGameOver();
                }
            }
        } else if(classicBoard.findFigureByPoint(cell.getPoint()).isPresent() && classicBoard.findFigureByPoint(cell.getPoint()).get().getColor().equals(classicBoard.getCurrentTurn())){
            chosenCell = Optional.of(cell);
        } else{
            chosenCell = Optional.empty();
        }
    }

    private void onGameOver(){
        pausePane.setVisible(true);
        pausePane.toFront();
    }

    private Cell findCellByPoint(Point point){
        return cells.stream().filter(cell->cell.getPoint().equals(point)).findFirst().get();
    }

    private void updateCellImage(Cell cell){
        Optional<Figure> figure = classicBoard.findFigureByPoint(cell.getPoint());
        Image image = null;
        if(figure.isPresent()){
            image = new Image(imagesNamesFinder.findImageName(figure.get().getColor(),figure.get().getClass()));
        }
        cell.setImage(image);
    }
    
    



}
