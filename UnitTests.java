import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.sort;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

class UnitTests {

    ParkingGarage parkingGarage = null;

    @BeforeEach
    public void createGarage() {
        FlatRatePricing flatRatePricing = new FlatRatePricing();
        FirstAvailableStrategy firstAvailableStrategy = new FirstAvailableStrategy();
        parkingGarage = new ParkingGarage(10, flatRatePricing, firstAvailableStrategy);
    }

    @Test
    void parkNormalVehicleTest() {
        Vehicle vehicle = new Vehicle("ABCD", "Mercedes");
        parkingGarage.parkVehicleAuto(vehicle);
        ParkingSpot parkingSpot = parkingGarage.occupiedParkingSpots.get("ABCD");
        Assertions.assertSame(parkingSpot.getVehicle(), vehicle);
    }

    @Test
    void parkElectricCarVehicleTest() {
        ElectricCar electricCar = new ElectricCar("ABCD", "Mercedes", 3);
        ParkingSpot evParkingSpot = new ParkingSpot("A", 2, "EV");
        parkingGarage.addParkingSpot(evParkingSpot);
        parkingGarage.parkVehicleAuto(electricCar);
        ParkingSpot parkingSpot = parkingGarage.occupiedParkingSpots.get("ABCD");
        Assertions.assertSame(parkingSpot.getVehicle(), electricCar);
    }

    @Test
    void removeVehicleTest() {
        Vehicle vehicle = new Vehicle("ABCD", "Mercedes");
        parkingGarage.parkVehicleAuto(vehicle);
        parkingGarage.removeVehicle(vehicle);

        ParkingSpot parkingSpot = parkingGarage.occupiedParkingSpots.get("ABCD");
        Assertions.assertNull(parkingSpot);
    }

    @Test
    void getParkingSpotTest() {
        ElectricCar electricCar = new ElectricCar("ABCD", "Mercedes", 3);
        ParkingSpot evParkingSpot = new ParkingSpot("A", 2, "EV");
        parkingGarage.addParkingSpot(evParkingSpot);
        parkingGarage.parkVehicleAuto(electricCar);

        Assertions.assertSame(evParkingSpot, parkingGarage.getParkingSpot("ABCD"));
    }

    @Test
    void findVehiclesInZoneTest() {
        Collection<Vehicle> vehiclesInZone = new ArrayList<>();
        ParkingSpot evParkingSpot = new ParkingSpot("A", 2, "EV");
        ParkingSpot evParkingSpot2 = new ParkingSpot("A", 3, "EV");
        parkingGarage.addParkingSpot(evParkingSpot);
        parkingGarage.addParkingSpot(evParkingSpot2);

        ElectricCar electricCar = new ElectricCar("ABCDE", "Mercedes", 3);
        ElectricCar electricCar2 = new ElectricCar("ABCD", "Mercedes", 3);
        parkingGarage.parkVehicleAuto(electricCar);
        parkingGarage.parkVehicleAuto(electricCar2);

        vehiclesInZone.add(electricCar);
        vehiclesInZone.add(electricCar2);

        Collection<Vehicle> actualVehiclesInZone = parkingGarage.findVehiclesInZone("EV");
        Object[] vehiclesActual = actualVehiclesInZone.toArray();
        Object[] vehicles = vehiclesInZone.toArray();

        boolean actual = true;
        if (vehicles[0] != vehiclesActual[0]) {
            actual = false;
        }
        if (vehicles[1] != vehiclesActual[1]) {
            actual = false;
        }

        Assertions.assertTrue(actual);

    }
}
