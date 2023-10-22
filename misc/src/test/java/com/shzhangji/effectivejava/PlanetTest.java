package com.shzhangji.effectivejava;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlanetTest {
  @Test
  public void testSurfaceWeight() {
    double earthWeight = 185;
    double mass = earthWeight / Planet.EARTH.surfaceGravity();
    assertEquals(69.912739, Planet.MERCURY.surfaceWeight(mass), 1e-6);
    assertEquals(167.434436, Planet.VENUS.surfaceWeight(mass), 1e-6);
    assertEquals(185.0, Planet.EARTH.surfaceWeight(mass), 1e-6);
    assertEquals(70.226739, Planet.MARS.surfaceWeight(mass), 1e-6);
    assertEquals(467.990696, Planet.JUPITER.surfaceWeight(mass), 1e-6);
    assertEquals(197.120111, Planet.SATURN.surfaceWeight(mass), 1e-6);
    assertEquals(167.398264, Planet.URANUS.surfaceWeight(mass), 1e-6);
    assertEquals(210.208751, Planet.NEPTUNE.surfaceWeight(mass), 1e-6);
  }
}
