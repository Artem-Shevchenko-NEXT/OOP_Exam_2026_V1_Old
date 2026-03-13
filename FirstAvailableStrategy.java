import java.util.List;

public class FirstAvailableStrategy implements  SpotAllocationStrategy{

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots) {
        // OOP Concept: Collections framework efficiency
        // .getFirst() grabs index 0 of the List. Simple, fast O(1) time complexity.
        return availableSpots.getFirst();
    }
}
