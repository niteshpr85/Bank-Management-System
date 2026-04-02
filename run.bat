@echo off
title SQLite Bank System
echo === SQLite Bank Management System ===

rem Check Java
java -version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo ! Java not found - install JDK 11+ from https://adoptium.net/
    pause
    exit /b 1
)

rem Find sqlite-jdbc JAR in lib/
set "SQLITE_JAR="
for %%f in (lib\sqlite-jdbc-*.jar) do if not defined SQLITE_JAR set "SQLITE_JAR=%%f"
if not defined SQLITE_JAR (
    echo ! JAR missing - downloading sqlite-jdbc-3.51.3.0.jar...
    if not exist lib mkdir lib
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.51.3.0/sqlite-jdbc-3.51.3.0.jar' -OutFile 'lib\sqlite-jdbc-3.51.3.0.jar'}"
    if %ERRORLEVEL% neq 0 (
        echo ! Download failed - manually download to lib\sqlite-jdbc-3.51.3.0.jar
        echo URL: https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.51.3.0/sqlite-jdbc-3.51.3.0.jar
        pause
        exit /b 1
    )
    set "SQLITE_JAR=lib\sqlite-jdbc-3.51.3.0.jar"
)

echo Using %SQLITE_JAR%

if not exist classes mkdir classes
set "SRC_DIR=src"
if not exist "%SRC_DIR%\bank\management\system\Login.java" set "SRC_DIR=Bank-Management-System--master\src"
echo Compiling (source=%SRC_DIR%)...
pushd "%SRC_DIR%\bank\management\system"
javac -cp "%SQLITE_JAR%" -d "%~dp0classes" *.java
set "COMPILATION_STATUS=%ERRORLEVEL%"
popd
if %COMPILATION_STATUS% neq 0 (
    echo Compile failed!
    pause
    exit /b 1
)

echo Running...
java -cp "%SQLITE_JAR%;classes;%SRC_DIR%" bank.management.system.Login
pause
