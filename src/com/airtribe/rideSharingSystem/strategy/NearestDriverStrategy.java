package com.airtribe.rideSharingSystem.strategy;

import com.airtribe.rideSharingSystem.model.Driver;
import com.airtribe.rideSharingSystem.model.Rider;

import java.util.List;

public class NearestDriverStrategy implements RideMatchingStrategy{

    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        return drivers.stream().filter(Driver::isAvailable).findFirst().orElse(null);
    }
}
