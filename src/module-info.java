module MC02 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;

    opens tictactoe;
    opens tictactoe.model;
    opens tictactoe.controller;
    opens tictactoe.view;
}