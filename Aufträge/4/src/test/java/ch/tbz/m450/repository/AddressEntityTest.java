// src/test/java/ch/tbz/m450/repository/AddressEntityTest.java
package ch.tbz.m450.repository;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressEntityTest {

  private Date now;

  @BeforeEach
  void setup() {
    now = new Date();
  }

  @Test
  void allArgsCtor_setsFields() {
    Address a = new Address(5, "Eva", "Zingg", "077", now);
    assertAll(
        () -> assertEquals(5, a.getId()),
        () -> assertEquals("Eva", a.getFirstname()),
        () -> assertEquals("Zingg", a.getLastname()),
        () -> assertEquals("077", a.getPhonenumber()),
        () -> assertEquals(now, a.getRegistrationDate()));
  }

  @Test
  void noArgsCtor_allowsSetters() {
    Address a = new Address();
    a.setId(6);
    a.setFirstname("Max");
    a.setLastname("Mustermann");
    a.setPhonenumber("076");
    a.setRegistrationDate(now);

    assertEquals(6, a.getId());
    assertEquals("Max", a.getFirstname());
    assertEquals("Mustermann", a.getLastname());
    assertEquals("076", a.getPhonenumber());
    assertEquals(now, a.getRegistrationDate());
  }
}
