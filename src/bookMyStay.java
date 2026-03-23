import java.io.*;
import java.util.*;

// Booking class (Serializable)
class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;

    public Booking(String reservationId, String guestName) {
        this.reservationId = reservationId;
        this.guestName = guestName;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    @Override
    public String toString() {
        return "ReservationID: " + reservationId + ", Guest: " + guestName;
    }
}

// Inventory class (Serializable)
class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private int availableRooms;

    public Inventory(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void bookRoom() {
        if (availableRooms > 0) {
            availableRooms--;
        }
    }

    @Override
    public String toString() {
        return "Available Rooms: " + availableRooms;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // Save state
    public static void saveState(List<Booking> bookings, Inventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bookings);
            oos.writeObject(inventory);
            System.out.println("\nState saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state
    public static Object[] loadState() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Booking> bookings = (List<Booking>) ois.readObject();
            Inventory inventory = (Inventory) ois.readObject();
            System.out.println("State loaded successfully.\n");
            return new Object[]{bookings, inventory};
        } catch (Exception e) {
            System.out.println("Error loading state. Starting with safe defaults.");
            return null;
        }
    }
}

// Main Class
public class  bookMyStay {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Booking> bookings;
        Inventory inventory;

        // Load previous state
        Object[] state = PersistenceService.loadState();

        if (state != null) {
            bookings = (List<Booking>) state[0];
            inventory = (Inventory) state[1];
        } else {
            bookings = new ArrayList<>();
            inventory = new Inventory(5); // default rooms
        }

        while (true) {
            System.out.println("\n1. Add Booking");
            System.out.println("2. View Bookings");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit (Save & Shutdown)");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (inventory.getAvailableRooms() == 0) {
                        System.out.println("No rooms available!");
                        break;
                    }

                    System.out.print("Enter Reservation ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter Guest Name: ");
                    String name = scanner.nextLine();

                    bookings.add(new Booking(id, name));
                    inventory.bookRoom();

                    System.out.println("Booking added successfully.");
                    break;

                case 2:
                    System.out.println("\nBookings:");
                    for (Booking b : bookings) {
                        System.out.println(b);
                    }
                    break;

                case 3:
                    System.out.println(inventory);
                    break;

                case 4:
                    // Save state before exit
                    PersistenceService.saveState(bookings, inventory);
                    System.out.println("System shutting down...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}