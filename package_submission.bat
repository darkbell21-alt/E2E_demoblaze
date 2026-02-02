@echo off
REM Ejecuta pruebas y empaqueta los archivos necesarios en submission.zip
call gradlew clean test
powershell -Command "Compress-Archive -Path ./src, ./pom.xml, ./build, ./target/site/serenity, ./readme.txt, ./CONCLUSIONES_UNIFICADAS.md -DestinationPath submission.zip -Force"
echo Paquete creado: submission.zip
