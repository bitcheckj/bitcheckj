
@rem set JAVA_HOME=

@set JAVA_OPTS=-server
@set JAVA_OPTS=%AVA_OPTS% -Xms2048m -Xmx2048m -XX:MaxPermSize=256m
@rem set JAVA_OPTS=%JAVA_OPTS% -Xdebug -Xrunjdwp:transport=dt_socket,address=9000,suspend=n,server=y
@rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.port=8000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
@set JAVA_OPTS=%JAVA_OPTS% -Xss412k


