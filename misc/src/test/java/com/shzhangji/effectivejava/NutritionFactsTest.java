package com.shzhangji.effectivejava;

import org.junit.Test;
import static org.junit.Assert.*;

public class NutritionFactsTest {
  @Test
  public void testBuild() {
    var facts = new NutritionFacts.Builder(240, 8)
        .calories(100)
        .sodium(35)
        .carbohydrate(27)
        .build();
    assertEquals(240, facts.getServingSize());
    assertEquals(35, facts.getSodium());
    assertEquals(0, facts.getFat());
  }
}
