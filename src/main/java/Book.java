
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Book available in the library
 */
public class Book {

    /**
     * Book ID
     */
    private int ID;

    /**
     * Book title
     */
    private String title;

    /**
     * Authors
     */
    private ArrayList<String> authors = new ArrayList<String>();

    /**
     * Book status: true - Available; false - Not available
     */
    private boolean status = true;

    /**
     * Book demand: 0 - low demand; 1 - high demand
     */
    private int demand;

    /**
     * Return due date of the book
     */
    private Date dueDay;

    public Book(int ID, String title, ArrayList<String> authors, int demand, boolean status, Date dueDay) {
        this.ID = ID;
        this.title = title;
        this.authors = authors;
        this.demand = demand;
        this.status = status;
        this.dueDay = dueDay;
    }

    public int getDemand() {
        return this.demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<String> getAuthors() {
        return this.authors;
    }

    public Date getDueDay() {
        return dueDay;
    }

    public void setDueDay(Date dueDay) {
        this.dueDay = dueDay;
    }

    public boolean getStatus() {
        return status;
    }

}
