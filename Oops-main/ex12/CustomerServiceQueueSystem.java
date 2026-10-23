
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Customer {
    private int tokenNumber;
    private String customerName;

    public Customer(int tokenNumber, String customerName) {
        this.tokenNumber = tokenNumber;
        this.customerName = customerName;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void displayCustomer() {
        System.out.println("Token Number : " + tokenNumber);
        System.out.println("Customer Name: " + customerName);
    }
}

public class CustomerServiceQueueSystem {

    static Queue<Customer> queue = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextToken = 1;

    static void generateToken() {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        Customer c = new Customer(nextToken++, name);
        queue.offer(c);

        System.out.println("\nToken Generated Successfully!");
        c.displayCustomer();
    }

    static void serveCustomer() {
        if (queue.isEmpty()) {
            System.out.println("No customers waiting!");
            return;
        }

        Customer c = queue.poll();

        System.out.println("\n----- SERVED CUSTOMER -----");
        c.displayCustomer();
        System.out.println("Service Status: Completed");
    }

    static void displayWaitingCustomers() {
        if (queue.isEmpty()) {
            System.out.println("No customers waiting!");
            return;
        }

        System.out.println("\n----- WAITING CUSTOMERS -----");

        for (Customer c : queue) {
            c.displayCustomer();
            System.out.println("----------------------");
        }

        System.out.println("Total Waiting Customers: " + queue.size());
    }

    static void searchCustomer() {
        System.out.print("Enter Token Number: ");

        try {
            int token = Integer.parseInt(sc.nextLine());

            for (Customer c : queue) {
                if (c.getTokenNumber() == token) {
                    System.out.println("Customer Found!");
                    c.displayCustomer();
                    return;
                }
            }

            System.out.println(
                "Customer not found in waiting queue.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid token number!");
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" CUSTOMER SERVICE QUEUE SYSTEM");
            System.out.println("================================");
            System.out.println("1. Generate Customer Token");
            System.out.println("2. Serve Next Customer");
            System.out.println("3. Display Waiting Customers");
            System.out.println("4. Search Customer by Token");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        generateToken();
                        break;

                    case 2:
                        serveCustomer();
                        break;

                    case 3:
                        displayWaitingCustomers();
                        break;

                    case 4:
                        searchCustomer();
                        break;

                    case 5:
                        System.out.println(
                            "Thank you! Visit Again.");
                        break;

                    default:
                        System.out.println(
                            "Invalid choice! Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }

        } while (choice != 5);

        sc.close();
    }
}
