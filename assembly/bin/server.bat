
@set BIN=%~dp0
@pushd %BIN%\..\log

@call ..\bin\config.bat

@set CLASSPATH=..\conf

@for %%a in (..\lib\*.jar) do @call ..\bin\add2cp.bat %%a

@IF "%JAVA_HOME%" == "" (
   set JAVA=java
) ELSE (
   set JAVA=%JAVA_HOME%\bin\java
)

@"%JAVA%" -classpath %CLASSPATH% %JAVA_OPTS% %* org.bitcheck.BitCheck

@popd
