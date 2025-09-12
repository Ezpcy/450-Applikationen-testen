package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;



/**
 * Tests f�r die Klasse SavingsAccount.
 *
 * @author Roger H. J&ouml;rg
 * @version 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author XXX
 * @version 1.0
 */
public class SavingsAccountTests
{
	@Test
	public void withdrawWithinBalance()
	{
		SavingsAccount a = new SavingsAccount("S-TEST");
		assertTrue(a.deposit(1, 10_000)); // 0.10 CHF
		assertEquals(10_000, a.getBalance());
		assertTrue(a.withdraw(2, 5_000));
		assertEquals(5_000, a.getBalance());
	}

	@Test
	public void cannotOverdraw()
	{
		SavingsAccount a = new SavingsAccount("S-TEST2");
		assertTrue(a.deposit(1, 1_000));
		assertFalse(a.withdraw(2, 5_000));
		assertEquals(1_000, a.getBalance());
	}
}

