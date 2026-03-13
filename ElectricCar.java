public class ElectricCar extends Vehicle{
    private final int batteryCapacity;

    public ElectricCar (String licensePlate, String brand, int batteryCapacity){
        // OOP Concept: Inheritance
        // 'super' calls the parent class (Vehicle) constructor to initialize the inherited private fields.
        // This must be the first statement in the constructor.
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
