import java.util.List;

// OOP Design Pattern: Strategy Pattern (Spot Allocation Contract)
// Defines the contract for finding a spot. The Garage doesn't care how a spot is found,
// it only cares that it gets a ParkingSpot back.
public interface SpotAllocationStrategy {
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots);
}
