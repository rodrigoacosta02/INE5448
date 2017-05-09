#!/bin/sh 
export JAVA_HOME=/usr/lib/jvm/java-8-oracle
export MJDIR=/media/12104064/6559-AC7F/workspace/INE5448/EightPuzzleGame/mujava
export CLASSPATH=$JAVA_HOME/lib/tools.jar:$MJDIR/mujava.jar:$MJDIR/openjava.jar
echo $CLASSPATH 
java mujava.gui.GenMutantsMain
