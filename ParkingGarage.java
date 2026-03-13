import java.security.InvalidParameterException;
import java.util.*;

public class ParkingGarage {
    private int maxVehicles;
    //We use the Map collection to store vehicle license plates on parking spots, as we can easily
    //assign and look up vehicles on parking spots
    protected final Map<String, ParkingSpot> occupiedParkingSpots;
    private final List<ParkingSpot> availableParkingSpots = new ArrayList<>();
    private final PricingStrategy pricingStrategy;
    private final SpotAllocationStrategy spotAllocationStrategy;

    public ParkingGarage(int maxVehicles, PricingStrategy pricingStrategy, SpotAllocationStrategy spotAllocationStrategy) {
        this.maxVehicles = maxVehicles;
        this.pricingStrategy = pricingStrategy;
        this.spotAllocationStrategy = spotAllocationStrategy;
        occupiedParkingSpots = new HashMap<>();
        //We add one default parking spot so there is always one. Further parking spots
        //Must be added first via 'void addParkingSpot()'
        availableParkingSpots.add(new ParkingSpot("A", 1, "General"));
    }

    public void addParkingSpot(ParkingSpot newParkingSpot) {
        if (newParkingSpot == null) {
            throw new InvalidParameterException("ParkingSpot cannot be null");
        }
        String newLevel = newParkingSpot.getLevel();
        int newSpot = newParkingSpot.getSpotNumber();
        //We check if a parking spot already exists by comparing level and spot
        for (ParkingSpot parkingSpot : availableParkingSpots) {
            if (parkingSpot.getLevel().equals(newLevel) && parkingSpot.getSpotNumber() == newSpot) {
                throw new InvalidParameterException("ParkingSpot already exists!");
            }
        }
        availableParkingSpots.add(newParkingSpot);
    }

    public void parkVehicleAuto(Vehicle vehicle) {
        if (vehicle == null) {
            throw new InvalidParameterException("Vehicle cannot be null");
        }
        if (occupiedParkingSpots.size() >= maxVehicles) {
            throw new GarageFullException("Garage is full!");
        }
        ParkingSpot foundVehicle = occupiedParkingSpots.get(vehicle.getLicensePlate());
        if (foundVehicle != null) {
            throw new DuplicateVehicleException("Vehicle already parked!");
        }
        //We create a dummy electric car to compare classes
        ElectricCar electricCar = new ElectricCar("A", "B", 1);
        ParkingSpot chosenParkingSpot = null;
        boolean evSpot = false;
        //We check if there is an available parking spot.
        //If the vehicle is an electric car, we can only assign a parking spot with the right zone
        for (ParkingSpot parkingSpot : availableParkingSpots) {
            if (vehicle.getClass() == electricCar.getClass() && parkingSpot.getZone().equals("EV")) {
                chosenParkingSpot = parkingSpot;
                evSpot = true;
                break;
            } else if (vehicle.getClass() != electricCar.getClass()) {
                chosenParkingSpot = parkingSpot;
                break;
            }
        }
        //If no parking spot is found, we know that there was no EV zones left for an electric car
        if (chosenParkingSpot == null) {
            throw new IncompatibleZoneException("Incompatible zone!");
        }
        if (!evSpot){
            chosenParkingSpot = spotAllocationStrategy.findSpot(availableParkingSpots);
        }
        chosenParkingSpot.setVehicle(vehicle);
        occupiedParkingSpots.put(vehicle.getLicensePlate(), chosenParkingSpot);
        availableParkingSpots.remove(chosenParkingSpot);
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new InvalidParameterException("Vehicle cannot be null");
        }
        ParkingSpot parkingSpot = occupiedParkingSpots.get(vehicle.getLicensePlate());
        parkingSpot.setVehicle(null);
        occupiedParkingSpots.remove(vehicle.getLicensePlate());
        //After removing the vehicle from the parking spot, we add the parking spot
        //to the list of available parking spots
        availableParkingSpots.add(parkingSpot);
    }

    public ParkingSpot getParkingSpot(String licensePlate) {
        if (licensePlate == null) {
            throw new InvalidParameterException("LicensePlate cannot be null");
        }
        ParkingSpot parkingSpot = occupiedParkingSpots.get(licensePlate);
        if (parkingSpot == null){
            throw new NonPresentVehicleException("Vehicle not parked!");
        }
        return parkingSpot;
    }

    public Collection<Vehicle> findVehiclesInZone (String zone){
        List<Vehicle> vehiclesInZone = new ArrayList<>();
        for (ParkingSpot parkingSpot : occupiedParkingSpots.values()){
            if (zone.equals(parkingSpot.getZone())){
                vehiclesInZone.add(parkingSpot.getVehicle());
            }
        }
        return vehiclesInZone;
    }

    public double calculateFee(String licensePlate, int minutesParked){
        ParkingSpot spot = occupiedParkingSpots.get(licensePlate);
        Vehicle vehicle = spot.getVehicle();
        return pricingStrategy.computeFee(vehicle, minutesParked, spot);
    }

}
