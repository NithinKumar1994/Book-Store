package Library;

import java.io.*;
import java.util.*;
import java.lang.*;
/**
 * Description: Store is used to create books, load inventory , print inventory , search and purcahse books.
 * @author : Prathamesh Punde , Pechetti Nithin
 */
public class Store {

    private String name="Downtown BookStore";
    private List<Book> inventory = new ArrayList();
    private Scanner in ;
    private double rentalPrice;
    private static final char AUTHOR_SEARCH='a';
    private static char[] ccc;
    private static String CODES;
    private static final char MEDIA_SEARCH='m';
    private static final char TITLE_SEARCH='t';
    private ArrayList tempList= new ArrayList();
    private List<Integer> nullList= new ArrayList<>();
    /**
     * Description: Store constructor used to print title and initialise rental Price.
     */
    Store()
    {
        System.out.println("Welcome to " + name);
        this.rentalPrice=3.95;

    }
    /**
     * Used for adding books into Arraylist inventory.
     * @param aBook Passing book object which that is created.
     */
    public void addBook(Book aBook)
    {
        this.inventory.add(aBook);
    }
    /**
     * Desc: this method will load the books , create the particular book object based on the details in file
     * @param filename Filename that is used to load the list of books
     * @throws FileNotFoundException If file is not found
     */

