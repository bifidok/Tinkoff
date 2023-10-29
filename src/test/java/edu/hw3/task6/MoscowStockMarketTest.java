package edu.hw3.task6;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoscowStockMarketTest {
    @Test
    @DisplayName("Базовый тест")
    public void moscowStockMarket_shouldCorrectlySortStocks() {
        Stock stock1 = new Stock(15449);
        Stock stock2 = new Stock(23);
        Stock stock3 = new Stock(547);
        Stock stock4 = new Stock(142);
        List<Stock> list = List.of(stock1, stock2, stock3, stock4);

        StockMarket stockMarket = new MoscowStockMarket();
        for (Stock stock : list) {
            stockMarket.add(stock);
        }
        Stock mostValuable = stockMarket.mostValuableStock();
        stockMarket.remove(stock1);
        Stock mostValuable1 = stockMarket.mostValuableStock();
        stockMarket.add(new Stock(999));
        Stock mostValuable2 = stockMarket.mostValuableStock();

        assertThat(mostValuable.cost()).isEqualTo(stock1.cost());
        assertThat(mostValuable1.cost()).isEqualTo(stock3.cost());
        assertThat(mostValuable2.cost()).isEqualTo(999);
    }

    @Test
    @DisplayName("Получение самой дорогой акции когда очередь пуста")
    public void moscowStockMarket_shouldReturnNull_WhenNoStocksInQueue() {
        StockMarket stockMarket = new MoscowStockMarket();
        Stock stock1 = new Stock(222);

        Stock mostValuable = stockMarket.mostValuableStock();
        stockMarket.remove(stock1);

        assertThat(mostValuable).isEqualTo(null);
    }
}
