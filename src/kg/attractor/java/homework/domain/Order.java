package kg.attractor.java.homework.domain;

import java.util.*;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.*;

public class Order {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private final Customer customer;
    private final List<Item> items;
    private final boolean homeDelivery;
    private transient double total = 0.0d;

    public Order(Customer customer, List<Item> orderedItems, boolean homeDelivery) {
        this.customer = customer;
        this.items = List.copyOf(orderedItems);
        this.homeDelivery = homeDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return homeDelivery == order.homeDelivery &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, homeDelivery);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    public DoubleStream calculateTotal() {
        return items.stream()
                .mapToDouble(e -> e.getPrice() + total);
    }

    public void printListOfOrders() {
        List<String> orders = items.stream()
                .map(Item::getName)
                .collect(toList());
        orders.forEach(System.out::println);
    }

    public List<Item> sortedByMaxPrice() {
        List<Item> maxPrices = items.stream()
                .sorted(Comparator.comparing(Item::getPrice))
                .collect(toList());

        return Arrays.asList(maxPrices.get(maxPrices.size() - 1), maxPrices.get(maxPrices.size() - 2));
    }

    public List<Item> sortedByMinPrice() {
        List<Item> minPrices = items.stream()
                .sorted(Comparator.comparing(Item::getPrice))
                .collect(toList());

        return Arrays.asList(minPrices.get(0), minPrices.get(1));
    }
}
