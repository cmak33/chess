module com.chess.chessapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.chess.chessapplication to javafx.fxml;
    exports com.chess.chessapplication;
}