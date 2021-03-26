import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.core.Is.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockMarket market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue(){

        when( market.getPrice("EBAY")).thenReturn(4.0);
        when( market.getPrice("MSFT")).thenReturn(1.5);

        portfolio.addStock( new Stock("EBAY",2));
        portfolio.addStock( new Stock("MSFT",4));
        double result = portfolio.getTotalValue();

        assertThat(14.0, is(result));
        verify(market, times(2)).getPrice(anyString());

    }
}