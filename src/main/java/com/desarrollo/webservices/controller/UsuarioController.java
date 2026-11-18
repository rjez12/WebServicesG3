package com.desarrollo.webservices.controller;

import com.desarrollo.webservices.model.Usuario;
import com.desarrollo.webservices.services.UsuarioServices;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;


public class UsuarioController {

    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn<Usuario, String> colId;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colEmail;
    @FXML
    private TableColumn<Usuario, String> colUsername;
    @FXML
    private TableColumn<Usuario, String> colTelefono;
    @FXML
    private TableColumn<Usuario, String> colCiudad;
    @FXML
    private TableColumn<Usuario, String> colEmpresa;
    @FXML
    private Button btnConsultar;
    @FXML
    private Label lblEstado;


    private final UsuarioServices service = new UsuarioServices();

    @FXML
    private void initialize() {

        tblUsuarios.setEditable(false);
        colId.setCellValueFactory(
                new PropertyValueFactory<>("ID")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("correo")
        );

        colUsername.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono")
        );

        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("ciudad")
        );

        colEmpresa.setCellValueFactory(
                new PropertyValueFactory<>("empresa")
        );

        lblEstado.setText("Presiona Consultar Usuarios");
    }

    @FXML
    private void consultarUsuarios() {
        Task<List<Usuario>> task = new Task<List<Usuario>>() {
            @Override
            protected List<Usuario> call() throws Exception {
                return service.ObtenerUsuarios();
            }
        };

        btnConsultar.disableProperty().bind(
                task.runningProperty()
        );

        lblEstado.setText("Consultando API Rest.....");
        task.setOnSucceeded(event -> {
            List<Usuario> usuarios = task.getValue();
            tblUsuarios.setItems(FXCollections.observableArrayList(usuarios));
            lblEstado.setText("Usuarios Encontrados");
        });

        // En caso de error
        task.setOnFailed(event -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }

}
