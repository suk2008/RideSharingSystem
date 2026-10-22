package com.airtribe.rideSharingSystem.strategy;

import com.airtribe.rideSharingSystem.model.Ride;

public interface FareStrategy {
    double fareCalculate(Ride ride);
}
