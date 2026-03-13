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


// Single Room class
class SingleRoom extends Room {

    SingleRoom() {
        super("Single Room", 1, 200, 2500);
    }
}


// Double Room class
class DoubleRoom extends Room {

    DoubleRoom() {
        super("Double Room", 2, 350, 4000);
    }
}


// Suite Room class
class SuiteRoom extends Room {

    SuiteRoom() {
        super("Suite Room", 3, 600, 7500);
    }
}



// Main Application Class
public class bookMyStay {

    public static void main(String[] args) {

        // -------- USE CASE 1 --------
        System.out.println("=====================================");
        System.out.println("   Welcome to Hotel Booking System   ");
        System.out.println("           Version 2.1               ");
        System.out.println("=====================================");

        System.out.println("Application started successfully!\n");


        // -------- USE CASE 2 --------

        // Creating Room objects
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("-------- Available Room Types --------\n");

        System.out.println("Single Room Details:");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable);
        System.out.println();

        System.out.println("Double Room Details:");
        dbl.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable);
        System.out.println();

        System.out.println("Suite Room Details:");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);
        System.out.println();


        System.out.println("Thank you for using the Hotel Booking System.");
    }
}

