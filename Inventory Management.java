
import java.util.ArrayList;
import java.util.Scanner;

class Product {
    int id;
    String name;
    int quantity;
    double price;

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void display() {
        System.out.printf("%-10d %-20s %-10d ₹%.2f%n",
                id, name, quantity, price);
    }
}

public class InventoryManagementSystem {

    static ArrayList<Product> inventory = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Find product by ID
    static Product findProduct(int id) {
        for (Product p : inventory) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    // Add a new product
    static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (findProduct(id) != null) {
            System.out.println("Product ID already exists!");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine());

        if (quantity < 0 || price < 0) {
            System.out.println("Quantity and price cannot be negative!");
            return;
        }

        inventory.add(new Product(id, name, quantity, price));
        System.out.println("Product added successfully!");
    }

    // Display all products
    static void viewProducts() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.printf("%-10s %-20s %-10s %s%n",
                "ID", "Name", "Quantity", "Price");

        for (Product p : inventory) {
            p.display();
        }
    }

    // Search for a product
    static void searchProduct() {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Product p = findProduct(id);

        if (p != null) {
            System.out.println("Product found:");
            System.out.printf("%-10s %-20s %-10s %s%n",
                    "ID", "Name", "Quantity", "Price");
            p.display();
        } else {
            System.out.println("Product not found.");
        }
    }

    // Update product details
    static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Product p = findProduct(id);

        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new price: ");
        double price = Double.parseDouble(sc.nextLine());

        if (quantity < 0 || price < 0) {
            System.out.println("Quantity and price cannot be negative!");
            return;
        }

        p.quantity = quantity;
        p.price = price;

        System.out.println("Product updated successfully!");
    }

    // Delete a product
    static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        Product p = findProduct(id);

        if (p != null) {
            inventory.remove(p);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Calculate total inventory value
    static void calculateInventoryValue() {
        double total = 0;

        for (Product p : inventory) {
            total += p.quantity * p.price;
        }

        System.out.printf("Total Inventory Value: ₹%.2f%n", total);
    }

    // Main method
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Total Inventory Value");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewProducts();
                        break;
                    case 3:
                        searchProduct();
                        break;
                    case 4:
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    case 6:
                        calculateInventoryValue();
                        break;
                    case 7:
                        System.out.println("Thank you for using the system!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
