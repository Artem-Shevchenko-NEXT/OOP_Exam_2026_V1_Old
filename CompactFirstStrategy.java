import java.util.List;

public class CompactFirstStrategy implements SpotAllocationStrategy{

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots) {
        // Logic: Fallback mechanism
        // We initialize with the first available spot. If the loop below fails to find a "Compact" spot,
        // we already have our fallback ready to return, satisfying the exam requirement.
        ParkingSpot chosenSpot = availableSpots.getFirst();
        for (ParkingSpot spot : availableSpots){
            if (spot.getZone().equals("Compact")){
                chosenSpot = spot;
                // Logic: Exit early
                // Once we find the first compact spot, we 'break' to stop looping and save performance.
                break;
            }
        }
        return chosenSpot;
    }
}
