#!/bin/bash

# Pfad zur H2-JAR-Datei
H2_JAR="$HOME/.m2/repository/com/h2database/h2/2.2.224/h2-2.2.224.jar"

# Falls du eine andere Version wie 2.3.232 verwendest, ändere den Pfad entsprechend

# CLASSPATH zusammenbauen
CLASSPATH="target/classes:$H2_JAR"

# In eine Datei schreiben, damit java -cp "@.classpath-file" funktioniert
echo "$CLASSPATH" > .classpath-file

# Die Variable in die Shell exportieren
export CLASSPATH

echo "project environment has been set up:"
echo " - created: .classpath-file"
echo " - set CLASSPATH: \"$CLASSPATH\""
