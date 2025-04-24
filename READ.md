1. mvn validate
   Aktion: Validiert die Struktur des Projekts und stellt sicher, dass alle notwendigen Dateien (wie pom.xml) korrekt konfiguriert sind.

Lifecycle-Phase: validate

Zweck: Überprüft, ob das Projekt für den Build-Prozess bereit ist.

2. mvn compile
   Aktion: Kompiliert den Quellcode des Projekts (aus dem Verzeichnis src/main/java) in .class-Dateien im Verzeichnis target/classes.

Lifecycle-Phase: compile

Zweck: Wandelt den Quellcode in kompilierte Bytecode-Dateien um.

3. mvn test
   Aktion: Führt Unit-Tests mit einem Test-Framework (wie JUnit) auf dem kompilierten Code aus.

Lifecycle-Phase: test

Zweck: Validiert, ob der Code korrekt funktioniert und alle Unit-Tests bestehen.

4. mvn package
   Aktion: Packt den kompilierten Code in ein verteilbares Format, wie z.B. eine .jar, .war oder .ear-Datei, gemäß der Konfiguration im pom.xml.

Lifecycle-Phase: package

Zweck: Erstellt das finale Artefakt (z.B. .jar, .war), das weitergegeben oder bereitgestellt werden kann.

5. mvn verify
   Aktion: Führt alle notwendigen Prüfungen aus, um die Integrität und Korrektheit des Projekts sicherzustellen (z.B. durch Tests oder Überprüfungen des Pakets).

Lifecycle-Phase: verify

Zweck: Stellt sicher, dass das Projekt valide ist und alle Verifizierungsaufgaben abgeschlossen sind.

6. mvn install
   Aktion: Installiert das gepackte Artefakt in das lokale Maven-Repository (~/.m2/repository), damit es als Abhängigkeit in anderen lokalen Projekten verwendet werden kann.

Lifecycle-Phase: install

Zweck: Macht das Artefakt für die Nutzung in anderen Projekten auf demselben System verfügbar.

7. mvn deploy
   Aktion: Lädt das Artefakt in ein Remote-Repository (z.B. ein Firmen-Repository oder Maven Central) hoch, damit es mit anderen geteilt werden kann.

Lifecycle-Phase: deploy

Zweck: Stellt das Artefakt für die Nutzung in anderen Umgebungen oder Projekten zur Verfügung.

8. mvn clean
   Aktion: Löscht das target-Verzeichnis, das alle kompilierten Dateien und gepackten Artefakte enthält, und reinigt damit das Projekt von zuvor erstellten Dateien.

Lifecycle-Phase: clean

Zweck: Reinigt das Projektumfeld, bevor ein neuer Build-Prozess gestartet wird.




ChatGPT:
Was ist das Ziel des "Projekt-Bauprozesses"?
Sicherstellen, dass der Quellcode in eine ausführbare oder verpackte Form umgewandelt wird.

Automatisierung des gesamten Build-Prozesses, um Konsistenz und Wiederholbarkeit zu gewährleisten.

Vermeidung manueller Fehler und Unterstützung der kontinuierlichen Entwicklung.

Wann beginnt der "Projekt-Bauprozess" und mit welchem Ergebnis endet er?
Der Bauprozess beginnt mit dem Quellcode und den Build-Dateien (z.B. pom.xml bei Maven).

Das Ergebnis ist ein Artefakt (z.B. .jar, .war, .ear), das bereitgestellt oder weiterverarbeitet werden kann.

Was sind Aufgaben des "Projekt-Baus"?
Kompilieren des Quellcodes.

Ausführen von Tests (z.B. Unit-Tests).

Erstellen eines finalen Artefakts (z.B. .jar, .war).

Verpacken und ggf. Bereitstellen des Artefakts.

Warum werden Build-Tools oder Build-Automatisierung eingesetzt?
Reduzierung manueller Arbeit und Fehlerquellen.

Sicherstellung von Wiederholbarkeit und Konsistenz im Build-Prozess.

Automatisierung von Tests, Kompilierung und Bereitstellung.

Welche anderen Build-Tools existieren (außer Maven)?
Gradle: Flexibles und leistungsstarkes Build-Tool, das in Java-Projekten verwendet wird.

Ant: Ein weiteres Java-basiertes Build-Tool, das jedoch weniger konventionelle Standards als Maven hat.

Bazaar: Build-Tool, das sich auf die kontinuierliche Integration und die Automatisierung des Deployments konzentriert.

Was ist ein Continuous Integration (CI)-Build?
Ein automatisierter Build-Prozess, der regelmäßig (häufig nach jedem Commit) ausgeführt wird.

Ziel ist es, sicherzustellen, dass der Code kontinuierlich integriert wird und keine Fehler auftreten.

Häufig mit automatisierten Tests und Benachrichtigungen bei Fehlern kombiniert.

Was sind Nightly Builds?
Automatisierte Builds, die einmal täglich (meist nachts) durchgeführt werden.

Dienen dazu, den neuesten Stand des Codes zu bauen und auf mögliche Fehler oder Probleme hinzuweisen.

Häufig in größeren Projekten verwendet, um den täglichen Fortschritt zu überprüfen.