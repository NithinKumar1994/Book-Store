package Library;

/**
 *  Super class book that are inherited by all the other sub classes Paperbackbok , audiobook, electronicbook
 */
public abstract class Book {
    private String author;
    private int cost;
    private Medium medium;
    private String title;
    /**
     * Book Super constructor calling the super class constructor.
     * @param title Title of the book.
     * @param author Name of the Author of the book.
     * @param cost Cost of the book.
     */
    public Book(String title,String author,int cost, Medium medium)
    {
        this.author=author;
        this.title = title;
        this.cost=cost;
        this.medium=medium;
    }
    String getAuthor()
    {
        return this.author;
    }
    double getCost()
    {
        return cost;
    }
    String getMedium()
    {

        return medium.toString();
    }
    String getTitle()
    {
        return this.title;
    }
    abstract boolean isForSale();
    public String toString()
    {
        return "\""+this.title+"\""+"\n\t"+this.getAuthor()+"."+"\n\t"+this.getMedium();

    }

}
