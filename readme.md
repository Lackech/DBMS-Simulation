# Simulation of a DBMS

## Introduction

This application is a simulation of a Database Management System, that have five modules and each module has its own way to generate times for all the events in the system.

## Code Samples

Example of gradle.build

group 'org.simulador.dbms'
version '1.0'

apply plugin: 'java'
apply plugin: 'application'

mainClassName = "com.simulador.dbms.Simulador"



repositories {
    mavenCentral()
}

jar {
    baseName = 'simulador'
    version =  '1.0'
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

## Installation

We are using Gradle to build our project.
You need to install Gradle on your computer and create the environment variable GRADLE_HOME with the location of your downloaded Gradle. 
Also you need to add this variable to the path system.
Next you need to import your project(preferably in InteliJ) in your IDE and start to work.