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
    // deposit on non-existing account fails
    assertFalse(bank.deposit("NOPE", 11, 50));

    // create salary account and deposit succeeds
    String id = bank.createSalaryAccount(-100_000);
    assertNotNull(id);
    assertTrue(bank.deposit(id, 1, 10_000));
    assertEquals(10_000, bank.getBalance(id));
  }

  /**
   * Testet das Abheben von einem Konto.
   */
  @Test
  public void testWithdraw() {
    String sId = bank.createSavingsAccount();
    assertTrue(bank.deposit(sId, 1, 5_000));
    assertTrue(bank.withdraw(sId, 2, 3_000));
    assertEquals(2_000, bank.getBalance(sId));
    // cannot overdraw savings account
    assertFalse(bank.withdraw(sId, 3, 3_000));
    assertEquals(2_000, bank.getBalance(sId));
  }
  //
  /**
   * Experimente mit print().
   */
  @Test
  public void testPrint() {
    String id = bank.createSavingsAccount();
    bank.deposit(id, 100, 1_000);
    bank.withdraw(id, 101, 500);
    // ensure no exception
    bank.print(id);
  }
  //
  /**
   * Experimente mit print(year, month).
   */
  @Test
  public void testMonthlyPrint() {
    String id = bank.createSavingsAccount();
    // dates around Sep/Oct 2025 (per AccountTests comment style)
    bank.deposit(id, 20045, 10_000);
    bank.withdraw(id, 20046, 5_000);
    bank.deposit(id, 20047, 20_000);
    bank.withdraw(id, 20048, 15_000);
    // ensure no exception
    bank.print(id, 2025, 9);
  }
  //
  /**
   * Testet Kontostand einzelner Konten via Bank.
   */
  @Test
  public void testBalancePerAccount() {
    String a = bank.createSavingsAccount();
    String b = bank.createSalaryAccount(-1_000_000);
    bank.deposit(a, 1, 10_000);
    bank.deposit(b, 1, 5_000);
    bank.withdraw(b, 2, 6_000); // within limit => -1_000
    assertEquals(10_000, bank.getBalance(a));
    assertEquals(-1_000, bank.getBalance(b));
  }
  //
  /**
   * Tested die Ausgabe der "top 5" konten.
   */
  @Test
  public void testTop5() {
    bank.createSavingsAccount();
    bank.createSavingsAccount();
    bank.deposit("S-1000", 1, 10_000);
    bank.deposit("S-1001", 1, 20_000);
    // ensure no exception
    bank.printTop5();
  }
  //
  /**
   * Tested die Ausgabe der "bottom 5" konten.
   */
  @Test
  public void testBottom5() {
    bank.createSalaryAccount(-100_000);
    bank.createSalaryAccount(-100_000);
    bank.withdraw("P-1000", 1, 10_000); // within limit
    bank.withdraw("P-1001", 1, 5_000);
    // ensure no exception
    bank.printBottom5();
  }

}
