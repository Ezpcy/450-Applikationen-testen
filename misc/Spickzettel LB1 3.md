### Fehler vs Mangel
- **Fehler**: Abweichung zwischen IST und SOLL-Verhalten (Anforderung nicht erfüllt)
- **Mangel**: Berechtigte Erwartung nicht angemessen erfüllt (z.B. korrekte Berechnung, aber falsche Darstellung)
### Fehlermaskierung
- Fehler werden durch andere Programmteile kompensiert/verdeckt
- Treten erst auf, wenn andere Teile korrigiert werden
- Können in Abhängigkeiten entstehen
### Kriterien für gute Testfälle
- **Hohe Fehlerwahrscheinlichkeit** - Tests, die wahrscheinlich Fehler finden
- **Keine Redundanz** - Nicht dasselbe mehrfach testen
- **Unabhängigkeit** - Tests beeinflussen sich nicht gegenseitig
- **Hohe Codeabdeckung** - Möglichst viel Code testen
### Testing in Vorgehensmodellen
**V-Modell:**
- Linker Ast: Entwicklung (Anforderungen → Programmierung)
- Rechter Ast: Testing (Komponententest → Abnahmetest)
- Testing parallel zur Entwicklung
**SCRUM:**
- Iteratives Testen nach jedem Sprint
- Automatisierte Tests wichtig wegen wachsender Funktionalität
- Wiederverwendbare Tests für jedes Increment
### Testabdeckung
- Vollständiges Testen unmöglich (kombinatorische Explosion)
- Testaufwand muss im Verhältnis zum Risiko stehen
- Tools helfen bei Messung der Code Coverage
## 2. TESTSTRATEGIE
- **Smoke Testing** → minimale Tests, um sicherzustellen, dass die Software überhaupt lauffähig ist („funktioniert das Grundlegende?“).
- **Regression Testing** → gezielte Wiederholung von Tests nach Änderungen, um sicherzustellen, dass alte Funktionen nicht kaputt gegangen sin
### Testobjekte
- Komponenten/Teile der Applikation die getestet werden
- Beispiele: Klassen, Module, DB-Skripte, Kommandozeilenskripte
### Funktionale vs Nicht-funktionale Tests
- **Funktional**: WAS die Software tut (Anforderungen erfüllt?)
- **Nicht-funktional**: WIE sie es tut (Performance, Sicherheit, Usability)
### Abstrakte vs Konkrete Tests
- **Abstrakt**: Logische Operatoren (größer/kleiner), keine konkreten Werte
- **Konkret**: Spezifische Ein-/Ausgabewerte
### BlackBox vs WhiteBox Testing
- **WhiteBox**: Code sichtbar, interne Struktur bekannt, von Entwicklern
- **BlackBox**: Code nicht sichtbar, nur externes Verhalten getestet
## 3. TESTLEVELS
### Hierarchie (von klein nach groß):
**1. Unit Testing**
- Erste Stufe, kleinste Einheiten
- Von Entwicklern geschrieben
- WhiteBox-Test
- Komponenten isoliert getestet
- Hauptsächlich funktionale Anforderungen
**2. Component Testing**
- Zusammenspiel mehrerer Komponenten
- WhiteBox-Test
- Schnittstellen werden gemockt
**3. Integration Testing**
- Tester/QA Team
- Black-/WhiteBox-Test
- Echte Schnittstellen (DB, APIs)
- Keine Mocks mehr
**4. System Testing**
- Software als Ganzes
- BlackBox-Test
- Nahe an Live-Umgebung
- Funktionale + nicht-funktionale Tests
**5. Acceptance Testing**
- Vom Kunden/Business
- BlackBox-Test
- Erfüllung der Akzeptanzkriterien
## 4. UNIT TESTING
### Kriterien für gute Unit Tests (F.I.R.S.T):
- **Fast** - Schnell ausführbar
- **Isolated** - Unabhängig voneinander
- **Repeatable** - Immer gleiches Ergebnis
- **Self-verifying** - Automatisiert
- **Timely** - Idealerweise vor dem Code (TDD)
Unverzichtbar für Refactorings, Steuern das Design, Dokumentation, Frühe Fehlererkennung.
### Wichtige JUnit Assertions
```java
assertEquals(expected, actual)  // Werte vergleichen
assertTrue/assertFalse(condition)  // Bedingungen prüfen
assertNull/assertNotNull(object)  // Null-Checks
assertThrows(Exception.class, () -> {...})  // Exceptions testen
```
### Wichtige JUnit Annotations
```java
@Test  // Markiert Testmethode
@BeforeEach  // Vor jedem Test ausführen
@AfterEach  // Nach jedem Test ausführen
@BeforeAll  // Einmal vor allen Tests
@AfterAll  // Einmal nach allen Tests
@DisplayName("...")  // Testbeschreibung
@Disabled  // Test deaktivieren
@ParameterizedTest  // Test mit verschiedenen Parametern
```
## 5. AUTOMATISIERUNG & MOCK-UPS
### Test Doubles Kategorien:
**Mocks** (Behavioral Testing):
- Mock: Erwartet bestimmte Methodenaufrufe
- Spy: Zeichnet alle Interaktionen auf
**Stubs** (State Testing):
- Stub: Vordefinierte Daten zurückgeben
- Dummy: Einfache Platzhalter (z.B. null)
- Fake: Vereinfachte Implementierung (z.B. In-Memory DB)
### Automatisierung:
- **Manual Testing**: Flexibel, explorativ, initial günstiger
- **Automation Testing**: Zuverlässig, wiederholbar, langfristig günstiger
- **Wichtig für**: Regression Testing, CI/CD Pipelines
### Mockito Framework:
```java
// Mock erstellen
MyClass mock = mock(MyClass.class);
// Verhalten definieren (Stubbing)
when(mock.method()).thenReturn(value);
// Verifizieren (Mock)
verify(mock).method();
```
**Merksatz**:
- Unit → Component → Integration → System → Acceptance
- WhiteBox → BlackBox (mit zunehmendem Level)

- **Testing Environment** → wird genutzt, um Funktionen gezielt zu prüfen (manuell oder automatisiert). Fokus: finden von Fehlern, Anforderungen checken.
- **Staging Environment** → bildet die Produktionsumgebung fast identisch nach. Fokus: prüfen von **Deployment-Prozessen, Konfiguration, Migration und Performance** vor dem echten Livegang.