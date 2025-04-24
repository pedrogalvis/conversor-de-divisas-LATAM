// filepath: [ExchangeRateService.java](http://_vscodecontentref_/0)
package com.example.currencyconverter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ExchangeRateService {
    private static final String API_KEY = System.getenv("EXCHANGE_RATE_API_KEY") != null
        ? System.getenv("EXCHANGE_RATE_API_KEY")
        : "1a9e37d571416dfbcd9754a4"; // Reemplaza con tu clave API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";
    private final HttpClient client;
    private final Gson gson;

    public ExchangeRateService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            if (API_KEY == null || API_KEY.isEmpty()) {
                throw new IllegalStateException("La clave API no está configurada. Por favor, configúrala como variable de entorno.");
            }

            URI uri = new URI(API_URL + fromCurrency + "/" + toCurrency);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Validar el código de estado HTTP
            if (response.statusCode() != 200) {
                System.err.println("Error: Respuesta HTTP no exitosa. Código de estado: " + response.statusCode());
                System.err.println("Cuerpo de la respuesta: " + response.body());
                return 0;
            }

            // Deserializar la respuesta JSON
            RateResponse rateResponse = gson.fromJson(response.body(), RateResponse.class);
            if (rateResponse == null || rateResponse.getConversionRate() <= 0) {
                System.err.println("Error: Respuesta JSON inválida o tasa de cambio no válida.");
                return 0;
            }

            return rateResponse.getConversionRate();
        } catch (java.net.URISyntaxException e) {
            System.err.println("Error: URI mal formada.");
        } catch (java.io.IOException e) {
            System.err.println("Error: Problema de entrada/salida al realizar la solicitud.");
        } catch (InterruptedException e) {
            System.err.println("Error: La solicitud fue interrumpida.");
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return 0;
    }
}