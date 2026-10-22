import com.airtribe.rideSharingSystem.constants.VehicleType;
import com.airtribe.rideSharingSystem.model.Driver;
import com.airtribe.rideSharingSystem.model.FareReceipt;
import com.airtribe.rideSharingSystem.model.Ride;
import com.airtribe.rideSharingSystem.model.Rider;
import com.airtribe.rideSharingSystem.service.DriverService;
import com.airtribe.rideSharingSystem.service.RideService;
import com.airtribe.rideSharingSystem.service.RiderService;
import com.airtribe.rideSharingSystem.strategy.DefaultFareStrategy;
import com.airtribe.rideSharingSystem.strategy.FareStrategy;
import com.airtribe.rideSharingSystem.strategy.NearestDriverStrategy;
import com.airtribe.rideSharingSystem.strategy.RideMatchingStrategy;

import java.util.Scanner;

public class ConsoleApplicationMenu{
    public static void main(String[] args) {
        RideMatchingStrategy matchingStrategy = new NearestDriverStrategy();
        FareStrategy fareStrategy = new DefaultFareStrategy();

        RiderService riderService = new RiderService();
        DriverService driverService = new DriverService();
        RideService rideService = new RideService(matchingStrategy, fareStrategy);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("           RIDEWISE MENU            ");
            System.out.println("====================================");
            System.out.println("1. Add Rider");
            System.out.println("2. Add Driver");
            System.out.println("3. View Available Drivers");
            System.out.println("4. Request Ride");
            System.out.println("5. Complete Ride");
            System.out.println("6. View Rides");
            System.out.println("7. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Rider Name: ");
                        String rName = scanner.nextLine();
                        System.out.print("Enter Rider Location: ");
                        String rLocation = scanner.nextLine();
                        Rider rider = riderService.registerRider(rName, rLocation);
                        System.out.println("✅ Rider registered successfully with ID: " + rider.getId());
                        break;

                    case 2:
                        System.out.print("Enter Driver Name: ");
                        String dName = scanner.nextLine();
                        System.out.print("Enter Driver Location: ");
                        String dLocation = scanner.nextLine();
                        System.out.print("Enter Vehicle Type (BIKE, AUTO, CAR): ");
                        VehicleType vType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                        Driver driver = driverService.registerDriver(dName, dLocation, vType);
                        System.out.println("✅ Driver registered successfully with ID: " + driver.getId());
                        break;

                    case 3:
                        System.out.println("\n--- Available Drivers ---");
                        var availableDrivers = driverService.listAvailableDrivers();
                        if (availableDrivers.isEmpty()) {
                            System.out.println("No drivers available.");
                        } else {
                            availableDrivers.forEach(d -> System.out.println("ID: " + d.getId() + ", Name: " + d.getName() + ", Vehicle: " + d.getVehicleType() + ", Location: " + d.getCurrentLocation()));
                        }
                        break;

                    case 4:
                        System.out.print("Enter Rider ID: ");
                        int riderId = Integer.parseInt(scanner.nextLine());
                        Rider foundRider = riderService.getRiderById(riderId);
                        if (foundRider == null) {
                            System.out.println("❌ Error: Rider not found.");
                            break;
                        }

                        System.out.print("Enter Estimated Distance (km): ");
                        int distance = Integer.parseInt(scanner.nextLine());

                        try {
                            Ride ride = rideService.requestRide(foundRider, driverService.listAvailableDrivers(), distance);
                            System.out.println("✅ Ride requested & assigned successfully!");
                            System.out.println("Ride ID: " + ride.getId() + " | Assigned Driver: " + ride.getDriver().getName());
                        } catch (Exception e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Enter Ride ID to Complete: ");
                        int rideId = Integer.parseInt(scanner.nextLine());
                        try {
                            FareReceipt receipt = rideService.completeRide(rideId);
                            System.out.println("✅ Ride completed successfully!");
                            System.out.println("Receipt -> Ride ID: " + receipt.getRideId() + " | Fare Amount: ₹" + receipt.getAmount() + " | Time: " + receipt.getGeneratedAt());
                        } catch (Exception e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("\n--- All Rides ---");
                        var rides = rideService.getAllRides();
                        if (rides.isEmpty()) {
                            System.out.println("No rides recorded yet.");
                        } else {
                            rides.forEach(r -> System.out.println("Ride ID: " + r.getId() + " | Rider: " + r.getRider().getName() + " | Driver: " + r.getDriver().getName() + " | Status: " + r.getStatus() + " | Distance: " + r.getDistance() + "km"));
                        }
                        break;

                    case 7:
                        running = false;
                        System.out.println("Exiting RideWise. Have a great day!");
                        break;

                    default:
                        System.out.println("❌ Invalid choice. Please select between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input format. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid Enum value provided (check vehicle type).");
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}

