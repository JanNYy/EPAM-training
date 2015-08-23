package ua.epam.pizzaservice.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Anna on 03.08.2015.
 */
public class TotalOrderCostCalculatorTest {

    private TotalOrderCostCalculator calculator = new TotalOrderCostCalculator();

    private final double DELTA = 0.01;

    Map<Pizza,Integer> pizzas;

    @Mock
    private AccumulativeCard accumulativeCard;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_PizzasListIsEmpty_ThrowException() throws Exception {
        pizzas = new HashMap<>();

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_PizzasListIsNull_ThrowException() throws Exception {
        pizzas = null;

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_OnePizzaIsNull_ThrowException() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 50.3, PizzaType.Sea), 1);
        pizzas.put(null,1);

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_NumberOfPizzasIsNull_ThrowException() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza1", 50.3, PizzaType.Sea), 1);
        pizzas.put(new Pizza("TestPizza2", 100.5, PizzaType.Meat), null);

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_NumberOfPizzasIsLessZero_ThrowException() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 50.3, PizzaType.Sea), -2);

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
     public void testCalculateTotalOrderPrice_NumberOfPizzasIsGreaterMax_ThrowException() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 50.3, PizzaType.Sea), calculator.maxNumberOfPizzas+5);

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_PriceOfOnePizzaIsNull_ThrowException() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", null, PizzaType.Sea), 1);

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPrice_PriceOfOnePizzaIsLessZero_ThrowException() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", -10.0, PizzaType.Sea), 1);

        calculator.calculateTotalOrderPrice(pizzas);
    }

    @Test
    public void testCalculateTotalOrderPrice_PriceOfOnePizza() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 50.3, PizzaType.Sea), 1);
        double expectedPrice = 50.3;

        double price = calculator.calculateTotalOrderPrice(pizzas);
        assertEquals(expectedPrice, price, DELTA);
    }

    @Test
    public void testCalculateTotalOrderPrice_PriceOfSeveralPizzas() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza1", 100.1, PizzaType.Sea), 1);
        pizzas.put(new Pizza("TestPizza2", 200.2, PizzaType.Meat), 1);
        pizzas.put(new Pizza("TestPizza3", 300.3, PizzaType.Vegetarian), 1);
        double expectedPrice = 600.6;

        double price = calculator.calculateTotalOrderPrice(pizzas);
        assertEquals(expectedPrice, price, DELTA);
    }

    @Test
    public void testCalculateTotalOrderPrice_PriceOfSeveralPizzasWithDiscount() throws Exception {
        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza1", 100.1, PizzaType.Sea), 2);
        pizzas.put(new Pizza("TestPizza2", 200.2, PizzaType.Meat), 2);
        pizzas.put(new Pizza("TestPizza3", 300.3, PizzaType.Vegetarian), 1);
        double expectedPrice = 300.3*2 + 210.21;

        double price = calculator.calculateTotalOrderPrice(pizzas);
        assertEquals(expectedPrice, price, DELTA);
    }

    // Mockito tests

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPriceWithAccumulativeCardSum_SumIsNull_ThrowException() {
        when(accumulativeCard.getSum()).thenReturn(null);

        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza1", 100.0, PizzaType.Meat), 1);
        Double cardSum = accumulativeCard.getSum();

        calculator.calculateTotalOrderPriceWithAccumulativeCardSum(pizzas, cardSum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalOrderPriceWithAccumulativeCardSum_SumIsLessZero_ThrowException() {
        when(accumulativeCard.getSum()).thenReturn(-100.0);

        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 100.0, PizzaType.Meat), 1);
        Double cardSum = accumulativeCard.getSum();

        calculator.calculateTotalOrderPriceWithAccumulativeCardSum(pizzas, cardSum);
    }

    @Test
    public void testCalculateTotalOrderPriceWithAccumulativeCardSum_SumIsZero() {
        when(accumulativeCard.getSum()).thenReturn(0.0);

        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 200.0, PizzaType.Meat), 1);
        Double cardSum = accumulativeCard.getSum();

        double expectedPrice = 200.0;

        double price = calculator.calculateTotalOrderPriceWithAccumulativeCardSum(pizzas, cardSum);
        assertEquals(expectedPrice, price, DELTA);
    }

    @Test
    public void testCalculateTotalOrderPriceWithAccumulativeCardSum_PriceWithDiscount(){
        when(accumulativeCard.getSum()).thenReturn(1000.0);

        pizzas = new HashMap<>();
        pizzas.put(new Pizza("TestPizza", 200.0, PizzaType.Meat), 1);
        Double cardSum = accumulativeCard.getSum();

        double expectedPrice = 196.0;

        double price = calculator.calculateTotalOrderPriceWithAccumulativeCardSum(pizzas, cardSum);
        assertEquals(expectedPrice, price, DELTA);
    }

}