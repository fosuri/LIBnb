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
    private final HistoryFacade  historyFacade;

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
        if(book.getCount()> 0){
            history.setBook(bookManager.getById(bookId));
            book.setCount(book.getCount()-1);
            bookManager.update(book);
            history.setGiveBookToReaderDate(new GregorianCalendar().getTime());
            historyFacade.create(history);
            
        }else{
            System.out.println("All books are read");
            
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
            System.out.printf("Book \"%s\" returned%n",history.getBook().getTitle());
        }else{
            System.out.println("All books are already in stock"); 
        }
    }

    public int printListReadingBooks() {
        List<History> historiesToReadingBooks = historyFacade.findHistoryToReadingBooks();
        System.out.println("List reading books:");
        for (int i = 0; i < historiesToReadingBooks.size(); i++) {
                System.out.printf("%d. %s. reading %s %s%n",
                        i+1,
                        historiesToReadingBooks.get(i).getBook().getTitle(),
                        historiesToReadingBooks.get(i).getReader().getFirstname(),
                        historiesToReadingBooks.get(i).getReader().getLastname()
            );
        }
        return historiesToReadingBooks.size();
    }

//    public void printRankingOfBooksBeingRead(List<History> histories) {
//        Map<Book,Integer> mapBooks = new HashMap<>();
//        for (int i = 0; i < histories.size(); i++) {
//            Book book = histories.get(i).getBook();
//            if(mapBooks.containsKey(book)){
//                mapBooks.put(book,mapBooks.get(book) + 1);
//            }else{
//                mapBooks.put(book,1);
//            }
//        }
//        Map<Book, Integer> sortedMap = mapBooks.entrySet()
//            .stream()
//            .sorted(Map.Entry.<Book, Integer>comparingByValue().reversed())
//            .collect(Collectors.toMap(
//                Map.Entry::getKey, 
//                Map.Entry::getValue, 
//                (oldValue, newValue) -> oldValue, 
//                LinkedHashMap::new));
//        System.out.println("Ranking of books being read:");
//        int n=1;
//        for (Map.Entry entry : sortedMap.entrySet()) {
//            System.out.printf("%d. %s: %d%n",
//                    n,
//                    ((Book)entry.getKey()).getTitle(),
//                    entry.getValue()
//            );
//            n++;
//        }
//    }   
}
