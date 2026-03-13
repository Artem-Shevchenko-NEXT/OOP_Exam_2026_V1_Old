public class ParkingSpot {
    private final String level;
    private final int spotNumber;
    private final String zone;
    private Vehicle vehicleParked;

    public ParkingSpot(String level, int spotNumber, String zone){
        this.level = level;
        this.spotNumber = spotNumber;
        this.zone = zone;
    }

    public String getLevel(){
        return this.level;
    }

    public int getSpotNumber(){
        return this.spotNumber;
    }

    public String getZone(){
        return this.zone;
    }

    public Vehicle getVehicle(){
        return this.vehicleParked;
    }

    public void setVehicle(Vehicle vehicle){
        this.vehicleParked = vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "level='" + level + '\'' +
                ", spotNumber=" + spotNumber +
                ", zone='" + zone + '\'' +
                ", vehicleParked=" + vehicleParked +
                '}';
    }
}
