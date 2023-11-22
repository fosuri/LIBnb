package managers;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
    /**
     * 1. Save book
     * 2. Save reader
     * 3. Save history
     * 
     * 4. Read book
     * 5. Read reader
     * 6. Read history
     */
    
    EntityManager em;

    public DatabaseManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibPU");
        this.em = emf.createEntityManager();
    }
    
    public void saveBook(Book book){
        /**
         * 1. получаем список авторов из книги
         * 2. если у автора id = null, сохраняем 
         * 3. иначе делаем обновление
         * 4. если ид книги = null, сохраняем книгу
         * 5. иначе делаем обновление
         */
        em.getTransaction().begin();
            for (int i = 0; i < book.getAuthors().size(); i++) {
                if(book.getAuthors().get(i).getId() == null){
                    em.persist(book.getAuthors().get(i));
                }else{
                    em.merge(book.getAuthors().get(i));
                }
            }
            if(book.getId() == null){
                em.persist(book);
            }else{
                em.merge(book);
            }
         em.getTransaction().commit();
    }
    
    public void saveReader(Reader reader){
        em.getTransaction().begin();
            if(reader.getId() == null){
                em.persist(reader);
            }else{
                em.merge(reader);
            }
        em.getTransaction().commit();
    }
    
    public void saveHistory(History history){
        em.getTransaction().begin();
            if(history.getId() == null){
                em.persist(history);
            }else{
                em.merge(history);
            }
        em.getTransaction().commit();
    }
}