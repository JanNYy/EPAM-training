package ua.epam.pizzaservice.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 03.08.2015.
 */
public class TotalOrderCostCalculator {

    public final int minNumberOfPizzas = 1;
    public final int maxNumberOfPizzas = 20;

    public final int numberOfPizzasForDiscount = 4;
    public final double discountSize = 0.3d;

    private Map<Double, Double> discountPercents;

    public TotalOrderCostCalculator() {
        discountPercents = new HashMap<Double, Double>();
        discountPercents.put(1000d, 0.02d);
        discountPercents.put(5000d, 0.05d);
        discountPercents.put(10000d, 0.1d);
        discountPercents.put(20000d, 0.15d);
        discountPercents.put(50000d, 0.2d);
    }

    public double calculateTotalOrderPrice(Map<Pizza,Integer> pizzas) {
        if ((pizzas == null) || (pizzas.size() < minNumberOfPizzas))
            throw new IllegalArgumentException("Order is invalid");

        double orderPrice = 0.0;
        int numberOfAllPizzas = 0;

        double maxPriceOfPizza = 0.0d;

        for(Map.Entry<Pizza,Integer> p : pizzas.entrySet()) {

            if ((p.getKey() == null) || (p.getValue() == null))
                throw new IllegalArgumentException("Pizza is invalid");

            if (p.getValue() <= 0)
                throw new IllegalArgumentException(
                        "Number of pizzas is equal or less than zero");

            numberOfAllPizzas += p.getValue();
            if (numberOfAllPizzas > maxNumberOfPizzas)
                throw new IllegalArgumentException(
                        "Number of pizzas is greater than courier's carrying");

            if ((p.getKey().getPrice() == null) || (p.getKey().getPrice() <= 0))
                throw new IllegalArgumentException(
                        "Price of pizza is equal or less than zero");

            if (p.getKey().getPrice() > maxPriceOfPizza)
                maxPriceOfPizza = p.getKey().getPrice();

            orderPrice += p.getKey().getPrice() * p.getValue();
        }

        return  numberOfAllPizzas >= numberOfPizzasForDiscount
                ? orderPrice-maxPriceOfPizza*discountSize
                : orderPrice;
    }

    public double calculateTotalOrderPriceWithAccumulativeCardSum(
            Map<Pizza,Integer> pizzas,
            Double cardSum) {
        if ((cardSum == null) || (cardSum < 0))
            throw new IllegalArgumentException("Accumulative card is invalid");

        double orderPrice = calculateTotalOrderPrice(pizzas);
        double discount = 0.0d;
        for (Map.Entry<Double, Double> d : discountPercents.entrySet())
        {
            if ((cardSum >= d.getKey()) && (d.getValue() > discount))
                discount = d.getValue();
        }
        return orderPrice*(1-discount);
    }
}
