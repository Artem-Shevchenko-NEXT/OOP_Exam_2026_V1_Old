public class Motorcycle extends Vehicle{
    private final boolean hasSidecar;

    public Motorcycle(String licensePlate, String brand, boolean hasSidecar) {
        super(licensePlate, brand);
        this.hasSidecar = hasSidecar;
    }

    public boolean getHasSidecar(){
        return this.hasSidecar;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "hasSidecar=" + hasSidecar +
                "licensePlate='" + getLicensePlate() + '\'' +
                ", brand='" + getBrand() + '\'' +
                '}';
    }
}
