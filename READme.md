1. Java Record & Entity: Erklärung und Vorteile
Was ist ein Java Record?
Ein Record in Java ist eine kompakte Syntax für Klassen, die hauptsächlich Daten (Felder) speichern.

Records sind immutable (unveränderlich), d.h. Felder sind final und es gibt keine Setter.

Java generiert automatisch Konstruktor, Getter, equals(), hashCode() und toString().

Warum ist ein Java Record für Entitätstypen (Entity Types) bevorzugt?
Weniger Boilerplate-Code: Kein manuelles Schreiben von Konstruktoren, Gettern, equals, hashCode, toString nötig.

Immutable by default: Entitäten sind oft Datencontainer, die sich nicht nach der Erstellung ändern sollen.

Klare, prägnante Definition: Lesbarer Code, der direkt die Datenstruktur zeigt.

Passend für "Value Objects": Entitäten sind oft Value-Objekte, deren Identität durch Daten definiert wird.

Was sind die wesentlichen Eigenschaften eines Java Records?
Sind Klassen, die nur Felder (Komponenten) definieren.

Alle Felder sind final und automatisch private.

Automatisch generierter Konstruktor mit allen Feldern als Parameter.

Automatisch generierte Methoden: equals(), hashCode(), toString().

Kein Vererben möglich, da Records final sind (außer von java.lang.Record).

Werden oft für unveränderliche Daten verwendet.

Was ist ein Primary Key (Primärschlüssel)?
Ein Primärschlüssel ist ein Feld (oder Kombination von Feldern), das jede Zeile (Datensatz) in einer Tabelle eindeutig identifiziert.

Dient der eindeutigen Identifikation von Datensätzen.

Beispiel: id im Customer-Record, eindeutig für jeden Kunden.

Was ist ein Foreign Key (Fremdschlüssel)?
Ein Fremdschlüssel ist ein Feld (oder Kombination), das auf einen Primärschlüssel in einer anderen Tabelle verweist.

Ermöglicht die Verknüpfung (Relation) zwischen Tabellen.

Beispiel: In einer Order-Tabelle könnte ein Feld customer_id sein, das auf die id des Kunden in der Customer-Tabelle verweist.

2. Singleton-Klasse DBSchemaCreator
Was ist ein Singleton?
Ein Entwurfsmuster (Design Pattern), das sicherstellt, dass eine Klasse genau eine einzige Instanz hat.

Diese Instanz wird global zugänglich gemacht (z.B. über eine statische Methode).

Eigenschaften eines Singleton:
Privater Konstruktor verhindert externe Instanziierung.

Statische Variable hält die einzige Instanz.

Öffentliche statische Methode (z.B. getInstance()), um auf die Instanz zuzugreifen.

Optional: Thread-Sicherheit (bei Multithreading).

Unterschied zwischen lazy und strict Singleton:
Lazy Singleton: Instanz wird erst bei der ersten Anfrage erstellt (z.B. beim ersten Aufruf von getInstance()).

Strict (Eager) Singleton: Instanz wird direkt beim Laden der Klasse erzeugt (z.B. statische Initialisierung).

Welche Variante wurde in DBSchemaCreator implementiert?
Lazy Singleton:

java
Kopieren
Bearbeiten
public static DBSchemaCreator getInstance() {
    if(dbSchema == null) {
        dbSchema = new DBSchemaCreator();
    }
    return dbSchema;
}
Die Instanz dbSchema wird erst beim ersten Aufruf von getInstance() erzeugt.

Vorteil: Ressourcen werden erst bei Bedarf verbraucht.

Nachteil: Nicht thread-safe ohne Synchronisation (kann aber für einfache Anwendungen ausreichend sein).

Zusammenfassung:
Frage	Antwort
Warum Java Record für Entity?	Weniger Code, unveränderlich, automatisch generierte Methoden, klare Datenstruktur
Wesentliche Eigenschaften eines Record	Final, automatisch generierte Konstruktoren und Methoden, immutable, keine Vererbung möglich
Was ist ein Primärschlüssel?	Eindeutige Identifikation eines Datensatzes in Tabelle
Was ist ein Fremdschlüssel?	Verweis auf Primärschlüssel in anderer Tabelle zur Verknüpfung
Was ist ein Singleton?	Klasse mit genau einer einzigen Instanz, global verfügbar
Eigenschaften eines Singleton	Privater Konstruktor, statische Instanz, Zugriffsmethode
Unterschied lazy vs. strict Singleton	lazy: Instanz bei Bedarf erstellt, strict: Instanz bei Klassenladen erstellt
Variante in DBSchemaCreator	Lazy Singleton, da Instanz erst bei erstem Aufruf erstellt wird

