package facade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BookFacade {
    EntityManager em;

    public BookFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibPU");
        this.em = emf.createEntityManager();
    }
    public void create(Book book){
        em.getTransaction().begin();
            em.persist(book);
        em.getTransaction().commit();
    }
    public void editBook(Book book){
        em.getTransaction().begin();
            em.merge(book);
        em.getTransaction().commit();
    }
    public Book find(Long id){
        return em.find(Book.class, id);
    }
    public List<Book> findAll(){
        return em.createQuery("SELECT book FROM Book book").getResultList();
    }
    
}
