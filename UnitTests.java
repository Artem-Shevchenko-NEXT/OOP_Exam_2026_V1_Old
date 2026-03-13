// Brings in the JUnit 5 Assertions class. This allows you to use methods like Assertions.assertTrue()
// to check if your code produces the expected result.
import org.junit.jupiter.api.Assertions;
// Brings in the @BeforeEach annotation. Any method with this annotation runs before every single test.
import org.junit.jupiter.api.BeforeEach;
// Brings in the @Test annotation. This tells the IDE that the method below it is a test case.
import org.junit.jupiter.api.Test;

// Brings in the specific Java Collections needed to create lists of vehicles for testing.
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//These static imports are from JUnit 4 (org.junit.Assert), whereas the ones above
// are from JUnit 5 (org.junit.jupiter).
//The imports below arent needed.
import static java.util.Collections.sort;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

class UnitTests {

    ParkingGarage parkingGarage = null;

    // Testing Concept: Test Isolation
    // @BeforeEach runs before every @Test method.
    // It guarantees a clean slate (a fresh Garage object) so tests don't corrupt each other's data.
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

        // Testing Concept: Assertion
        // This is the "Assert" step of the Arrange-Act-Assert pattern.
        // It programmatically verifies that the actual output matches the expected outcome.
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
        // Testing Concept: Verifying State Change
        // assertNull verifies that after calling removeVehicle, the car no longer exists in the garage map.
        Assertions.assertNull(parkingSpot);
    }

    @Test
    void getParkingSpotTest() {
        ElectricCar electricCar = new ElectricCar("ABCD", "Mercedes", 3);
        ParkingSpot evParkingSpot = new ParkingSpot("A", 2, "EV");
        parkingGarage.addParkingSpot(evParkingSpot);
        parkingGarage.parkVehicleAuto(electricCar);

        // asserts that the spot we manually created (evParkingSpot),
        // is the exact same spot returned by getParkingSpot("ABCD").
        Assertions.assertSame(evParkingSpot, parkingGarage.getParkingSpot("ABCD"));
    }

    @Test
    void findVehiclesInZoneTest() {
        // Arrange: Setup spots, cars, and park them.
        Collection<Vehicle> vehiclesInZone = new ArrayList<>();
        ParkingSpot evParkingSpot = new ParkingSpot("A", 2, "EV");
        ParkingSpot evParkingSpot2 = new ParkingSpot("A", 3, "EV");
        parkingGarage.addParkingSpot(evParkingSpot);
        parkingGarage.addParkingSpot(evParkingSpot2);

        // Arrange: Create cars and park them
        ElectricCar electricCar = new ElectricCar("ABCDE", "Mercedes", 3);
        ElectricCar electricCar2 = new ElectricCar("ABCD", "Mercedes", 3);
        parkingGarage.parkVehicleAuto(electricCar);
        parkingGarage.parkVehicleAuto(electricCar2);

        // Arrange: Create our "Expected" list of vehicles.
        // This is exactly what we expect the garage to hand back to us.
        vehiclesInZone.add(electricCar);
        vehiclesInZone.add(electricCar2);

        // Act: Call the method we are actually testing to get the "Actual" list.
        Collection<Vehicle> actualVehiclesInZone = parkingGarage.findVehiclesInZone("EV");

        // Testing Logic: Manual Comparison
        // Converts the collections to arrays so we can manually compare them item by item.
        // Assertions.assertIterableEquals() would be a cleaner way to do this.
        Object[] vehiclesActual = actualVehiclesInZone.toArray();
        Object[] vehicles = vehiclesInZone.toArray();

        boolean actual = true;
        if (vehicles[0] != vehiclesActual[0]) {
            actual = false;
        }
        if (vehicles[1] != vehiclesActual[1]) {
            actual = false;
        }

        // Assert: If the manual check passed, 'actual' will still be true.
        Assertions.assertTrue(actual);

    }

    @Test
    void betterFindVehiclesInZoneTest() {
        ParkingSpot evParkingSpot = new ParkingSpot("A", 2, "EV");
        ParkingSpot evParkingSpot2 = new ParkingSpot("A", 3, "EV");
        parkingGarage.addParkingSpot(evParkingSpot);
        parkingGarage.addParkingSpot(evParkingSpot2);

        ElectricCar electricCar = new ElectricCar("ABCDE", "Mercedes", 3);
        ElectricCar electricCar2 = new ElectricCar("ABCD", "Mercedes", 3);
        parkingGarage.parkVehicleAuto(electricCar);
        parkingGarage.parkVehicleAuto(electricCar2);

        List<Vehicle> expectedVehicles = new ArrayList<>();
        expectedVehicles.add(electricCar);
        expectedVehicles.add(electricCar2);

        Collection<Vehicle> actualVehiclesInZone = parkingGarage.findVehiclesInZone("EV");

        // Assert: JUnit 5's assertIterableEquals cleanly compares both collections.
        // It automatically checks if they have the same size and contain the exact same elements.

        // assertIterableEquals checks that they are in the exact same ORDER.
        // Because the Garage stores occupied spots in a HashMap (which doesn't guarantee order),
        // if this test ever randomly fails due to order, a great alternative assertion is:
        // Assertions.assertTrue(actualVehiclesInZone.containsAll(expectedVehicles) && actualVehiclesInZone.size() == expectedVehicles.size());
        Assertions.assertIterableEquals(expectedVehicles, actualVehiclesInZone);
    }
}
