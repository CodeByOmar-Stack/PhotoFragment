# PhotoFragment

PhotoFragment es una aplicación móvil para Android desarrollada con Kotlin y Jetpack Compose. Se diseñó como un proyecto de portafolio que integra una experiencia de escritura creativa sobre fragmentos visuales, con navegación fluida, gestión local de datos y una interfaz moderna basada en Material Design.

## Descripción general

La aplicación ofrece una experiencia centrada en la inspiración visual: el usuario recibe fragmentos literarios acompañados de imágenes de referencia, puede escribir historias personalizadas y guardar sus textos en una biblioteca local.

Este proyecto representa una primera aproximación profesional a Jetpack Compose y a la arquitectura moderna de Android, aplicada en un caso real de interacción creativa y persistencia de datos.

## Funcionalidades principales

- Presentación de fragmentos de inspiración con imágenes de alta calidad.
- Navegación entre pantallas mediante Compose Navigation.
- Selección de imágenes desde la galería y captura de fotos desde la cámara.
- Editor de texto con contador de palabras integrado.
- Almacenamiento local de historias usando Room.
- Biblioteca de historias con lista dinámica y eliminación de entradas.
- Diseño responsivo y consistente con Material 3.

## Componentes clave del proyecto

- `MainActivity.kt`: punto de entrada de la aplicación que inicia el árbol de composables con `AppNavigation()`.
- `AppNavigation.kt`: define la navegación entre `Home`, `Editor` y `Library` usando `NavHost` y `Composable`.
- `HomeScreen.kt`: pantalla principal que muestra el fragmento activo y permite seleccionar imágenes o acceder a la biblioteca.
- `EditorScreen.kt`: editor de texto con campo de título, contenido y guardado de historias.
- `LibraryScreen.kt`: lista de historias almacenadas con acciones de eliminación y acceso rápido para crear nuevas entradas.
- `PromptRepository.kt`: repositorio de prompts con ejemplos de fragmentos e imágenes de referencia.
- `AppDatabase.kt`: configuración de Room para persistencia local de la entidad `Story`.

## Arquitectura y patrones

PhotoFragment utiliza una arquitectura simple basada en capas:

- Capa de presentación: pantallas y componentes de Jetpack Compose.
- Capa de navegación: Compose Navigation para gestionar rutas y transiciones.
- Capa de datos: Room para persistencia local, con convertidores de tipos para fechas.
- Capa de repositorio: abstrae el acceso a los prompts y a las historias guardadas.

Esta organización facilita el mantenimiento y la escalabilidad del código, con responsabilidades bien delimitadas entre UI y persistencia.

## Tecnologías y bibliotecas

- Kotlin
- Jetpack Compose
- Compose Navigation
- Material 3
- Room
- Coil (para carga de imágenes)
- AndroidX Core KTX
- AndroidX Lifecycle Runtime

## Motivación y aprendizaje

PhotoFragment es el primer proyecto desarrollado con Jetpack Compose y Kotlin en un entorno profesional de portafolio. El desarrollo de esta aplicación permitió:

- dominar el ciclo de vida de composables y los estados reactivos,
- aplicar navegación declarativa con `NavHost` y `rememberNavController`,
- integrar Room en una app Compose para almacenamiento persistente,
- diseñar una interfaz moderna con Material 3 y componentes personalizados,
- gestionar permisos y resultados de actividad para cámara y galería.

## Instalación y ejecución

1. Clonar el repositorio.
2. Abrir en Android Studio.
3. Sincronizar dependencias con Gradle.
4. Ejecutar el módulo `app` en un emulador o dispositivo físico.

## Alcance del proyecto

PhotoFragment es un proyecto de portafolio que combina creatividad visual con funcionalidad técnica. Está pensado para mostrar habilidades en:

- diseño de interfaces con Compose,
- integración de datos locales,
- experiencia de usuario móvil,
- estructuración de código en arquitectura moderna.

## Notas adicionales

El proyecto usa `compileSdk 35`, `targetSdk 35` y `minSdk 24`. La versión de Coil incluida permite la carga eficiente de imágenes desde URLs y URIs locales.
