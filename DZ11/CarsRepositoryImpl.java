package repository;

import java.util.ArrayList;
import java.util.List;

import model.Car;

public class CarsRepositoryImpl implements CarsRepository {
    private List<Car> cars = new ArrayList<>();

    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }
}