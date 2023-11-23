package facade;


import entity.Author;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AuthorFacade {
    EntityManager em;

    public AuthorFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibPU");
        this.em = emf.createEntityManager();
    }
    public void create(Author author){
        em.getTransaction().begin();
            em.persist(author);
        em.getTransaction().commit();
    }
    public void editBook(Author author){
        em.getTransaction().begin();
            em.merge(author);
        em.getTransaction().commit();
    }
    public Author find(Long id){
        return em.find(Author.class, id);
    }
    public List<Author> findAll(){
        return em.createQuery("SELECT author FROM Author author").getResultList();
    }
    
}

