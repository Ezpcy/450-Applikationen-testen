package ch.schule.bank.junit5;

import ch.schule.Booking;
import ch.schule.BankUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests für die Klasse Booking.
 *
 * @author Luigi Cavuoti
 * @version 1.1
 */
public class BookingTests
{
	/**
	 * Tests f�r die Erzeugung von Buchungen.
	 */
	@Test
	public void testInitialization()
	{
		Booking b = new Booking(11, 1000);
		assertEquals(11, b.getDate());
		assertEquals(1000, b.getAmount());
	}

	/**
	 * Experimente mit print().
	 */
	@Test
	public void testPrint()
	{
		// Arrange
		int date = 0; // 01.01.1970
		long amount = 100000; // 1.00 CHF in mmRp
		long startingBalance = 500000; // 5.00 CHF in mmRp
		Booking b = new Booking(date, amount);

		// Capture System.out
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream oldOut = System.out;
		System.setOut(new PrintStream(baos));
		try {
			b.print(startingBalance);
		} finally {
			System.setOut(oldOut);
		}

		String expected = BankUtils.formatBankDate(date)
			+ " " + BankUtils.formatAmount(amount)
			+ " " + BankUtils.formatAmount(startingBalance + amount) + System.lineSeparator();

		assertEquals(expected, baos.toString());
	}
}
