package ec.game.pokemon.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.game.pokemon.model.PokemonListResponse;

public class PokemonService {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final ObjectMapper objectMapper;

    

    public PokemonService() {
        this.objectMapper = new ObjectMapper();
    }

    public CompletableFuture<PokemonListResponse> getPokemonList(int limit, int offset) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url = BASE_URL+"pokemon?limit="+ limit +"&offset="+ offset;
                String jsonResponse = makeHttpRequest(url);
                return objectMapper.readValue(jsonResponse, PokemonListResponse.class);
            } catch (Exception e) {
                throw new RuntimeException("No se pudo consumir el webservices  "+e.getMessage(), e);
            }
        } );
    }

    private String makeHttpRequest(String urlRequest) throws IOException {
        URL url = new URL(urlRequest);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        int responseCode = connection.getResponseCode();
        System.out.println("Respuesta  "+responseCode);
        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new IOException("Revisa este código de error "+ responseCode);
        }
    }
}
