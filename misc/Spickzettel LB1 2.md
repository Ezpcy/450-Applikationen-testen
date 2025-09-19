# Spickzettel Theorieprüfung – Software Testing

## Grundlagen & Vorgehensmodelle
- **Fehler** = Abweichung IST ↔ SOLL (Anforderung nicht erfüllt)  
- **Mangel** = Erwartung Kunde nicht erfüllt (Qualität ungenügend)  
- **Fehlermaskierung** = Fehler wird durch anderen verdeckt  
- **Gute Testfälle**: unabhängig, eindeutig, keine Redundanz, hohe Fehlerwahrscheinlichkeit, viel Abdeckung  
- **Testabdeckung**: misst getesteten Anteil (Anforderungen, Code – Statement/Branch Coverage)  
- **V-Modell**: jede Entwicklungsphase ↔ eigene Testphase (Unit→Integration→System→Abnahme)  
- **Scrum**: Testen in jedem Sprint, Automatisierung wichtig  

## Teststrategie
- **Testobjekte**: Module, Klassen, Schnittstellen, Skripte  
- **Funktionale Tests**: prüfen *was* Software tut  
- **Nicht-funktionale Tests**: prüfen *wie gut* (z.B. Performance, Sicherheit, Usability)  
- **Abstrakte Tests**: Idee/logische Operatoren, keine Werte  
- **Konkrete Tests**: mit Eingaben & erwarteten Ausgaben  
- **Black-Box**: nur Input/Output, keine Codekenntnis  
- **White-Box**: Tests basieren auf internem Code  

## Testlevels
1. **Unit Testing** – einzelne Methoden/Klassen, White-Box, Entwickler  
2. **Component Testing** – mehrere Komponenten, Schnittstellen oft gemockt  
3. **Integration Testing** – Zusammenspiel mit echten Schnittstellen/DB, QA/Tester  
4. **System Testing** – gesamtes System, funktional + nicht-funktional, Black-Box  
5. **Acceptance Testing** – Kunde prüft Akzeptanzkriterien, Black-Box  

## Unit Testing
- **Gute Unit Tests**: isoliert, unabhängig, schnell, automatisiert, klar, prüfen genau eine Eigenschaft  
- **Assertions**: Soll-/Ist-Vergleich (`assertEquals`, `assertTrue`, `assertThrows`)  
- **Annotations (JUnit 5)**:  
  - `@Test` – Testmethode  
  - `@BeforeEach`/`@AfterEach` – vor/nach jedem Test  
  - `@BeforeAll`/`@AfterAll` – einmaliges Setup/Teardown  

## Automatisierung
- **Manuell**: flexibel, explorativ, langsam  
- **Automatisiert**: schnell, wiederholbar, hoher Initialaufwand  
- Einsatz: Regressionstests, Lasttests, CI/CD  

## Mock-Ups & Test Doubles
- **Dummy** – Platzhalter, ungenutzt  
- **Stub** – feste Antworten  
- **Fake** – einfache funktionierende Implementierung (z.B. In-Memory-DB)  
- **Spy** – zeichnet Aufrufe/Parameter auf  
- **Mock** – überprüft erwartete Aufrufe & Verhalten (Mockito)
