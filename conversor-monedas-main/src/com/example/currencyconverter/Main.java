package com.example.currencyconverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("Bienvenido al conversor de Divisas");
            System.out.println("1) Dolar >>> Peso Argentino");
            System.out.println("2) Peso Argentino >>> Dolar");
            System.out.println("3) Dolar >>> Real Brasileño");
            System.out.println("4) Real Brasileño >>> Dolar");
            System.out.println("5) Dolar >>> Peso Colombiano");
            System.out.println("6) Peso Colombiano >>> Dolar");
            System.out.println("7) Salir");
            System.out.print("Elija una opcion valida: ");
            option = scanner.nextInt();

            if (option >= 1 && option <= 6) {
                System.out.print("Ingresa el valor que deseas convertir: ");
                double amount = scanner.nextDouble();
                CurrencyConverter converter = new CurrencyConverter();
                String result = converter.convertCurrency(option, amount);
                System.out.println(result);
//                System.out.println("\nPresiona Enter para volver al menú...");
//                scanner.nextLine();
            }
        } while (option != 7);

        scanner.close();
        System.out.println("Conversor de Monedas\n ");
    }
}
