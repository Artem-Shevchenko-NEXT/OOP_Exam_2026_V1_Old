// OOP Concept: Exception Handling
// Extending RuntimeException creates a custom unchecked exception.
// Allows us to throw specific, descriptive errors rather than generic system crashes.
public class GarageFullException extends RuntimeException {
    public GarageFullException(String message) {
        super(message);
    }
}
