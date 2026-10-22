package com.airtribe.rideSharingSystem.strategy;

import com.airtribe.rideSharingSystem.model.Ride;

public class DefaultFareStrategy implements FareStrategy{
    private static final double BASE_FARE = 25.0;
    private static final double RATE_PER_KM = 11.0;
    @Override
    public double fareCalculate(Ride ride) {
        return BASE_FARE + (ride.getDistance() * RATE_PER_KM);
    }
}