    public void fillInventory(String filename) throws FileNotFoundException {
        File f1= new File(filename);
        in= new Scanner(f1);
        int i=0;
        while(in.hasNext())
        {
            String line = in.nextLine();
            addBook(produceBook(line));

        }
    }
    /**
     *
     * @return 2.0 the cost multiplier
     */
    public double getMarkup()
    {
        return 2.0;
    }
    /**
     * Desc: listMatching function used to match the user inputs to search for the books from inventory.
     * @param parText The substring that is user wanted to search with.
     * @param code  The search code that user selected : author, medium , title.
     * @return List of books that match the user code and the substring input.
     * @throws IOException
     */
    private List<Book> listMatching(String parText, char code) throws IOException {

        tempList.clear();

        if (code==AUTHOR_SEARCH) {

            for (Book book : inventory) {
                if (book.getAuthor().toLowerCase().contains(parText.toLowerCase())) {
                    tempList.add(book);
                }
            }


        }
        else {
            if (code == TITLE_SEARCH) {
                for (Book book : inventory) {
                    if (book.getTitle().toLowerCase().contains(parText.toLowerCase())) {
                        tempList.add(book);
                    }
                }

            } else {
                if (code == MEDIA_SEARCH) {
                    for (Book book : inventory) {
                        if (book.getMedium().toLowerCase().contains(parText.toLowerCase())|| (book.toString().toLowerCase().contains(parText)&& ("Hardcoverleather".toLowerCase()).contains(parText.toLowerCase()))||(book.toString().toLowerCase().contains(parText)&& ("Hardcovercloth".toLowerCase()).contains(parText.toLowerCase()))) {
                            tempList.add(book);
                        }
                    }

                }
            }
        }

        return tempList;
    }
    /**
     *  Description: Main method
     * @param args Passing the file path from command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            if(args[0].equals(""))
                throw new FileNotFoundException();
            Store str = new Store();
            str.fillInventory(args[0]);
            str.printInventory();
            str.offerSearch();
        }catch (Exception es)
        {
            System.out.println("Usage: java Store <filename.txt>");
        }
    }
    /**
     * Description: Function used for the purchase of book.
     * @param bookList List of books that is returned by listmatching function based on user input.
     */
    private void offerPurchase(List<Book> bookList)
    {

        System.out.println("Which would you like to buy(ENTER to quit):");
        in = new Scanner(System.in);
        String selection = in.nextLine();
        if (selection.equals(""))
            return;
        if(Integer.parseInt(selection)<=bookList.size()&&Integer.parseInt(selection)>0) {
            if (!bookList.get(Integer.parseInt(selection) - 1).isForSale()) {
                System.out.println("Sorry \"" + bookList.get(Integer.parseInt(selection) - 1).getTitle() + "\" is not for Sale.");
                System.out.println("The rental price is " + rentalPrice + " per week.\n");
            } else
                System.out.println("The price of \"" + bookList.get(Integer.parseInt(selection) - 1).getTitle() + "\" is $" + bookList.get(Integer.parseInt(selection) - 1).getCost()*this.getMarkup() / 100 + "\n");
        }

        else{
            System.out.println("Invalid Input, please enter correct Input \n");
            offerPurchase(bookList);
        }
    }
    /**
     * Description: Take user input : t(title), a(author), m(medium) and the type of substring to search.
     * @throws IOException
     */
    void offerSearch() throws IOException {

        while (true) {
            System.out.println("Enter one of these one-letter codes: t(title), a(author), m(medium),and \n" +
                    "a portion of the desired text. Or enter a blank line to quit.(Note: medium is paper, hard, audio, or electronic).\n" +
                    "How would you like to search?");

            in= new Scanner(System.in);
            String CODES= in.nextLine();

            if (CODES.equals("")) {
                System.out.println("Search again any time!");
                return;
            }
            else {
                try {
                    String[] subString = CODES.split("\\s+");
                    int i = 0;
                    if(subString[0].toLowerCase().charAt(0)!=AUTHOR_SEARCH && subString[0].toLowerCase().charAt(0)!=MEDIA_SEARCH&&subString[0].toLowerCase().charAt(0)!=TITLE_SEARCH) {
                        System.out.println("Enter correct option");
                        continue;
                    }
                    if (listMatching(subString[1].toLowerCase(), subString[0].toLowerCase().charAt(0)).size() != 0) {
                        for (Book tempBook : listMatching(subString[1].toLowerCase(), subString[0].toLowerCase().charAt(0))) {
                            i++;
                            System.out.println(i + ". " + tempBook.toString() + " $" + tempBook.getCost() / 100);
                        }
                        offerPurchase(tempList);
                    }
                    else
                        System.out.println("No books found\n");
                }catch (Exception es)
                {
                    System.out.println("Enter proper option");
                }

            }

        }
    }
    /**
     * Desc: Used to print the inventory after use
     */
    void printInventory()
    {
        for (int i = 0,k=1; i < inventory.size(); i++,k++) {
            try {
                System.out.println((k) + "." + inventory.get(i).toString());
            } catch (Exception e)
            {
                System.out.println("Invalid format....Skipping entry..");
                inventory.remove(i);
                i--;
                k--;

            }
        }
    }
    /**
     * Desc: This method will create all the book objects based on the type of book.
     * @param line Each line form the file that is being passed from the command line arguemnts
     * @return Book object which is used to add object to an arraylist.
     */

    public Book produceBook(String line)
    {
        String[] arr= line.split("_");
        if(arr.length==3 && Character.isDigit(arr[2].toCharArray()[0]) )
        {
            PaperbackBook pbnew= new PaperbackBook(arr[0],arr[1],Integer.parseInt(arr[2]));
            return  pbnew;
        }
        else
        {
            if(line.contains("http://")&& Character.isDigit(arr[2].toCharArray()[0])&& arr.length<5)
            {
                return new ElectronicBook(arr[0],arr[1],Integer.parseInt(arr[2]),arr[3]);
            }
            else{
                if(line.contains("leather")&& Character.isDigit(arr[2].toCharArray()[0])&& arr.length<5||line.contains("cloth")&& Character.isDigit(arr[2].toCharArray()[0])&& arr.length<5)
                {
                    return new HardcoverBook(arr[0],arr[1],Integer.parseInt(arr[2]),arr[3]);
                }
                else
                {
                    if(arr.length==4 && Character.isDigit(arr[2].toCharArray()[0])&&Character.isDigit(arr[3].toCharArray()[0]))
                    {
                        return new AudioBook(arr[0],arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
                    }

                    else
                        return null;
                }
            }
        }


    }
}
