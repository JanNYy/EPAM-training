package ua.epam.pizzaservice.service;

import ua.epam.pizzaservice.domain.AccumulativeCard;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
public interface AccumulativeCardService {

    List<AccumulativeCard> getAllAccumulativeCards();

    Integer saveAccumulativeCard(AccumulativeCard accumulativeCard);

    void deleteAccumulativeCard(Integer id);
}
