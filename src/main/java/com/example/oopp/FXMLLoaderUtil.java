package com.example.oopp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLLoaderUtil {

    public static void loadFXML(String fxmlFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlertFXML("Error loading FXML: " + fxmlFileName);
        }
    }

    private static void showAlertFXML(String message) {
        System.err.println("Error: " + message);
    }
}

