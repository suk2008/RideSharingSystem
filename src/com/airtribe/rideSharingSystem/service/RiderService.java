package com.airtribe.rideSharingSystem.service;

import com.airtribe.rideSharingSystem.model.Rider;

import java.util.ArrayList;
import java.util.List;

public class RiderService {
    private final List<Rider> riders = new ArrayList<>();
    private int idCounter = 1;

    public Rider registerRider(String name, String location) {
        Rider rider = new Rider(idCounter++, name, location);
        riders.add(rider);
        return rider;
    }

    public Rider getRiderById(int id) {
        return riders.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Rider> getAllRiders() {
        return riders;
    }
}
