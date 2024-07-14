
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookList {

    private List<Book> t;

    public List<Book> getT() {
        return t;
    }

    public void setT(List<Book> t) {
        this.t = t;
    }

    public BookList() {
        t = new ArrayList<>();
    }

    public boolean isBookCodeExisted(String code) {
        return t.stream().anyMatch((Book book) -> {
            return book.getCode().equalsIgnoreCase(code);
        });
    }

    // 1.Input & add book(s) to the end.
    public void addBook(Book book) {
        t.add(book);
    }

    // 2.Display all books.
    public void displayAllBooks() {
        for (Book book : t) {
            System.out.println(book);
        }
    }

    // 3.Search a book for given code.
    public Book searchBook(String code) {
        for (Book book : t) {
            if (book.getCode().equals(code)) {
                return book;
            }
        }
        return null;
    }

    // 4.Update the book’s price for given code.
    public void updateBookPrice(String code, double newPrice) {
        Book book = searchBook(code);
        if (book != null) {
            book.setPrice(newPrice);
        }
    }

    // 5.Find the (first) max price value.
    public double findMaxPrice() {
        if (t.isEmpty()) {
            return 0;
        }
        return Collections.max(t, Comparator.comparing(Book::getPrice)).getPrice();
    }

    // 6.Sort the list ascendingly by code.(Tang dan)
    public void sortByCode() {
        Collections.sort(t, Comparator.comparing(Book::getCode));
    }

    // 7.Remove the book having given code.
    public void removeBook(String code) {
        t.removeIf(book -> book.getCode().equals(code));
    }

    // 8.Load data from file.
    public void loadFromFile(String fileName) throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            if (reader.readLine() == null) {
                System.out.println("Not have any data");
                return;
            }
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Reading from file completed.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }

    //9 write data to file
    public void loadDataToFile(String fileName) throws FileNotFoundException, IOException {
        if (t.isEmpty()) {
            System.out.println("List empty, must be input data first");
            return;
        }

        try (BufferedWriter write = new BufferedWriter(new FileWriter(fileName))) {
            for (Book book : t) {
                StringBuilder data = new StringBuilder();
                data.append(book.getCode())
                        .append(",")
                        .append(book.getPrice())
                        .append(",")
                        .append(book.getQua())
                        .append(",")
                        .append(book.getTitle());
                write.write(data.toString());
                write.newLine();
            }
            System.out.println("Write from list to file completed.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }

}
