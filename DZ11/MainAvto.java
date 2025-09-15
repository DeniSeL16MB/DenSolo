package test;
import java.io.*;
import java.util.*;
import java.util.stream.*;

import model.Car;
import repository.CarsRepository;
import repository.CarsRepositoryImpl;

public class MainAvto {
    public static void main(String[] args) {
        CarsRepository repo = new CarsRepositoryImpl();

        String[][] rawData = {
            {"a123me", "Mercedes", "White", "0", "8300000"},
            {"b873of", "Volga", "Black", "0", "673000"},
            {"w487mn", "Lexus", "Grey", "76000", "900000"},
            {"p987hj", "Volga", "Red", "610", "704340"},
            {"c987ss", "Toyota", "White", "254000", "761000"},
            {"o983op", "Toyota", "Black", "698000", "740000"},
            {"p146op", "BMW", "White", "271000", "850000"},
            {"u893ii", "Toyota", "Sirenevenkiy", "210900", "440000"},
            {"l097df", "Toyota", "Black", "108000", "780000"},
            {"y876wd", "Toyota", "Black", "160000", "1000000"}
        };

        for (String[] data : rawData) {
            String number = data[0];
            String model = data[1];
            String color = data[2];
            int mileage = Integer.parseInt(data[3]);
            int cost = Integer.parseInt(data[4]);
            repo.addCar(new Car(number, model, color, mileage, cost));
        }

        List<Car> cars = repo.getAllCars();

        String colorToFind = "Black";
        int mileageToFind = 0;
        int n = 680_000;
        int m = 750_000;
        String modelToFind = "Volga";


        System.out.println("Автомобили в базе:");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "Number", "Model", "Color", "Mileage", "Cost");
        for (Car c : cars) {
            System.out.println(c);
        }

        List<String> filteredNumbers = cars.stream()
            .filter(c -> c.getColor().equalsIgnoreCase(colorToFind) || c.getMileage() == mileageToFind)
            .map(Car::getNumber)
            .collect(Collectors.toList());
        System.out.println("\nНомера автомобилей по цвету или пробегу:");
        for (String num : filteredNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        long uniqueModelsCount = cars.stream()
            .filter(c -> c.getCost() >= n && c.getCost() <= m)
            .map(Car::getModel)
            .distinct()
            .count();
        System.out.println("Уникальные модели в диапазоне " + n + " - " + m + ": " + uniqueModelsCount + " шт.");

        Optional<Car> minCostCarOpt = cars.stream()
            .min(Comparator.comparingInt(Car::getCost));
        String minCostColor = minCostCarOpt.map(Car::getColor).orElse("Нет данных");
        System.out.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);

        OptionalDouble avgCost = cars.stream()
            .filter(c -> c.getModel().equalsIgnoreCase(modelToFind))
            .mapToInt(Car::getCost)
            .average();
        if (avgCost.isPresent()) {
            System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind, avgCost.getAsDouble());
        } else {
            System.out.printf("Модель %s не найдена.%n", modelToFind);
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("cars.txt"))) {
            pw.println("Автомобили в базе:");
            pw.printf("%-10s %-10s %-10s %-10s %-10s%n", "Number", "Model", "Color", "Mileage", "Cost");
            for (Car c : cars) {
                pw.println(c);
            }
            pw.println("\nНомера автомобилей по цвету или пробегу:");
            for (String num : filteredNumbers) {
                pw.print(num + " ");
            }
            pw.println();
            pw.println("Уникальные модели в диапазоне " + n + " - " + m + ": " + uniqueModelsCount + " шт.");
            pw.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);
            pw.printf("Средняя стоимость модели %s: %.2f%n", modelToFind, avgCost.orElse(0.0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}