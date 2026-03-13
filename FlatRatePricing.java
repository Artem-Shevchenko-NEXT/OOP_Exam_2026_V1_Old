// OOP Design Pattern: Strategy Pattern (Concrete Implementation)
// The 'implements' keyword forces this class to provide the actual logic for the interface's contract.
// We can swap this out easily without breaking the main Garage code.
public class FlatRatePricing implements  PricingStrategy{
    @Override
    public double computeFee(Vehicle vehicle, int minutesParked, ParkingSpot spot){
        double hours = (double) minutesParked / 60;
        // Logic: Manual calculation to round up to the next started hour.
        // Note for future: Math.ceil((double) minutesParked / 60) achieves the exact same thing in one line.
        if (hours - (int) hours > 0){
            hours = hours + (1 - (hours -  (int) hours));
        }
        return hours * 100;
    }
}
