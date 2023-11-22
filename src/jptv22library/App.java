package jptv22library;

import entity.History;
import managers.HistoryManager;
import entity.Book;
import entity.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import managers.BookManager;
import managers.ReaderManager;
import java.util.Scanner;
import managers.DatabaseManager;
import managers.SaveManager;
import tools.InputFromKeyboard;

public class App {
    private final Scanner scanner;
    private List<Book> books;
    private List<Reader> readers;
    private List<History> histories;
    private final BookManager bookManager;
    private final ReaderManager readerManager;
    private HistoryManager historyManager;
    private SaveManager saveManager;
    private final DatabaseManager databaseManager;
    
    public App() {
        this.scanner = new Scanner(System.in);
        this.saveManager = new SaveManager();
        this.databaseManager = new DatabaseManager();
        this.books = saveManager.loadBooks();
        this.readers = saveManager.loadReaders();
        this.histories = saveManager.loadHistories();
        this.bookManager = new BookManager(scanner);
        this.readerManager = new ReaderManager(scanner);
        this.historyManager = new HistoryManager(scanner);
    }

    void run() {
        boolean repeat = true;
        System.out.println("------ Library ------");
        do{
            System.out.println("List tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Add new book");
            System.out.println("2. Add new reader");
            System.out.println("3. Print list books");
            System.out.println("4. Print list readers");
            System.out.println("5. Give the book to the reader");
            System.out.println("6. Return book");
            System.out.println("7. Print list readed books");
            System.out.println("8. Add a copy of an existing book in library");
            System.out.println("9. Ranking of books being read");
            System.out.println("10. Most Reading Reader");
            System.out.print("Enter number task: ");
            int task = InputFromKeyboard.inputNumberFromRange(0,9);
            System.out.println("Selected task is "+task+". Are you sure? Y/N");
            String continueRun = InputFromKeyboard.inputSympolYesOrNO();
            if(continueRun.equals("n")){
                continue;
            }
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
//                    books.add(bookManager.addBook());
//                    saveManager.saveBooks(this.books);//save to file
                    databaseManager.saveBook(bookManager.addBook()); // save to base
                    break;
                case 2:
//                    readers.add(readerManager.addReader());
//                    saveManager.saveReaders(readers);
                    databaseManager.saveReader(readerManager.addReader());
                    break;
                case 3:
                    bookManager.pirntListBooks(books);
                    break;
                case 4:
                    readerManager.pirntListReaders(readers);
                    break;
                case 5:
                    History history = historyManager.giveBookToReader(readers, books);
                    if(history != null){
                        this.histories.add(history);
                        saveManager.saveHistories(histories);
                    }
                    break;
                case 6:
                    historyManager.returnBook(histories);
                    saveManager.saveHistories(histories);
                    break;
                case 7:
                    historyManager.printListReadingBooks(histories);
                    break;
                case 8:
                    bookManager.addCopyOfExistingBookInLibrary(books);
                    saveManager.saveBooks(books);
                    break;
                case 9:
                    //System.out.println("Implementation expected");
//                    historyManager.printRankingOfBooksBeingRead(this.histories);
                    break;
                case 10:
                    System.out.println("Implementation expected");
                    break;
                default:
                    System.out.println("Select number from list tasks!");
            }
            System.out.println("-------------------------");
        }while(repeat);
    }

    
}