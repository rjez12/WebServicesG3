
package com.desarrollo.webservices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                UsuarioApplication.class.getResource("clientes-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Consulta de usuarios - SUPABASE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
