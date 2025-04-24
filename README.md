# **EduTrack - Plataforma de Seguimiento Académico**

> **Documentación técnica del aplicativo web**

## Descripción

**EduTrack** es una plataforma diseñada para el seguimiento académico, que facilita la gestión de notas, tareas y asistencia de los estudiantes. Ofrece tres roles principales: **estudiante**, **docente** y **administrador**, permitiendo un manejo eficiente de las actividades académicas y mejorando la comunicación entre todos los involucrados. 

El sistema está desplegado utilizando **Google Cloud Platform (GCP)** con **App Engine** y **Cloud SQL**, lo que asegura una infraestructura escalable y segura.

## Características

- Gestión de notas, tareas y asistencia de los estudiantes
- Roles de usuario: estudiante, docente y administrador
- Panel de control para cada rol con funcionalidades específicas
- Almacenamiento en base de datos relacional (Cloud SQL)
- Despliegue en Google Cloud Platform (App Engine)

### Requisitos 📋

Lista de software y hardware recomendado para instalar y ejecutar este proyecto:

| Recurso                    | Requerimiento                |
|----------------------------|------------------------------|
| **PC**                     | Al menos 8 GB de RAM         |
| **Sistema Operativo**      | Windows 10 o superior        |
| **Framework**              | Spring Boot                  |
| **Base de Datos**          | Google Cloud SQL             |
| **IDE**                    | IntelliJ IDEA  |
| **JDK**                    | Java Development Kit 17      |
| **Gestor de Dependencias** | Maven                        |

### Instalación 🔧

Guía paso a paso sobre cómo configurar el entorno de desarrollo e instalar todas las dependencias.

```bash
paso 1: Clona el repositorio
git clone https://github.com/tu-usuario/eduTrack.git

paso 2: Entra en el directorio del proyecto
cd eduTrack

paso 3: Compila el proyecto usando Maven
mvn clean install

paso 4: Configura el archivo application.properties para conectar con Google Cloud SQL

paso 5: Ejecuta la aplicación Spring Boot
mvn spring-boot:run
