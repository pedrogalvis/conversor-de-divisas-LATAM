package com.example.currencyconverter;

public class CurrencyConverter {
    private final ExchangeRateService exchangeRateService;

    public CurrencyConverter() {
        this.exchangeRateService = new ExchangeRateService();
    }

    public String convertCurrency(int option, double amount) {
        String fromCurrency = "";
        String toCurrency = "";
        String toCurrencySymbol = "";

        switch (option) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "ARS";
                toCurrencySymbol = "pesos argentinos";
                break;
            case 2:
                fromCurrency = "ARS";
                toCurrency = "USD";
                toCurrencySymbol = "dólares";
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "BRL";
                toCurrencySymbol = "reales brasileños";
                break;
            case 4:
                fromCurrency = "BRL";
                toCurrency = "USD";
                toCurrencySymbol = "dólares";
                break;
            case 5:
                fromCurrency = "USD";
                toCurrency = "COP";
                toCurrencySymbol = "pesos colombianos";
                break;
            case 6:
                fromCurrency = "COP";
                toCurrency = "USD";
                toCurrencySymbol = "dólares";
                break;
            default:
                return "Opción inválida. Por favor, selecciona una opción válida.";
        }

        double rate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        if (rate == 0) {
            return "No se pudo obtener la tasa de cambio. Por favor, intenta nuevamente más tarde.";
        }

        double convertedAmount = amount * rate;
        return String.format("El valor de %.2f %s corresponde a %.2f %s", amount, fromCurrency, convertedAmount, toCurrencySymbol);
    }
}