package com.airtribe.rideSharingSystem.model;

import com.airtribe.rideSharingSystem.constants.VehicleType;

public class Driver {
    private int id;
    private String name;
    private String currentLocation;
    private boolean available;
    private VehicleType vehicleType;

    public Driver(int id, String name, String currentLocation, boolean available, VehicleType vehicleType) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.available = available;
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", available=" + available +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
