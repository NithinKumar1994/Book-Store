package Library;

/**
 * Class to create Paperback type books
 *
 */
public class PaperbackBook extends Book {

    /**
     * Paperbackbook constructor calling the super calss constructor.
     * @param title Title of the book.
     * @param author Name of the Author of the book.
     * @param cost Cost of the book.
     */
    public PaperbackBook(String title, String author, int cost)
    {
        super(title,author,cost,Medium.Paperback);
    }
    @Override
    boolean isForSale() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+".";
    }

}


