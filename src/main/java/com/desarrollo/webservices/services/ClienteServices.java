package com.desarrollo.webservices.services;

import com.desarrollo.webservices.model.Cliente;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClienteServices {


    private static final String SUPABASE_URL = System.getenv("SUPABASE_URL");
    private static final String SUPABASE_KEY = System.getenv("SUPABASE_KEY");
    private static final String TABLA = "customers";

    private final HttpClient cliente = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Cliente> obtenerClientes() throws IOException, InterruptedException {
        if (SUPABASE_URL == null || SUPABASE_KEY == null) {
            throw new IOException("Faltan las variables de entorno SUPABASE_URL o SUPABASE_KEY");
        }

        String url = SUPABASE_URL + "/rest/v1/" + TABLA + "?select=*&order=id.asc";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .header("apikey", SUPABASE_KEY)
                .header("Authorization", "Bearer " + SUPABASE_KEY)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("Error " + response.statusCode() + ": " + response.body());
        }

        JsonNode datos = mapper.readTree(response.body());
        if (!datos.isArray()) {
            throw new IOException("La respuesta de Supabase no es una lista");
        }

        List<Cliente> clientes = new ArrayList<>();
        for (JsonNode nodo : datos) {
            clientes.add(new Cliente(
                    nodo.path("id").asInt(),
                    nodo.path("first_name").asText(),
                    nodo.path("last_name").asText(),
                    nodo.path("email").asText(),
                    nodo.path("phone").asText(),
                    nodo.path("date_of_birth").asText(),
                    nodo.path("country").asText(),
                    nodo.path("city").asText(),
                    nodo.path("address").asText(),
                    nodo.path("annual_income").asDouble(),
                    nodo.path("credit_score").asInt(),
                    nodo.path("has_unpaid_debt").asBoolean(),
                    nodo.path("unpaid_debt_amount").asDouble(),
                    nodo.path("status").asText()
            ));
        }
        return clientes;
    }
}