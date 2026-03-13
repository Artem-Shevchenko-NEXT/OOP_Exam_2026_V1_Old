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

    // OOP Concept: Overriding
    // Replaces the default Object.toString() (which prints memory addresses)
    // with a meaningful representation of the object's data.
    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
