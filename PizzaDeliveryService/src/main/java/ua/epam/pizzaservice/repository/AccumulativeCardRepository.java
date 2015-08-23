package ua.epam.pizzaservice.repository;

import ua.epam.pizzaservice.domain.AccumulativeCard;

import java.util.List;

/**
 * Created by Anna on 04.08.2015.
 */
public interface AccumulativeCardRepository {

    List<AccumulativeCard> getAllAccumulativeCards();

    AccumulativeCard getAccumulativeCardByID(Integer id);

    Integer saveAccumulativeCard(AccumulativeCard accumulativeCard);

    void deleteAccumulativeCard(Integer id);
}
