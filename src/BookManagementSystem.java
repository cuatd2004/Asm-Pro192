
import constant.IConst;
import java.io.IOException;
import java.util.Scanner;

public class BookManagementSystem {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BookList bookList = new BookList();
        while (true) {
            System.out.println("\nBook Management System");
            System.out.println("1. Input & add book(s) to the end");
            System.out.println("2. Display all books");
            System.out.println("3. Search a book for given code");
            System.out.println("4. Update the book's price for given code");
            System.out.println("5. Find the (first) max price value");
            System.out.println("6. Sort the list ascendingly by code");
            System.out.println("7. Remove the book having given code");
            System.out.println("8. Read data from file");
            System.out.println("9. Load data from list to file");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter book code: ");
                    String code = scanner.nextLine();

                    //check code o trong list da ton tai
                    boolean check = bookList.isBookCodeExisted(code);
                    if (check) {
                        System.out.println("Code has existed");
                        break;
                    }
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qua = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    bookList.addBook(new Book(code, title, qua, price));
                    break;
                case 2:
                    bookList.displayAllBooks();
                    break;
                case 3:
                    System.out.print("Enter book code to search: ");
                    code = scanner.nextLine();
                    Book foundBook = bookList.searchBook(code);
                    if (foundBook != null) {
                        System.out.println(foundBook);
                    } else {
                        System.out.println("Book not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter book code to update price: ");
                    code = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    price = scanner.nextDouble();
                    bookList.updateBookPrice(code, price);
                    break;
                case 5:
                    System.out.println("Max price: " + bookList.findMaxPrice());
                    break;
                case 6:
                    bookList.sortByCode();
                    System.out.println("Books sorted by code");
                    break;
                case 7:
                    System.out.print("Enter book code to remove: ");
                    code = scanner.nextLine();
                    bookList.removeBook(code);
                    break;
                case 8:
                    bookList.loadFromFile(IConst.FILE_NAME);
                    break;
                case 9:
                    bookList.loadDataToFile(IConst.FILE_NAME);
                    break;
                case 0:
                    System.out.println("Exiting program. Byeeee!!!!!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

}
