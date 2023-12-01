package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import facade.HistoryFacade;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import java.util.Map.Entry;
//import static java.util.Map.entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import tools.InputFromKeyboard;

public class HistoryManager {

    private final Scanner scanner;
    private final ReaderManager readerManager;
    private final BookManager bookManager;
    private final HistoryFacade historyFacade;
   

    public HistoryManager(Scanner scanner) {
        this.scanner = scanner;
        this.readerManager = new ReaderManager(scanner);
        this.bookManager = new BookManager(scanner);
        this.historyFacade = new HistoryFacade();
        
    }

    public void giveBookToReader() {
        System.out.println("------------- Give the book to the reader ----------------");
        History history = new History();
        readerManager.pirntListReaders();
        System.out.print("Enter number reader: ");
        int readerNumber = InputFromKeyboard.inputNumberFromRange(1, null);
        history.setReader(readerManager.getById(readerNumber));
        bookManager.pirntListBooks();
        System.out.print("Enter number book: ");
        int bookId = InputFromKeyboard.inputNumberFromRange(1, null);
        Book book = bookManager.getById(bookId);
        if(book.getCount() > 0){
            history.setBook(bookManager.getById(bookId));
            book.setCount(book.getCount()-1);
            bookManager.update(book);
            history.setGiveBookToReaderDate(new GregorianCalendar().getTime());
            historyFacade.create(history);
        }else{
            System.out.println("All copies of the book are in hand");
        }
    }

    public void returnBook() {
        System.out.println("-------- Return book to library ---------");
        
        if(this.printListReadingBooks()<1){
            System.out.println("The list is empty");
            return;
        }
        System.out.print("Enter number book: ");
        int historyNumber = InputFromKeyboard.inputNumberFromRange(1, null);
        History history = historyFacade.find((long)historyNumber);
        if(history.getBook().getCount() < history.getBook().getQuantity()){
            history.setReturnBook(new GregorianCalendar().getTime());
            history.getBook().setCount(history.getBook().getCount()+1);
            System.out.printf("The book \"%s\" returned%n",history.getBook().getTitle());
        }else{
            System.out.println("All copies of the book in the library"); 
        }
    }

    public int printListReadingBooks() {
        List<History> historiesToReadigBooks = historyFacade.findHistoryToReadingBooks();
        System.out.println("List reading books:");
        for (int i = 0; i < historiesToReadigBooks.size(); i++) {
            System.out.printf("%d. %s. reading %s %s%n",
                    i+1,
                    historiesToReadigBooks.get(i).getBook().getTitle(),
                    historiesToReadigBooks.get(i).getReader().getFirstname(),
                    historiesToReadigBooks.get(i).getReader().getLastname()
            );
        }
        return historiesToReadigBooks.size();
    }
    
}
