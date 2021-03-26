import java.util.LinkedList;

public class StocksPortfolio {

    private String name;
    private IStockMarket market;
    private LinkedList<Stock> stocks;

    StocksPortfolio(IStockMarket market){
        this.market = market;
        stocks = new LinkedList<Stock>();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public IStockMarket getMarketService(){
        return this.market;
    }

    public void setMarketService(IStockMarket stockMarket){
        this.market = market;
    }

    public Double getTotalValue(){
        double total = 0;
        for(Stock stock : stocks){
            total+= stock.getQuantity() * market.getPrice(stock.getName());
        }
        return total;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }


}
