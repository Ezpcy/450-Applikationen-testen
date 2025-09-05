package ch.schule.bank.junit5;

import ch.schule.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 * Tests f�r die Klasse 'Bank'.
 *
 * @author xxxx
 * @version 1.0
 */
public class BankTests {
  private Bank bank;

  @BeforeEach
  public void setUp() {
    bank = new Bank();
  }

  /**
   * Tests to create new Accounts
   */
  @Test
  public void testCreate() {
    assertNotNull(bank);
  }

  /**
   * Testet das Einzahlen auf ein Konto.
   */
  @Test
  public void testDeposit() {
    assertFalse(bank.deposit("1", 11, 50));
    bank.deposit("P-1000", 1, 1000);
    System.out.println(bank.getBalance("P-1000"));
    System.out.println(bank.getBalance("P-1000"));
    assertEquals(1000, bank.getBalance("P-1000"));
  }

  // /**
  // * Testet das Abheben von einem Konto.
  // */
  // @Test
  // public void testWithdraw() {
  // fail("toDo");
  // }
  //
  // /**
  // * Experimente mit print().
  // */
  // @Test
  // public void testPrint() {
  // fail("toDo");
  // }
  //
  // /**
  // * Experimente mit print(year, month).
  // */
  // @Test
  // public void testMonthlyPrint() {
  // fail("toDo");
  // }
  //
  // /**
  // * Testet den Gesamtkontostand der Bank.
  // */
  // @Test
  // public void testBalance() {
  // fail("toDo");
  // }
  //
  // /**
  // * Tested die Ausgabe der "top 5" konten.
  // */
  // @Test
  // public void testTop5() {
  // fail("toDo");
  // }
  //
  // /**
  // * Tested die Ausgabe der "top 5" konten.
  // */
  // @Test
  // public void testBottom5() {
  // fail("toDo");
  // }

}
