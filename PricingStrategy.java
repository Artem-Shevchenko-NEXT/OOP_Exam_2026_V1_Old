// OOP Design Pattern: Strategy Pattern (The Contract)
// Interfaces define 'what' needs to be done, not 'how'.
// Any class implementing this interface guarantees it has a computeFee method.
public interface PricingStrategy {
    double computeFee(Vehicle vehicle, int minutesParked, ParkingSpot spot);
}
