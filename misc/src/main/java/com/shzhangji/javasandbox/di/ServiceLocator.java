package com.shzhangji.javasandbox.di;

public class ServiceLocator {
  private static ServiceLocator instance = new ServiceLocator();

  private MetaService metaService = new MetaService();

  public static MetaService metaService() {
    return instance.metaService;
  }
}
