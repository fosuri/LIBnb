package facade;


import entity.Book;
import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class HistoryFacade extends AbstractFacade<History>{
    EntityManager em;

    public HistoryFacade() {
        super(History.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibraryPU");
        this.em = emf.createEntityManager();
    }


    public List<History> findHistoryToReadingBooks() {
        try {
//            return em.createQuery("SELECT history FROM History history WHERE history.returnBook = null")
//                .getResultList();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<History> criteriaQuery = criteriaBuilder.createQuery(History.class);
            Root<History> historyRoot = criteriaQuery.from(History.class);
            criteriaQuery.select(historyRoot);
            criteriaQuery.where(criteriaBuilder.isNull(historyRoot.get("returnBook")));
            
            List<History> result = em.createQuery(criteriaQuery).getResultList();
        
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }   
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

