#! /bin/bash
javac main.java
java -cp .:mysql-connector-java-8.0.25.jar main $1 $2
