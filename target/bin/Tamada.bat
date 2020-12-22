@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\com\github\goxr3plus\java-google-speech-api\8.0.0\java-google-speech-api-8.0.0.jar;"%REPO%"\com\github\axet\java-flac-encoder\0.3.8\java-flac-encoder-0.3.8.jar;"%REPO%"\org\json\json\20150729\json-20150729.jar;"%REPO%"\com\vk\api\sdk\0.5.12\sdk-0.5.12.jar;"%REPO%"\com\google\code\gson\gson\2.8.1\gson-2.8.1.jar;"%REPO%"\org\asynchttpclient\async-http-client\2.0.33\async-http-client-2.0.33.jar;"%REPO%"\org\asynchttpclient\async-http-client-netty-utils\2.0.33\async-http-client-netty-utils-2.0.33.jar;"%REPO%"\io\netty\netty-buffer\4.0.48.Final\netty-buffer-4.0.48.Final.jar;"%REPO%"\io\netty\netty-codec-http\4.0.48.Final\netty-codec-http-4.0.48.Final.jar;"%REPO%"\io\netty\netty-codec\4.0.48.Final\netty-codec-4.0.48.Final.jar;"%REPO%"\io\netty\netty-handler\4.0.48.Final\netty-handler-4.0.48.Final.jar;"%REPO%"\io\netty\netty-transport\4.0.48.Final\netty-transport-4.0.48.Final.jar;"%REPO%"\io\netty\netty-transport-native-epoll\4.0.48.Final\netty-transport-native-epoll-4.0.48.Final-linux-x86_64.jar;"%REPO%"\io\netty\netty-common\4.0.48.Final\netty-common-4.0.48.Final.jar;"%REPO%"\org\asynchttpclient\netty-resolver-dns\2.0.33\netty-resolver-dns-2.0.33.jar;"%REPO%"\org\asynchttpclient\netty-resolver\2.0.33\netty-resolver-2.0.33.jar;"%REPO%"\org\asynchttpclient\netty-codec-dns\2.0.33\netty-codec-dns-2.0.33.jar;"%REPO%"\org\reactivestreams\reactive-streams\1.0.0\reactive-streams-1.0.0.jar;"%REPO%"\com\typesafe\netty\netty-reactive-streams\1.0.8\netty-reactive-streams-1.0.8.jar;"%REPO%"\org\apache\commons\commons-collections4\4.1\commons-collections4-4.1.jar;"%REPO%"\commons-io\commons-io\2.5\commons-io-2.5.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.3\httpclient-4.5.3.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.6\httpcore-4.4.6.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\commons-codec\commons-codec\1.9\commons-codec-1.9.jar;"%REPO%"\org\apache\httpcomponents\httpmime\4.5.3\httpmime-4.5.3.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;"%REPO%"\org\apache\commons\commons-lang3\3.6\commons-lang3-3.6.jar;"%REPO%"\org\slf4j\slf4j-jdk14\1.7.22\slf4j-jdk14-1.7.22.jar;"%REPO%"\com\google\api-client\google-api-client-gson\1.30.4\google-api-client-gson-1.30.4.jar;"%REPO%"\com\google\api-client\google-api-client\1.30.4\google-api-client-1.30.4.jar;"%REPO%"\com\google\oauth-client\google-oauth-client\1.30.3\google-oauth-client-1.30.3.jar;"%REPO%"\com\google\http-client\google-http-client-jackson2\1.32.1\google-http-client-jackson2-1.32.1.jar;"%REPO%"\com\google\guava\guava\28.0-android\guava-28.0-android.jar;"%REPO%"\com\google\guava\failureaccess\1.0.1\failureaccess-1.0.1.jar;"%REPO%"\com\google\guava\listenablefuture\9999.0-empty-to-avoid-conflict-with-guava\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;"%REPO%"\org\checkerframework\checker-compat-qual\2.5.5\checker-compat-qual-2.5.5.jar;"%REPO%"\com\google\errorprone\error_prone_annotations\2.3.2\error_prone_annotations-2.3.2.jar;"%REPO%"\com\google\j2objc\j2objc-annotations\1.3\j2objc-annotations-1.3.jar;"%REPO%"\org\codehaus\mojo\animal-sniffer-annotations\1.17\animal-sniffer-annotations-1.17.jar;"%REPO%"\com\google\http-client\google-http-client-gson\1.32.1\google-http-client-gson-1.32.1.jar;"%REPO%"\com\google\http-client\google-http-client\1.32.1\google-http-client-1.32.1.jar;"%REPO%"\io\opencensus\opencensus-api\0.24.0\opencensus-api-0.24.0.jar;"%REPO%"\io\grpc\grpc-context\1.22.1\grpc-context-1.22.1.jar;"%REPO%"\io\opencensus\opencensus-contrib-http-util\0.24.0\opencensus-contrib-http-util-0.24.0.jar;"%REPO%"\net\dv8tion\JDA\4.2.0_223\JDA-4.2.0_223.jar;"%REPO%"\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;"%REPO%"\org\jetbrains\annotations\16.0.1\annotations-16.0.1.jar;"%REPO%"\com\neovisionaries\nv-websocket-client\2.10\nv-websocket-client-2.10.jar;"%REPO%"\com\squareup\okhttp3\okhttp\3.13.0\okhttp-3.13.0.jar;"%REPO%"\com\squareup\okio\okio\1.17.2\okio-1.17.2.jar;"%REPO%"\club\minnced\opus-java-api\1.0.4\opus-java-api-1.0.4.jar;"%REPO%"\net\java\dev\jna\jna\4.4.0\jna-4.4.0.jar;"%REPO%"\club\minnced\opus-java-natives\1.0.4\opus-java-natives-1.0.4.jar;"%REPO%"\net\sf\trove4j\trove4j\3.0.3\trove4j-3.0.3.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.10.1\jackson-databind-2.10.1.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.10.1\jackson-annotations-2.10.1.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.10.1\jackson-core-2.10.1.jar;"%REPO%"\org\reflections\reflections\0.9.12\reflections-0.9.12.jar;"%REPO%"\org\javassist\javassist\3.26.0-GA\javassist-3.26.0-GA.jar;"%REPO%"\io\github\cdimascio\java-dotenv\5.2.2\java-dotenv-5.2.2.jar;"%REPO%"\org\jetbrains\kotlin\kotlin-stdlib\1.4.0\kotlin-stdlib-1.4.0.jar;"%REPO%"\org\jetbrains\kotlin\kotlin-stdlib-common\1.4.0\kotlin-stdlib-common-1.4.0.jar;"%REPO%"\org\xerial\sqlite-jdbc\3.8.10.1\sqlite-jdbc-3.8.10.1.jar;"%REPO%"\com\googlecode\json-simple\json-simple\1.1.1\json-simple-1.1.1.jar;"%REPO%"\junit\junit\4.10\junit-4.10.jar;"%REPO%"\org\hamcrest\hamcrest-core\1.1\hamcrest-core-1.1.jar;"%REPO%"\org\junit\jupiter\junit-jupiter\5.6.3\junit-jupiter-5.6.3.jar;"%REPO%"\org\junit\jupiter\junit-jupiter-api\5.6.3\junit-jupiter-api-5.6.3.jar;"%REPO%"\org\apiguardian\apiguardian-api\1.1.0\apiguardian-api-1.1.0.jar;"%REPO%"\org\opentest4j\opentest4j\1.2.0\opentest4j-1.2.0.jar;"%REPO%"\org\junit\platform\junit-platform-commons\1.6.3\junit-platform-commons-1.6.3.jar;"%REPO%"\org\junit\jupiter\junit-jupiter-params\5.6.3\junit-jupiter-params-5.6.3.jar;"%REPO%"\org\junit\jupiter\junit-jupiter-engine\5.6.3\junit-jupiter-engine-5.6.3.jar;"%REPO%"\org\junit\platform\junit-platform-engine\1.6.3\junit-platform-engine-1.6.3.jar;"%REPO%"\com\urfu\Tamada\1.0-SHAPSHOT\Tamada-1.0-SHAPSHOT.jar
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="Tamada" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" com.urfu.Tamada.Main %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
