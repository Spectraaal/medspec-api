# MedSpec API — Digitalización de Clínicas Privadas

## 1. Descripción del proyecto y problema de negocio
Muchas clínicas privadas siguen gestionando citas, pacientes e informes con herramientas no integradas (WhatsApp, llamadas, agendas externas, documentos sueltos)eso genera:
- Pérdida de tiempo en tareas administrativas.
- Errores en la disponibilidad y programación de citas.
- Dificultad del paciente para acceder a informes y documentación.
- Falta de trazabilidad (qué informe corresponde a qué cita, médico y paciente).

**MedSpec** propone una solución digital con dos portales:
- **Portal Paciente (app móvil):** solicitar/gestionar citas y consultar informes.
- **Portal Médico (app escritorio/web):** gestión de agenda, pacientes, citas e informes.
La **API REST** es el núcleo backend que conecta ambos portales con una base de datos relacional.

---

## 2. Tecnologías utilizadas
- **Java 17**
- **Spring Boot** (Web, Validation, Spring Data JPA)
- **Hibernate / JPA**
- **MySQL** (XAMPP local)
- **Maven**
- **Postman** (pruebas de endpoints)

---

## 3. Instrucciones para ejecutar el proyecto

### 3.1 Requisitos
- Tener instalado **XAMPP** y arrancar el servicio **MySQL**.
- Crear la base de datos `medspecdb` (solo una vez).

### 3.2 Crear la base de datos
En phpMyAdmin (XAMPP) o ejecutando:
CREATE DATABASE medspecdb;

## 4. Configuración del proyecto

- Configurar **src/main/resources/application.properties** 
- Ejecutar la app / Api disponible en **http://localhost:8080**

## 5. Estructura general del proyecto
- Controller -> Endpoints REST
- Service -> Lógica de negocio
- Repository -> Acceso a datos
- Entity -> Modelo de datos
- DTO -> Respuestas con datos seleccionados (no todos)

## 6. Endpoints principales

## 6.1 Clínicas
- POST /api/clinicas → Crear clínica
- GET /api/clinicas → Listar clínicas
- - GET /api/clinicas?nombre={texto} → Buscar clínicas por nombre (opcional)
- GET /api/clinicas/{id} → Obtener clínica por id
- PUT /api/clinicas/{id} → Actualizar clínica
- DELETE /api/clinicas/{id} → Eliminar clínica

## 6.2 Pacientes
- POST /api/pacientes → Crear paciente
- GET /api/pacientes → Listar pacientes
- GET /api/pacientes/{id} → Obtener paciente por id
- PUT /api/pacientes/{id} → Actualizar paciente
- DELETE /api/pacientes/{id} → Eliminar paciente
- GET /api/pacientes?nombre={texto}&apellidos={texto} → Buscar pacientes por nombre y/o apellidos (opcional)

## 6.3 Médicos
- POST /api/medicos?clinicaId={clinicaId} → Crear médico (asociado a clínica)
- GET /api/medicos → Listar médicos
- GET /api/medicos/{id} → Obtener médico por id
- GET /api/medicos?especialidad=cardio → filtra por especialidad
- GET /api/medicos?clinicaId=1 → filtra por clínica
- GET /api/medicos?clinicaId=1&especialidad=cardio → filtra por ambos
- PUT /api/medicos/{id}?clinicaId={clinicaId} → Actualizar médico
- DELETE /api/medicos/{id} → Eliminar médico

## 6.4 Citas
- POST /api/citas?clinicaId={id}&pacienteId={id}&medicoId={id} → Crear cita
- GET /api/citas → Listar todas las citas
- GET /api/citas/{id} → Obtener cita por id
- GET /api/citas/por-paciente/{pacienteId} → Listar citas por paciente
- GET /api/citas/por-medico/{medicoId} → Listar citas por médico
- PUT /api/citas/{id}/estado?estado=CONFIRMADA → Cambiar estado de cita
- DELETE /api/citas/{id} → Eliminar cita
- Estados válidos de cita:
SOLICITADA, CONFIRMADA, CANCELADA, FINALIZADA

## 6.5 Informes Médicos
- Regla de negocio: 1 informe por cita.
- POST /api/informes?citaId={citaId} → Crear informe asociado a cita
- GET /api/informes/{id} → Obtener informe por id
- GET /api/informes/por-paciente/{pacienteId} → Listar informes por paciente
- GET /api/informes/por-medico/{medicoId} → Listar informes por médico
- PUT /api/informes/{id} → Actualizar informe por id
- DELETE /api/informes/{id} → Eliminar informe
