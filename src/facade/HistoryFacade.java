package facade;


import entity.Book;
import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HistoryFacade {
    EntityManager em;

    public HistoryFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibPU");
        this.em = emf.createEntityManager();
    }
    public void create(History history){
        em.getTransaction().begin();
            em.persist(history);
        em.getTransaction().commit();
    }
    public void edit(History history){
        em.getTransaction().begin();
            em.merge(history);
        em.getTransaction().commit();
    }
    public History find(Long id){
        return em.find(History.class, id);
    }
    public List<History> findAll(){
        return em.createQuery("SELECT author FROM History history").getResultList();
    }

    public List<History> findHistoryToReadingBooks() {
        try {
            return em.createQuery("SELECT histoty FROM History hisroty WHERE history.returnBook = null").getResultList();
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
}

