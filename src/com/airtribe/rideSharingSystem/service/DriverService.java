package com.airtribe.rideSharingSystem.service;

import com.airtribe.rideSharingSystem.constants.VehicleType;
import com.airtribe.rideSharingSystem.model.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DriverService {
    private final List<Driver> drivers = new ArrayList<>();
    private int idCounter = 1;

    public Driver registerDriver(String name, String currentLocation, VehicleType vehicleType) {
        Driver driver = new Driver(idCounter++, name, currentLocation, true, vehicleType);
        drivers.add(driver);
        return driver;
    }
    public void updateAvailability(int driverId, boolean available) {
        Driver driver = getDriverById(driverId);
        if (driver != null) {       driver.setAvailable(available);        }
    }

    public List<Driver> listAvailableDrivers() {
        return drivers.stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }

    public Driver getDriverById(int id) {
        return drivers.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public List<Driver> getAllDrivers() {
        return drivers;
    }

}
