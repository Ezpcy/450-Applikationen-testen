package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.SalaryAccount;
import ch.schule.SavingsAccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Account.
 *
 * @author xxxx
 * @version 1.0
 */
public class AccountTests {
  /**
   * Tested die Initialisierung eines Kontos.
   */
  @Test
  public void testInit() {
    Account account = new Account("A1") {
    };
    assertEquals("A1", account.getId());
    assertEquals(0L, account.getBalance());
  }

  /**
   * Testet das Einzahlen auf ein Konto.
   */
  @Test
  public void testDeposit() {
    Account account = new Account("A2") {
    };
    boolean ok = account.deposit(20250905, 10000L);
    assertTrue(ok);
    assertEquals(10000L, account.getBalance());
  }

  /**
   * Testet das Abheben von einem Konto.
   */
  @Test
  public void testWithdraw() {
    Account account = new Account("A3") {
    };
    account.deposit(20250905, 10000L);
    boolean ok = account.withdraw(20250906, 5000L);
    assertTrue(ok);
    assertEquals(5000L, account.getBalance());
  }

  /**
   * Tests the reference from SavingsAccount
   */
  @Test
  public void testReferences() {
    Account account = new SavingsAccount("A4");
    assertTrue(true);
    assertFalse(account instanceof SalaryAccount);
  }

  /**
   * teste the canTransact Flag
   */
  @Test
  public void testCanTransact() {
    Account account = new Account("A5") {
    };
    Account account2 = new Account("A6") {
    };
    assertTrue(account.canTransact(20250905));
  }

  /**
   * Experimente mit print().
   */
  @Test
  public void testPrint() {
    Account account = new Account("A7") {
    };
    account.deposit(20045, 10000000L); // 100.00 am 06.09.2025
    account.withdraw(20046, 5000L); // 0.05 am 07.09.2025
    account.deposit(20047, 20000L);
    account.withdraw(20048, 15000L);
    account.print();
  }

  /**
   * Experimente mit print(year,month).
   */
  @Test
  public void testMonthlyPrint() {
    Account account = new Account("A8") {
    };
    account.deposit(20045, 10000000L); // 100.00 am 05.09.2025
    account.withdraw(20046, 5000L); // 0.05 am 06.09.2025
    account.deposit(20047, 20000L); // 200.00 am 07.10.2025
    account.withdraw(20048, 15000L); // 150.00 am 08.10.2025
    account.print(2025, 9);
  }

}
