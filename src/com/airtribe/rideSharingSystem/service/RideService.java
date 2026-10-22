package com.airtribe.rideSharingSystem.service;

import com.airtribe.rideSharingSystem.constants.RideStatus;
import com.airtribe.rideSharingSystem.model.Driver;
import com.airtribe.rideSharingSystem.model.FareReceipt;
import com.airtribe.rideSharingSystem.model.Ride;
import com.airtribe.rideSharingSystem.model.Rider;
import com.airtribe.rideSharingSystem.strategy.FareStrategy;
import com.airtribe.rideSharingSystem.strategy.RideMatchingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RideService {
    private final RideMatchingStrategy rideMatchingStrategy;
    private final FareStrategy fareStrategy;
    private final List<Ride> rides = new ArrayList<>();
    private int idCounter = 1;

    public RideService(RideMatchingStrategy rideMatchingStrategy, FareStrategy fareStrategy) {
        this.rideMatchingStrategy = rideMatchingStrategy;
        this.fareStrategy = fareStrategy;
    }

    public Ride requestRide(Rider rider, List<Driver> availableDrivers, int distance) {
        if (availableDrivers.isEmpty()) {
            throw new RuntimeException("No available drivers found for matching.");
        }

        Driver matchedDriver = rideMatchingStrategy.findDriver(rider, availableDrivers);
        if (matchedDriver == null) {
            throw new RuntimeException("Could not match a driver at the moment.");
        }
        matchedDriver.setAvailable(false);

        Ride ride = new Ride(idCounter++, rider, matchedDriver, distance, RideStatus.ASSIGNED);
        rides.add(ride);
        return ride;
    }

    public FareReceipt completeRide(int rideId) {
        Ride ride = getRideById(rideId);
        if (ride == null) {
            throw new RuntimeException("Ride not found with ID: " + rideId);
        }
        if (ride.getStatus() == RideStatus.COMPLETED) {
            throw new RuntimeException("Ride is already completed.");
        }

        ride.setStatus(RideStatus.COMPLETED);
        ride.getDriver().setAvailable(true); // Free up driver

        double amount = fareStrategy.fareCalculate(ride);
        return new FareReceipt(ride.getId(), amount, LocalDateTime.now());
    }

    public Ride getRideById(int id) {
        return rides.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Ride> getAllRides() {
        return rides;
    }

}
