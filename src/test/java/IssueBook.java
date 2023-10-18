
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement and test {Programme.addStudent } that respects the condition given
 * the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book
 * from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {
    public LibraryCard libraryCard;
    public Book availableBook;
    public Book unavailableBook;
    public Book highDemandBook;
    public Book lowDemandBook;
    public Book testBook1;
    public Book testBook2;
    public Book testBook3;
    public Book testBook4;

    private int calculateDaysDifference(Date startDate, Date endDate) {
        long difference = endDate.getTime() - startDate.getTime() + 1;
        return (int) (difference / (24 * 60 * 60 * 1000));
    }

    @BeforeEach
    public void setup() {
        Student student = new Student("Chris Park", 3973667);
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + (10 * 24 * 60 * 60 * 1000));
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Author1");
        authors.add("Author2");

        // setting for different types of books
        availableBook = new Book(1, "Available Book", authors, 0, true, currentDate);
        unavailableBook = new Book(2, "Unavailable Book", authors, 0, false, currentDate);
        highDemandBook = new Book(3, "High Demand Book", authors, 1, true, currentDate);
        lowDemandBook = new Book(4, "Low Demand Book", authors, 0, true, currentDate);

        // dummy books to fill borrowedBooks array
        testBook1 = new Book(5, "Test Book 1", authors, 0, true, currentDate);
        testBook2 = new Book(6, "Test Book 2", authors, 0, true, currentDate);
        testBook3 = new Book(7, "Test Book 3", authors, 0, true, currentDate);
        testBook4 = new Book(8, "Test Book 4", authors, 0, true, currentDate);

        List<Book> borrowedBooks = new ArrayList<Book>();
        libraryCard = new LibraryCard(student, currentDate, expiryDate, 123, 0, borrowedBooks);
    }

    @Test
    @DisplayName("Test issuing a book that is available for borrowing")
    public void IssueBook_True_ifAvailableBook() throws Exception {
        assertTrue(libraryCard.issueBook(availableBook));
        assertEquals(1, libraryCard.getBooks().size());
    }

    @Test
    @DisplayName("Test issuing a book the is unavailable for borrowing")
    public void IssueBook_False_ifUnavailableBook() throws Exception {
        assertFalse(libraryCard.issueBook(unavailableBook));
        assertEquals(0, libraryCard.getBooks().size());
    }

    @Test
    @DisplayName("Test issuing a book that is on low demand")
    public void IssueBook_EqualsThree_ifLowDemandBook() throws Exception {
        assertTrue(libraryCard.issueBook(lowDemandBook));
        assertNotNull(lowDemandBook.getDueDay());
        // Check if the due date is set to 3 days for high-demand book
        assertEquals(3, calculateDaysDifference(new Date(), lowDemandBook.getDueDay()));
    }

    @Test
    @DisplayName("Test issuing a book that is on high demand")
    public void IssueBook_EqualsFifteen_ifHighDemandBook() throws Exception {
        assertTrue(libraryCard.issueBook(highDemandBook));
        assertNotNull(highDemandBook.getDueDay());
        // Check if the due date is set to 3 days for high-demand book
        assertEquals(15, calculateDaysDifference(new Date(), highDemandBook.getDueDay()));
    }

    @Test
    @DisplayName("Test issuing a book when the library card has expired")
    public void IssueBook_throwsException_ifExpiredCard() {
        libraryCard.setExpiryDate(new Date(0)); // Set expiry date to a date in the past
        assertThrows(Exception.class, () -> libraryCard.issueBook(availableBook));
    }

    @Test
    @DisplayName("Test issuing a book when the number of borrowed books is already 4")
    public void IssueBook_False_ifExceededBorrowedLimit() throws Exception {
        // Add 4 books to the borrowed list
        libraryCard.getBooks().add(testBook1);
        libraryCard.getBooks().add(testBook2);
        libraryCard.getBooks().add(testBook3);
        libraryCard.getBooks().add(testBook4);

        assertFalse(libraryCard.issueBook(availableBook));
    }

    @Test
    @DisplayName("Test issuing a book when there is a pending fine associated with the library card")
    public void IssueBook_throwsException_ifPendingFine() {
        libraryCard.setFine(50); // Assume there is a pending fine of 50
        assertThrows(Exception.class, () -> libraryCard.issueBook(availableBook));
    }

    @Test
    @DisplayName("Test issuing a book that is already issued on the library card")
    public void IssueBook_throwsException_ifSameBookAlreadyIssued() {
        libraryCard.getBooks().add(availableBook);
        assertThrows(Exception.class, () -> libraryCard.issueBook(availableBook));
    }

}
