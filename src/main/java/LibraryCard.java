
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student
 */
public class LibraryCard {
    /**
     * Card id
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     * Details about the cardholder
     */
    private Student student;

    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID, double fine, List<Book> borrowed) {
        this.student = student;
        this.IssueDate = IssueDate;
        this.ExpiryDate = ExpiryDate;
        this.ID = ID;
        this.fine = fine;
        this.borrowed = borrowed;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    public List<Book> getBooks() {
        return borrowed;
    }

    /**
     * Issue a new book
     * 
     * @param Book: book to borrow
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book) throws Exception {
        // Check if number of borrowed books is not greater than 4
        if (borrowed.size() >= 4) {
            return false;
        }

        // Check if the same book is already issued on the library card
        if (borrowed.contains(book)) {
            throw new Exception("The book is already issued on the library card.");
        }

        // Check if the library card is still valid
        Date currentDate = new Date();
        if (currentDate.after(ExpiryDate)) {
            throw new IllegalBookIssueException("The library card has expired.");
        }

        // Check if the book is available for borrowing
        if (!book.getStatus()) {
            return false;
        }

        // Check if there is a pending fine associated with the library card
        if (fine > 0) {
            throw new Exception("There is a pending fine associated with the library card.");
        }

        Date dueDate;
        if (book.getDemand() == 0) {
            dueDate = new Date(currentDate.getTime() + (3 * 24 * 60 * 60 * 1000)); // 3 days
        } else {
            dueDate = new Date(currentDate.getTime() + (15 * 24 * 60 * 60 * 1000)); // 15 days
        }

        // Issue the book to the student and update relevant variables
        borrowed.add(book);
        book.setStatus(false);
        book.setDueDay(dueDate);
        return true;
    }
}
