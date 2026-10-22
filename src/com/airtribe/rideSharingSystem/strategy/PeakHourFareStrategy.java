package com.airtribe.rideSharingSystem.strategy;

import com.airtribe.rideSharingSystem.model.Ride;

public class PeakHourFareStrategy implements FareStrategy{
    private static final double SURGE_MULTIPLIER = 1.5;
    private final FareStrategy baseStrategy;

    PeakHourFareStrategy(FareStrategy baseStrategy){this.baseStrategy = baseStrategy;}

    @Override
    public double fareCalculate(Ride ride) {
        return baseStrategy.fareCalculate(ride) * SURGE_MULTIPLIER;
    }
}
