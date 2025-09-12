package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests der Klasse SalaryAccount.
 *
 * @author XXX
 * @version 1.1
 */
public class SalaryAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void creditLimitEnforced()
	{
		SalaryAccount a = new SalaryAccount("P-TEST", -10_000); // -0.10 CHF limit
		// Withdraw within limit
		assertTrue(a.withdraw(1, 5_000)); // balance -0.05 CHF
		assertEquals(-5_000, a.getBalance());
		// Withdraw exceeding limit
		assertFalse(a.withdraw(2, 6_000)); // would be -0.11 CHF < -0.10 CHF limit
		assertEquals(-5_000, a.getBalance());
		// Deposit increases balance
		assertTrue(a.deposit(3, 20_000));
		assertEquals(15_000, a.getBalance());
	}
}
