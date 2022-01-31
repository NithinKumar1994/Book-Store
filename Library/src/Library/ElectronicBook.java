package Library;

/**
 * Class to create Electronic type book object.
 */
public class ElectronicBook extends Book {
    private String theURL;
    /**
     * Electronic book constructor calling the super calls constructor.
     * @param title Title of the book.
     * @param author Name of the Author of the book.
     * @param cost Cost of the book.
     */
    public ElectronicBook(String title, String author, int cost,String theURL)
    {
        super(title,author,cost,Medium.Electronic);
        this.theURL=theURL;
    }
    @Override
    boolean isForSale() {
        return false;
    }

    @Override
    String getMedium() {
        return super.getMedium()+" from ";
    }

    @Override
    public String toString() {
        return super.toString()+theURL+".";
    }
}
