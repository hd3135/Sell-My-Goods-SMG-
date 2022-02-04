package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.font.TextLabel;
import sun.plugin.javascript.navig4.LayerArray;

import javax.swing.*;
import javax.swing.text.Element;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.font.TextLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
/**
 * Main() class
 * */
public class Main extends Application {


    /**
     * Main() class
     * Main class extends the java.fx application
     * the start method is the main code for the javax application
     * here the user interface is defined by means of a stage and scene
     * stage class is the top level javafx container
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StoreFront.fxml"));
        primaryStage.setTitle("smg");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }
    public static void main(String[] args) {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();
        launch(args);

    }
}
