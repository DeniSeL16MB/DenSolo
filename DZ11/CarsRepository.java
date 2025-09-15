package repository;

import java.util.List;

import model.Car;

public interface CarsRepository {
    List<Car> getAllCars();
    void addCar(Car car);
}