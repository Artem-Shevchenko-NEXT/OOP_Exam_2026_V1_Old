public interface PricingStrategy {
    double computeFee(Vehicle vehicle, int minutesParked, ParkingSpot spot);
}
