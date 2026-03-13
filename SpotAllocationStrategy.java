import java.util.List;

public interface SpotAllocationStrategy {
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots);
}
