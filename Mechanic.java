package lab1;

import java.util.ArrayList;

public class Mechanic<T extends Car> {
    private int carCapacity;
    private ArrayList<T> cars;

    public Mechanic(int carCapacity) {
        this.carCapacity = carCapacity;
        this.cars = new ArrayList<>();
    }

    public void addCar(T car) {
        if (cars.size() < carCapacity) {
            cars.add(car);
        }
    }

    public <T> T removeCar(T car) {
        cars.remove(car);
        return car;
    }

    public static void main(String[] args) {
        Mechanic<Volvo240> volvoMechanic = new Mechanic<>(2);
        Mechanic<Car> generalMechanic = new Mechanic<>(10);
        Volvo240 black = new Volvo240();
        Saab95 white = new Saab95();
        Scania big = new Scania();
        Carcarrier veryempty = new Carcarrier();

    }
}
