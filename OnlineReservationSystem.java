import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ReservationSystem {
    private Map<String, String> reservations;

    public ReservationSystem() {
        reservations = new HashMap<>();
    }

    public void makeReservation(String username, String reservationDetails) {
        reservations.put(username, reservationDetails);
    }

    public void cancelReservation(String username) {
        reservations.remove(username);
    }

    public void printReservations() {
        for (Map.Entry<String, String> entry : reservations.entrySet()) {
            System.out.println("Username: " + entry.getKey() + ", Reservation: " + entry.getValue());
        }
    }
}

public class OnlineReservationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static ReservationSystem reservationSystem = new ReservationSystem();

    public static void main(String[] args) {
        // Create some example users
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
                    System.out.println("Login successful.");
                    reservationMenu(username, scanner);
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void reservationMenu(String username, Scanner scanner) {
        while (true) {
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter reservation details: ");
                    String reservationDetails = scanner.nextLine();
                    reservationSystem.makeReservation(username, reservationDetails);
                    System.out.println("Reservation made successfully.");
                    break;
                case 2:
                    reservationSystem.cancelReservation(username);
                    System.out.println("Reservation canceled successfully.");
                    break;
                case 3:
                    reservationSystem.printReservations();
                    break;
                case 4:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
