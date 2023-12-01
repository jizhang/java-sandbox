package com.shzhangji.spi;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class ExchangeRate {
  private static final String DEFAULT_PROVIDER = "com.shzhangji.spi.YahooFinanceExchangeRateProvider";

  public static List<ExchangeRateProvider> providers() {
    var services = new ArrayList<ExchangeRateProvider>();
    var loader = ServiceLoader.load(ExchangeRateProvider.class);
    loader.forEach(services::add);
    return services;
  }

  public static ExchangeRateProvider provider() {
    return provider(DEFAULT_PROVIDER);
  }

  public static ExchangeRateProvider provider(String providerName) {
    var loader = ServiceLoader.load(ExchangeRateProvider.class);
    for (var provider : loader) {
      if (providerName.equals(provider.getClass().getName())) {
        return provider;
      }
    }
    throw new ProviderNotFoundException("Exchange Rate provider " + providerName + " not found");
  }
}
