public class ElectricCar extends Vehicle{
    private final int batteryCapacity;

    public ElectricCar (String licensePlate, String brand, int batteryCapacity){
        super(licensePlate,brand);
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity(){
        return this.batteryCapacity;
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "batteryCapacity=" + batteryCapacity +
                "licensePlate='" + getLicensePlate() + '\'' +
                ", brand='" + getBrand() + '\'' +
                '}';
    }
}
