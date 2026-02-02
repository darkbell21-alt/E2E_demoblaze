🧪 Automatización E2E con Serenity BDD, Cucumber y Demoblaze

Este proyecto implementa un flujo de pruebas automatizadas end-to-end sobre el sitio Demoblaze
, utilizando Serenity BDD, Cucumber y el patrón Screenplay.
Incluye la gestión de datos externos, evidencias automáticas en cada paso y configuraciones para ejecución en Chrome o Firefox.

🚀 Tecnologías utilizadas

Java (JDK 16+)

Gradle 7.6.1

Serenity BDD 4.2.13

Cucumber 7.20.1

WebDriverManager

⚙️ Configuración del entorno
Prerrequisitos

Java JDK 16 o superior

Gradle instalado

Navegador Chrome o Firefox

📦 Instalación y ejecución

Clona el repositorio:

git clone https://github.com/MaribelDS/PracticoSerenity.git 


Ejecuta las pruebas con Gradle:

./gradlew clean test


Ejecutar en Firefox:

./gradlew clean test -Denvironment=firefox


Ejecutar en Chrome:

./gradlew clean test -Denvironment=chrome

📋 Flujo automatizado

El escenario principal cubre un flujo completo de compra:

Inicio de sesión en la aplicación con credenciales válidas.

Selección de productos y agregado al carrito.

Ingreso al carrito y paso al checkout.

Carga de datos de usuario desde purchase-data.json.

Confirmación de la compra y validación del mensaje final.

Además, se agregó una suite E2E para https://www.demoblaze.com/ (etiquetada con `@demoblaze`).
La feature se encuentra en `src/test/resources/features/purchase/demoblaze_purchase.feature` y los datos en `src/test/resources/test-data/purchase-data-demoblaze.json`.
Para ejecutar sólo las pruebas de Demoblaze use: `./gradlew clean test -Dtags=@demoblaze`.

📁 Estructura de carpetas
E2EDemoblaze
├── build.gradle
├── pom.xml
├── serenity.conf
├── serenity.properties
├── src
│   └── test
│       ├── java
│       │   └── starter
│       │       ├── stepdefinitions    # Definiciones de pasos Cucumber
│       │       └── store              # Tareas, preguntas y modelos Screenplay
│       │           ├── purchase
│       │           └── CompleteStore, Login, etc.
│       └── resources
│           ├── features/purchase      # Escenarios en Gherkin
│           └── test-data              # purchase-data.json
├── target
└── build

📄 Datos externos (JSON)

El archivo purchase-data-demoblaze.json permite parametrizar datos de usuario, productos y mensajes esperados para Demoblaze.

Ubicación:

src/test/resources/test-data/purchase-data-demoblaze.json


Ejemplo:

{
  "demo_user": {
    "products": ["Sony vaio i5", "Samsung galaxy s7"],
    "checkoutData": [
      {
        "name": "Yess Vargas",
        "country": "Ecuador",
        "city": "Quito",
        "card": "4111111111111111",
        "month": "02",
        "year": "2026"
      }
    ],
    "confirmationMessage": "Thank you for your purchase!"
  }
}

📸 Reportes

Los reportes de Serenity se generan en:

target/site/serenity/index.html


Abrir en navegador:

open target/site/serenity/index.html


También se incluyen capturas como:

Reporte de eje 2025-09-09 234353.png

Evidencias gráficas de fallos (seguridadContraseña.jpeg).

🧑‍💻 Patrón Screenplay

El proyecto sigue el patrón Screenplay, donde los actores ejecutan tareas y validan preguntas.
Esto permite pruebas más legibles, reutilizables y fáciles de mantener.

✅ Resultado esperado

Al finalizar la compra, el sistema muestra el mensaje:


"Thank you for your order!"
