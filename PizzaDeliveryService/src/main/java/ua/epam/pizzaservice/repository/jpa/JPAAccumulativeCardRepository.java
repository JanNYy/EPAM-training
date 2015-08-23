package ua.epam.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.pizzaservice.domain.AccumulativeCard;
import ua.epam.pizzaservice.repository.AccumulativeCardRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Repository("accumulativeCard")
public class JPAAccumulativeCardRepository implements AccumulativeCardRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public List<AccumulativeCard> getAllAccumulativeCards() {
        TypedQuery<AccumulativeCard> query =
                em.createQuery("select a from AccumulativeCard a", AccumulativeCard.class);
        return query.getResultList();
    }

    @Override
    public AccumulativeCard getAccumulativeCardByID(Integer id) {
        return em.find(AccumulativeCard.class, id);
    }

    @Override
    @Transactional
    public Integer saveAccumulativeCard(AccumulativeCard accumulativeCard) {
        if (accumulativeCard == null)
            return null;

        if (accumulativeCard.getId() == null)
            em.persist(accumulativeCard);
        else
            em.merge(accumulativeCard);
        return accumulativeCard.getId();
    }

    @Override
    @Transactional
    public void deleteAccumulativeCard(Integer id) {
        if (id == null)
            return;

        AccumulativeCard accumulativeCard = getAccumulativeCardByID(id);

        if (accumulativeCard != null)
            em.remove(accumulativeCard);
    }
}
