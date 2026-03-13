import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates how booking requests are collected and ordered
 * using a Queue to ensure First-Come-First-Served processing.
 *
 * Version: 5.1
 */


/* -------------------- Reservation CLASS -------------------- */

class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}


/* -------------------- BOOKING REQUEST QUEUE -------------------- */

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request
    void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for: " + reservation.guestName);
    }

    // Display all queued requests
    void displayRequests() {

        System.out.println("\n------ Booking Request Queue (FIFO Order) ------");

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}


/* -------------------- MAIN APPLICATION -------------------- */

public class bookMyStay{

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Hotel Booking Request System");
        System.out.println("           Version 5.1");
        System.out.println("=====================================\n");


        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();


        // Simulated guest booking requests
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");


        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);


        // Display queued requests
        bookingQueue.displayRequests();
    }
}