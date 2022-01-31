package Library;

/**
 * Audiobook class to create medium type audio.
 */
public class AudioBook extends Book {
    private int numDiscs;
    /**
     * Paperbackbook constructor calling the super calss constructor.
     * @param title Title of the book.
     * @param author Name of the Author of the book.
     * @param cost Cost of the book.
     */
    public AudioBook(String title,String author, int cost, int numDiscs)
    {
        super(title,author,cost,Medium.Audio);
        this.numDiscs=numDiscs;
    }


    @Override
    boolean isForSale() {
        return false;
    }
    String getMedium()
    {
        return super.getMedium();

    }
    @Override
    public String toString()
    {
        return super.toString()+": "+this.numDiscs+" discs.";
    }

}
