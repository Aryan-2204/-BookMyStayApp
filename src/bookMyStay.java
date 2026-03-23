import java.util.*;

/**
 * UseCase11ConcurrentBooking
 *
 * Demonstrates thread-safe booking using synchronization.
 * Prevents race conditions and double allocation.
 *
 * Version: 11.1
 */


/* ---------------- RESERVATION ---------------- */

class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}


/* ---------------- THREAD-SAFE INVENTORY ---------------- */

class RoomInventory {

    private Map<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 1);
    }

    // synchronized ensures only one thread enters at a time
    public synchronized boolean allocateRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            System.out.println(Thread.currentThread().getName()
                    + " allocating " + roomType);

            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }

    public void display() {
        System.out.println("\nFinal Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}


/* ---------------- SHARED QUEUE ---------------- */

class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.offer(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}


/* ---------------- THREAD PROCESSOR ---------------- */

class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    BookingProcessor(BookingQueue queue, RoomInventory inventory, String name) {
        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation r;

            // synchronized queue access
            synchronized (queue) {
                r = queue.getRequest();
            }

            if (r == null) {
                break;
            }

            boolean success = inventory.allocateRoom(r.roomType);

            if (success) {
                System.out.println(getName() + " SUCCESS for " + r.guestName);
            } else {
                System.out.println(getName() + " FAILED for " + r.guestName);
            }
        }
    }
}


/* ---------------- MAIN APPLICATION ---------------- */

public class bookMyStay{

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Concurrent Booking Simulation");
        System.out.println("           Version 11.1");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Simulate multiple guest requests
        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Single"));
        queue.addRequest(new Reservation("Charlie", "Double"));
        queue.addRequest(new Reservation("David", "Double"));

        // Create multiple threads (simulating concurrent users)
        Thread t1 = new BookingProcessor(queue, inventory, "Thread-1");
        Thread t2 = new BookingProcessor(queue, inventory, "Thread-2");

        // Start threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final state
        inventory.display();

        System.out.println("\nAll bookings processed safely without conflicts.");
    }
}}