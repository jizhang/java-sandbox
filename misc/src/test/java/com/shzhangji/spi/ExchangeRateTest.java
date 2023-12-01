package com.shzhangji.spi;

import java.nio.file.ProviderNotFoundException;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExchangeRateTest {
  @Test
  public void testProvider() {
    assertFalse(ExchangeRate.providers().isEmpty());
    assertTrue(ExchangeRate.provider() instanceof YahooFinanceExchangeRateProvider);
    assertThrows(ProviderNotFoundException.class, () -> ExchangeRate.provider("N/A"));
  }

  @Test
  public void testManager() {
    QuoteManager manager = ExchangeRate.provider().create();
    assertTrue(manager.getQuotes("CNY", LocalDate.now()).isEmpty());
  }
}
