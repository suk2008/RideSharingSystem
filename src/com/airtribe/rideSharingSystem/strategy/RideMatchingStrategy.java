package com.airtribe.rideSharingSystem.strategy;

import com.airtribe.rideSharingSystem.model.Driver;
import com.airtribe.rideSharingSystem.model.Rider;

import java.util.List;

public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
}
