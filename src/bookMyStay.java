import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates how booking requests are collected using
 * a Queue to maintain First-Come-First-Served ordering.
 *
 * Version: 5.1
 */


/* ---------------- RESERVATION CLASS ---------------- */

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


/* ---------------- BOOKING REQUEST QUEUE ---------------- */

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request to queue
    void addBookingRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request received from: " + reservation.guestName);
    }

    // Display all requests in queue
    void displayQueue() {

        System.out.println("\n------ Booking Request Queue (FIFO) ------");

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests available.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}


/* ---------------- MAIN APPLICATION ---------------- */

public class bookMyStay {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Hotel Booking Request System");
        System.out.println("           Version 5.1");
        System.out.println("=====================================\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submit booking requests
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");

        // Add requests to queue
        bookingQueue.addBookingRequest(r1);
        bookingQueue.addBookingRequest(r2);
        bookingQueue.addBookingRequest(r3);

        // Display queued requests
        bookingQueue.displayQueue();

        System.out.println("\nAll requests stored in arrival order.");
        System.out.println("Room allocation will be handled in the next use case.");
    }
}