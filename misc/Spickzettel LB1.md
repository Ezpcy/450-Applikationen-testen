#### **Fehler vs Mangel**
- **Fehler (Error)**: Menschliche Handlung, die zu falschem Ergebnis führt
    - Beispiel: Programmierer tippt `if (x = 5)` statt `if (x == 5)`
- **Mangel/Defekt (Defect/Bug)**: Fehler im Code/Dokument
    - Beispiel: Der obige Tippfehler ist nun im Code
- **Versagen (Failure)**: Sichtbare Abweichung vom erwarteten Verhalten
    - Beispiel: Programm stürzt ab oder zeigt falsche Werte
#### **Fehlermaskierung**
- Ein Fehler verdeckt einen anderen Fehler
- Beispiel: Division durch 0 wird durch vorherige falsche IF-Bedingung nie erreicht
- Gefahr: Versteckte Fehler werden erst später entdeckt
#### **Kriterien gute Testfälle**
1. **Eindeutig**: Klare Schritte & erwartete Ergebnisse
2. **Nachvollziehbar**: Andere können Test wiederholen
3. **Unabhängig**: Keine Abhängigkeit von anderen Tests
4. **Atomär**: Testet nur eine Funktionalität
5. **Priorisiert**: Wichtige Features zuerst
#### **Testing in Vorgehensmodellen**
- **Wasserfall**: Testing am Ende (sequenziell)
- **V-Modell**: Jede Entwicklungsphase hat korrespondierende Testphase
- **Agile/Scrum**: Kontinuierliches Testing in jedem Sprint
- **DevOps**: Automatisiertes Testing in CI/CD Pipeline
#### **Testabdeckung (Coverage)**
- **Statement Coverage**: % der ausgeführten Code-Zeilen
- **Branch Coverage**: % der durchlaufenen Verzweigungen
- **Path Coverage**: % aller möglichen Pfade
- Formel: `Coverage = (Getestete Elemente / Alle Elemente) × 100%`
---
### **2. TESTSTRATEGIE**
#### **Testobjekte**
- **Code**: Funktionen, Klassen, Module
- **Dokumente**: Anforderungen, Design-Dokumente
- **Konfiguration**: Config-Files, Umgebungsvariablen
- **Daten**: Testdaten, Datenbanken
#### **Funktionale vs Nicht-funktionale Tests**

|**Funktional**|**Nicht-funktional**|
|---|---|
|WAS das System tut|WIE das System arbeitet|
|Login funktioniert|Performance (< 2 Sek Ladezeit)|
|Berechnung korrekt|Sicherheit (SQL-Injection)|
|Daten werden gespeichert|Usability (intuitiv bedienbar)|
#### **Abstrakte vs Konkrete Tests**
- **Abstrakt**: Logische Testfälle ohne konkrete Werte
    - "Teste Login mit gültigen Daten"
- **Konkret**: Spezifische Werte definiert
    - "Login mit user='admin', pwd='Test123!'"
#### **BlackBox vs WhiteBox Testing**

|**BlackBox**|**WhiteBox**|
|---|---|
|Keine Kenntnis der Implementierung|Volle Kenntnis des Codes|
|Input → Output Verifikation|Code-Coverage wichtig|
|Testet Anforderungen|Testet alle Code-Pfade|
|Beispiel: GUI-Test|Beispiel: Unit-Test|
---
### **3. TESTLEVELS**
```
Acceptance Test    ← Kunde/Business
     ↑
System Test       ← Gesamtsystem
     ↑  
Integration Test  ← Module zusammen
     ↑
Unit Test        ← Einzelne Funktionen
```
1. **Unit Test**:
    - Kleinste testbare Einheit (Methode/Funktion)
    - Isoliert, schnell, automatisiert
2. **Integration Test**:
    - Zusammenspiel mehrerer Units/Module
    - API-Tests, Datenbank-Verbindungen
3. **System Test**:
    - Komplettes System Ende-zu-Ende
    - Alle Requirements erfüllt?
4. **Acceptance Test**:
    - Erfüllt Business-Anforderungen?
    - User Acceptance Testing (UAT)
---
### **4. UNIT TESTING**
#### **Kriterien gute Unit Tests (FIRST)**
- **F**ast: Millisekunden Ausführung
- **I**ndependent: Keine Abhängigkeiten
- **R**epeatable: Immer gleiches Ergebnis
- **S**elf-Validating: Pass/Fail ohne manuelle Prüfung
- **T**imely: Vor/während Code-Entwicklung
#### **Assertions (Beispiele JUnit)**
```java
assertEquals(expected, actual);  // Werte gleich?
assertTrue(condition);           // Bedingung wahr?
assertFalse(condition);         // Bedingung falsch?
assertNull(object);            // Objekt null?
assertNotNull(object);         // Objekt nicht null?
assertThrows(Exception.class,  // Exception geworfen?
            () -> method());
```
#### **Annotations (JUnit)**
```java
@Test           // Markiert Testmethode
@BeforeEach     // Vor jedem Test ausführen
@AfterEach      // Nach jedem Test ausführen
@BeforeAll      // Einmal vor allen Tests
@AfterAll       // Einmal nach allen Tests
@Disabled       // Test temporär deaktivieren
@ParameterizedTest  // Test mit verschiedenen Parametern
```
---
### **ZUSATZ: Automatisierung & Mocking**
#### **Test-Automatisierung**
- **CI/CD Integration**: Tests bei jedem Commit
- **Test-Pyramide**: Viele Unit-Tests, wenige UI-Tests
- **Tools**: Jenkins, GitLab CI, GitHub Actions
#### **Mock-Objekte**
- Simulieren externe Abhängigkeiten
- Beispiel: Mock-Datenbank statt echte DB
```java
// Mockito Beispiel
UserService mockService = mock(UserService.class);
when(mockService.getUser(1)).thenReturn(new User("Test"));
```

- **Dummy**: Object only used to fill parameter lists. Not used at all.
- **Stub**: Provides predefined answers to calls. Does not record usage.
- **Fake**: Has working but simplified implementation. Often in-memory.
- **Mock**: A stub plus the ability to verify interactions. Checks whether certain methods were called.
- **Spy**: Wraps a real object but lets you monitor and override parts of it.
- **Dummy**: Rare, only when method signature forces you.
- **Stub**: When you need specific return values but do not care how often it was called.
- **Fake**: When you want a fast but working implementation (e.g., replace DB with `Map`). Good for integration tests.
- **Mock**: When you care about _interactions_ with dependencies. Most common in Spring service tests.
- **Spy**: When you want to partially test a real object but still verify calls.

**Testabdeckung** misst, wie viel vom System/Code durch Tests geprüft wird.
Typische Arten:
	1. **Anforderungsabdeckung** → Welche *Requirments* sind durch Tests abgedeckt?
	2. **Codeabdeckung** → Welcher Anteil des *Quellcodes* wird durch Tests ausgeführt?
		- Beispiele: *Statement Coverage* (Anweisungen), *Branch Coverage*  (Verzweigungen).