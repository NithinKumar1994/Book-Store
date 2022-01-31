package Library;

/**
 * Class to create HardCover type Books
 */
public class HardcoverBook extends Book {
    private String coverMaterial;

    /**
     * HardcoverBook constructor calling the superclass constructor
     * @param title Title of the Book
     * @param author Author of the Book
     * @param cost cost of the Book
     * @param coverMaterial coverMaterial of the Book
     */
    public HardcoverBook(String title, String author,int cost,String coverMaterial)
    {
        super(title,author,cost,Medium.Hardcover);
        this.coverMaterial=coverMaterial;
    }
    String getMedium()
    {
        return super.getMedium();
    }
    public String toString()
    {
        return super.toString()+" "+ this.coverMaterial+".";
    }

    @Override
    boolean isForSale() {
        return true;
    }
}
