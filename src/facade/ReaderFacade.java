package facade;


import entity.Reader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ReaderFacade {
    EntityManager em;

    public ReaderFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibPU");
        this.em = emf.createEntityManager();
    }
    public void create(Reader reader){
        em.getTransaction().begin();
            em.persist(reader);
        em.getTransaction().commit();
    }
    public void edit(Reader reader){
        em.getTransaction().begin();
            em.merge(reader);
        em.getTransaction().commit();
    }
    public Reader find(Long id){
        return em.find(Reader.class, id);
    }
    public List<Reader> findAll(){
        return em.createQuery("SELECT author FROM Reader reader").getResultList();
    }
    
}

