package ua.epam.pizzaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.pizzaservice.domain.AccumulativeCard;
import ua.epam.pizzaservice.repository.AccumulativeCardRepository;
import ua.epam.pizzaservice.service.AccumulativeCardService;

import java.util.List;

/**
 * Created by Anna on 20.08.2015.
 */
@Service("accumulativeCardService")
public class AccumulativeCardServiceImpl implements AccumulativeCardService {

    @Autowired
    AccumulativeCardRepository accumulativeCardRepository;

    @Override
    public List<AccumulativeCard> getAllAccumulativeCards() {
        return accumulativeCardRepository.getAllAccumulativeCards();
    }

    @Override
    public Integer saveAccumulativeCard(AccumulativeCard accumulativeCard) {
        return accumulativeCardRepository.saveAccumulativeCard(accumulativeCard);
    }

    @Override
    public void deleteAccumulativeCard(Integer id) {
        accumulativeCardRepository.deleteAccumulativeCard(id);
    }
}
