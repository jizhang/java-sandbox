package com.shzhangji.spi;

import java.time.LocalDate;
import java.util.List;

public class YahooQuoteManagerImpl implements QuoteManager {
  @Override
  public List<Quote> getQuotes(String baseCurrency, LocalDate date) {
    return List.of();
  }
}
