Instrucciones de ejecución (Windows)

1. Ejecutar las pruebas:
   .\gradlew clean test

2. Generar reporte Serenity:
   (se genera automáticamente tras la ejecución)
   Abra: target/site/serenity/index.html

3. Incluir en el ZIP de entrega:
   - Carpeta src (tests, features y código)
   - Recursos en src/test/resources/test-data (JSONs)
   - Reportes en target/site/serenity
   - build.gradle / pom.xml
   - readme.txt y CONCLUSIONES_UNIFICADAS.md

Nota: El test de Demoblaze está etiquetado con @demoblaze y usa: src/test/resources/test-data/purchase-data-demoblaze.json
