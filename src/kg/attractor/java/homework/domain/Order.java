package kg.attractor.java.homework.domain;

import kg.attractor.java.homework.RestaurantOrders;
import kg.attractor.java.homework.util.NotImplementedException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
        int sum = 0;
        DoubleStream result = DoubleStream.of(0);

        while (items.size() > 0) {
            result = items.stream()
                    .mapToDouble(e -> e.getPrice() + sum);
        }

        return result;
    }

    public void printListOfOrders() {
        var orders = items.stream()
                .map(Item::getName)
                .collect(toList());
        orders.forEach(System.out::println);
    }
}
