# Software Dokumentation

## Funktionsweise

- Java-basierte Bankkonto-Verwaltung
- Verschiedene Kontotypen: SalaryAccount, SavingsAccount
- Einzahlungen und Abhebungen möglich
- Automatisierte Tests mit JUnit 5
- Fehlererkennung durch Assertions in Tests

## Zusammenhänge

- Account als Basisklasse für Konten
- SalaryAccount und SavingsAccount erben von Account
- Testfälle im Verzeichnis `src/test/java/ch/schule/bank/junit5/`
- Maven für Build und Abhängigkeiten
 - Dokumentation und Hilfe in HELP.md 

## Testfälle

- Überblick: Die Tests liegen unter `src/test/java/ch/schule/bank/junit5/` und verwenden JUnit 5. Beträge sind in Millirappen (mmRp), Datumswerte sind Banktage seit 01.01.1970.
- Ausführen: `mvn test` führt alle Tests headless aus.

- AccountTests: Initialisierung, Ein- und Auszahlung, Referenztypen, Transaktionsreihenfolge (`canTransact`), sowie Ausgabe über `print()` und `print(year, month)`.
- BookingTests: Konstruktion einer Buchung und formatierte Ausgabe einer Buchungszeile inklusive Datum, Betrag und fortgeschriebenem Saldo (Vergleich mit `BankUtils.format...`).
- SavingsAccountTests: Abhebung ist nur bis zum aktuellen Guthaben erlaubt (keine Überziehung), Restguthaben bleibt unverändert bei unzulässiger Abhebung.
- SalaryAccountTests: Kreditlimite (negativer Grenzwert) wird strikt eingehalten; Abhebungen, die die Limite unterschreiten würden, werden abgelehnt.
- PromoYouthSavingsAccountTests: Jede Einzahlung wird mit 1% Bonus in mmRp gutgeschrieben (z. B. 100.00 -> +1.00, 0.50 -> +0.005). Die Tests prüfen die resultierenden Salden in mmRp.
- BankTests: Einzahlungen auf nicht existierende Konten schlagen fehl; nach dem Anlegen eines Lohnkontos sind Einzahlungen erfolgreich, der Kontostand wird korrekt zurückgeliefert.

Hinweis: Die Konsolen-Ausgaben in `AccountTests` dienen als Sichtprüfung des Formats und beeinflussen die Testergebnisse nicht.
