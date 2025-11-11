package com.example.catatanolahragaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Diperbaiki: Gunakan resource loader, bukan path file absolut
        // FXML sekarang di src/main/resources/view/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CatatanOlahragaView.fxml"));
        Scene scene = new Scene(loader.load());
        
        stage.setTitle("Catatan Latihan Olahraga Harian");
        stage.setScene(scene);
        stage.show();
    }
}