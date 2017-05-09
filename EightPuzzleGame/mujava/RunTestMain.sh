#!/bin/sh 
export JAVA_HOME=/usr/lib/jvm/java-8-oracle 
export MJDIR=/media/12104064/6559-AC7F/workspace/EightPuzzleGame/mujava 
export CLASSPATH=$JAVA_HOME/lib/tools.jar:$MJDIR/mujava.jar:$MJDIR/openjava.jar:$MJDIR/hamcrest-core-1.3.jar:$MJDIR/junit-4.9.jar
echo $CLASSPATH 
java mujava.gui.RunTestMain > output.txt 
