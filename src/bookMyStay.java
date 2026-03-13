import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management using HashMap.
 * Availability of room types is stored in a single data structure
 * to ensure consistent updates and fast lookups.
 *
 * Version: 3.1
 */

/* ------------------- DOMAIN MODEL ------------------- */

// Abstract Room class
abstract class Room {

    String roomType;
    int beds;
    int size;
    double price;

    Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Room Size: " + size + " sq.ft");
        System.out.println("Price per Night: ₹" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    SingleRoom() {
        super("Single", 1, 200, 2500);
    }
}

// Double Room
class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double", 2, 350, 4000);
    }
}

// Suite Room
class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite", 3, 600, 7500);
    }
}


/* ------------------- INVENTORY COMPONENT ------------------- */

class RoomInventory {

    // Centralized storage for room availability
    private HashMap<String, Integer> inventory;

    // Constructor initializes availability
    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    // Retrieve availability
    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display current inventory
    void displayInventory() {

        System.out.println("------ Current Room Inventory ------");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}


/* ------------------- APPLICATION ENTRY ------------------- */

public class bookMyStay {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Welcome to Hotel Booking System   ");
        System.out.println("           Version 3.1               ");
        System.out.println("=====================================\n");

        // Create room objects (domain model)
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Display room details
        System.out.println("---- Room Types ----\n");

        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Single"));
        System.out.println();

        dbl.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Double"));
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Suite"));
        System.out.println();

        // Display centralized inventory
        inventory.displayInventory();
    }
}
