package com.shzhangji.spi;

public class YahooFinanceExchangeRateProvider implements ExchangeRateProvider {
  @Override
  public QuoteManager create() {
    return new YahooQuoteManagerImpl();
  }
}
