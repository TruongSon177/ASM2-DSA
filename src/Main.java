import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mytree tree = new Mytree();

        tree.insert(new Student(5, "son", 8.5));
        tree.insert(new Student(3, "giang", 7.0));
        tree.insert(new Student(8, "nam", 9.2));
        tree.insert(new Student(2, "quyet", 6.5));
        tree.insert(new Student(6, "tuan", 8.0));

        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Add student");
                System.out.println("2. Update student information");
                System.out.println("3. Delete student");
                System.out.println("4. Search for student");
                System.out.println("5. Display tree (In-order Traversal)");
                System.out.println("6. Sort by ID");
                System.out.println("7. Sort by Rank");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(scanner, tree);
                        break;
                    case 2:
                        updateStudent(scanner, tree);
                        break;
                    case 3:
                        deleteStudent(scanner, tree);
                        break;
                    case 4:
                        searchStudent(scanner, tree);
                        break;
                    case 5:
                        System.out.println("Student list (In-order Traversal):");
                        tree.inorderTraversal(tree.root);
                        break;
                    case 6:
                        System.out.println("Student list sorted by ID:");
                        tree.sortById();
                        break;
                    case 7:
                        System.out.println("Student list sorted by Rank:");
                        tree.sortByRank();
                        break;
                    case 8:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void addStudent(Scanner scanner, Mytree tree) {
        int id = 0;
        String name = "";
        double score = 0.0;

        do {
            try {
                System.out.print("Enter student ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.err.println("Error: ID must be an integer. Please try again.");
                scanner.nextLine();
            }
        } while (true);

        do {
            try {
                System.out.print("Enter student name: ");
                name = scanner.nextLine();
                if (!name.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Name can only contain letters. Please try again.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.print("Enter student score: ");
                score = scanner.nextDouble();
                if (score < 0 || score > 10) {
                    throw new IllegalArgumentException("Score must be between 0 and 10. Please try again.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: Score must be a number. Please try again.");
                scanner.nextLine();
            }
        } while (true);

        tree.insert(new Student(id, name, score));
        System.out.println("Student added successfully!");
        tree.inorderTraversal(tree.root);
    }

    private static void updateStudent(Scanner scanner, Mytree tree) {
        do {
            try {
                System.out.print("Enter student ID to update: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                if (!newName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Name can only contain letters. Please try again.");
                }
                System.out.print("Enter new score: ");
                double newScore = scanner.nextDouble();
                if (newScore < 0 || newScore > 10) {
                    throw new IllegalArgumentException("Score must be between 0 and 10. Please try again.");
                }
                boolean updated = tree.updateStudent(updateId, newName, newScore);
                if (updated) {
                    System.out.println("Update successful!");
                } else {
                    System.out.println("Student with ID " + updateId + " not found.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: Invalid input. Please try again.");
                scanner.nextLine();
            }
        } while (true);
    }

    private static void deleteStudent(Scanner scanner, Mytree tree) {
        do {
            try {
                System.out.print("Enter student ID to delete: ");
                int deleteId = scanner.nextInt();
                tree.delete(deleteId);
                System.out.println("Student deleted successfully!");
                break;
            } catch (Exception e) {
                System.err.println("Error: Invalid input. Please try again.");
                scanner.nextLine();
            }
        } while (true);
    }

    private static void searchStudent(Scanner scanner, Mytree tree) {
        do {
            try {
                System.out.print("Enter student ID to search: ");
                int searchId = scanner.nextInt();
                Student foundStudent = tree.search(searchId);
                if (foundStudent != null) {
                    System.out.println("Student found: " + foundStudent);
                } else {
                    System.out.println("Student not found.");
                }
                break;
            } catch (Exception e) {
                System.err.println("Error: Invalid input. Please try again.");
                scanner.nextLine();
            }
        } while (true);
    }
}
