import java.util.List;

public class FirstAvailableStrategy implements  SpotAllocationStrategy{

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots) {
        return availableSpots.getFirst();
    }
}
