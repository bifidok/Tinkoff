package edu.hw3.task6;

import java.util.PriorityQueue;

public class MoscowStockMarket implements StockMarket {
    private final PriorityQueue<Stock> stocks;

    public MoscowStockMarket() {
        stocks = new PriorityQueue<>((o1, o2) -> o2.cost() - o1.cost());
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
