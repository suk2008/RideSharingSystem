package com.airtribe.rideSharingSystem.model;

import java.time.LocalDateTime;

public class FareReceipt {
    private int rideId;
    private double amount;
    private LocalDateTime generatedAt;

    public FareReceipt(int rideId, double amount, LocalDateTime generatedAt) {
        this.rideId = rideId;
        this.amount = amount;
        this.generatedAt = generatedAt;
    }

    @Override
    public String toString() {
        return "FareReceipt{" +
                "rideId=" + rideId +
                ", amount=" + amount +
                ", generatedAt=" + generatedAt +
                '}';
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
