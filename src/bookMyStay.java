import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory class
class Inventory {
    private Map<String, Integer> roomAvailability;

    public Inventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 2);
        roomAvailability.put("Suite", 1);
    }

    public boolean isValidRoomType(String type) {
        return roomAvailability.containsKey(type);
    }

    public int getAvailableRooms(String type) {
        return roomAvailability.getOrDefault(type, 0);
    }

    public void bookRoom(String type) throws InvalidBookingException {
        int available = getAvailableRooms(type);

        if (available <= 0) {
            throw new InvalidBookingException("No available rooms for type: " + type);
        }

        roomAvailability.put(type, available - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Validator class
class BookingValidator {

    public static void validate(String reservationId, String guestName, String roomType, Inventory inventory)
            throws InvalidBookingException {

        if (reservationId == null || reservationId.trim().isEmpty()) {
            throw new InvalidBookingException("Reservation ID cannot be empty.");
        }

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.getAvailableRooms(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}

// Booking class
class Booking {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Booking(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "ReservationID: " + reservationId + ", Guest: " + guestName + ", RoomType: " + roomType;
    }
}

// Main Class
public class bookMyStay {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        List<Booking> bookings = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Book Room");
            System.out.println("2. View Inventory");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Reservation ID: ");
                        String id = scanner.nextLine();

                        System.out.print("Enter Guest Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Room Type (Single/Double/Suite): ");
                        String type = scanner.nextLine();

                        // Validation (Fail-Fast)
                        BookingValidator.validate(id, name, type, inventory);

                        // Process booking
                        inventory.bookRoom(type);
                        bookings.add(new Booking(id, name, type));

                        System.out.println("Booking successful!");

                    } catch (InvalidBookingException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Unexpected error occurred.");
                    }
                    break;

                case 2:
                    inventory.displayInventory();
                    break;

                case 3:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}