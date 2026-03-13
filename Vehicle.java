public class Vehicle {
    private final String licensePlate;
    private final String brand;

    public Vehicle(String licensePlate, String brand) {
        this.licensePlate = licensePlate;
        this.brand = brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
