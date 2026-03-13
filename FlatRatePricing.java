public class FlatRatePricing implements  PricingStrategy{
    @Override
    public double computeFee(Vehicle vehicle, int minutesParked, ParkingSpot spot){
        double hours = (double) minutesParked / 60;
        if (hours - (int) hours > 0){
            hours = hours + (1 - (hours -  (int) hours));
        }
        return hours * 100;
    }
}
