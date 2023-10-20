package edu.hw3.task6;

import java.util.Objects;

public record Stock(int cost) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return cost == stock.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }
}
