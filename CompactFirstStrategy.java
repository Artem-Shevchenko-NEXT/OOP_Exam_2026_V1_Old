import java.util.List;

public class CompactFirstStrategy implements SpotAllocationStrategy{

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots) {
        ParkingSpot chosenSpot = availableSpots.getFirst();
        for (ParkingSpot spot : availableSpots){
            if (spot.getZone().equals("Compact")){
                chosenSpot = spot;
                break;
            }
        }
        return chosenSpot;
    }
}
