package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author XXXX
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void depositAddsOnePercentBonus()
	{
		PromoYouthSavingsAccount a = new PromoYouthSavingsAccount("Y-TEST");
		// 100.00 CHF in mmRp
		long amount1 = 10_000_000;
		// 0.50 CHF in mmRp
		long amount2 = 50_000;

		// After first deposit: +1%
		boolean ok1 = a.deposit(1, amount1);
		assertEquals(true, ok1);
		assertEquals(amount1 + amount1 / 100, a.getBalance()); // 10,100,000

		// After second deposit: +1%
		boolean ok2 = a.deposit(2, amount2);
		assertEquals(true, ok2);
		assertEquals(amount1 + amount1 / 100 + amount2 + amount2 / 100, a.getBalance()); // 10,150,500
	}
}
