package managers;

import entity.Author;
import facade.AuthorFacade;
import java.util.List;
import java.util.Scanner;


public class AuthorManager {
    private final Scanner scanner;
    private final AuthorFacade authorFacade;

    public AuthorManager(Scanner scanner) {
        this.scanner = scanner;
        this.authorFacade = new AuthorFacade();
    }
    
    public void createAuthor(){
        Author author = new Author();
        System.out.println("---- Author creation ----");
        System.out.print("Author name: ");
        author.setFirstname(scanner.nextLine());
        System.out.print("Author last name: ");
        author.setLastname(scanner.nextLine());
        authorFacade.create(author);
    }
    public void printListAuthors(){
        System.out.print("List of authors");
        List<Author> authors = authorFacade.findAll();
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s. %s. %n",
                    authors.get(i).getId(),
                    authors.get(i).getFirstname(),
                    authors.get(i).getLastname()
            );
        }        
    }
    public Author findAuthorById(Long id){
        return authorFacade.find((long)id);
    }
}
