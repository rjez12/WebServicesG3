package com.desarrollo.webservices.controller;

import com.desarrollo.webservices.model.Cliente;
import com.desarrollo.webservices.services.ClienteServices;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ClienteController {

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colPais;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, Double> colIngreso;
    @FXML private TableColumn<Cliente, Integer> colCredito;
    @FXML private TableColumn<Cliente, String> colDeuda;
    @FXML private TableColumn<Cliente, Double> colMontoDeuda;
    @FXML private TableColumn<Cliente, String> colEstado;
    @FXML private Button btnConsultar;
    @FXML private Label lblEstado;

    private final ClienteServices service = new ClienteServices();

    @FXML
    private void initialize() {
        tblClientes.setEditable(false);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colIngreso.setCellValueFactory(new PropertyValueFactory<>("ingresoAnual"));
        colCredito.setCellValueFactory(new PropertyValueFactory<>("puntajeCredito"));
        colDeuda.setCellValueFactory(new PropertyValueFactory<>("deudaTexto"));
        colMontoDeuda.setCellValueFactory(new PropertyValueFactory<>("montoDeuda"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        lblEstado.setText("Presiona Consultar clientes");
    }

    @FXML
    private void consultarClientes() {
        Task<List<Cliente>> task = new Task<>() {
            @Override
            protected List<Cliente> call() throws Exception {
                return service.obtenerClientes();
            }
        };

        btnConsultar.disableProperty().bind(task.runningProperty());
        lblEstado.setText("Consultando Supabase.....");

        task.setOnSucceeded(e -> {
            List<Cliente> clientes = task.getValue();
            tblClientes.setItems(FXCollections.observableArrayList(clientes));
            lblEstado.setText(clientes.size() + " clientes encontrados");
        });

        task.setOnFailed(e -> lblEstado.setText(task.getException().getMessage()));

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}