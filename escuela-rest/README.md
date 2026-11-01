# escuela-rest

Servicios REST (Spring Boot) para los 5 catálogos de la base de datos `ESCUELA`.

## Cómo correrlo

1. En IntelliJ: clic derecho en `escuela-rest/pom.xml` → **Add as Maven Project**.
2. Define las variables de entorno `DB_PASSWORD` (obligatoria) y `DB_USER`
   (opcional, por defecto `root`) con las credenciales de MySQL.
3. Ejecuta `EscuelaRestApplication`. Queda en `http://localhost:8081`.

## Endpoints

| Catálogo         | Ruta base                 |
|------------------|---------------------------|
| ALUMNO           | `/api/alumnos`            |
| MATERIA          | `/api/materias`           |
| PROFESOR         | `/api/profesores`         |
| ALUMNO_MATERIA   | `/api/alumno-materias`    |
| PROFESOR_MATERIA | `/api/profesor-materias`  |

Cada uno tiene `GET /`, `GET /{id}`, `POST /`, `PUT /{id}` y `DELETE /{id}`.

Para `ALUMNO_MATERIA` y `PROFESOR_MATERIA` se mandan solo los IDs:

```json
{ "alumno": { "id": 2 }, "materia": { "id": 5 }, "calificacion": 9.5 }
```

## Respuestas de error

| Código | Cuándo |
|--------|--------|
| 400    | Faltan datos o el alumno/profesor/materia indicado no existe |
| 404    | El ID no existe |
| 409    | Integridad referencial: borrar un registro que otros usan, o inscribir dos veces al mismo alumno en una materia |

## Demostración

- `pruebas.http`: todas las peticiones, listas para ejecutar desde IntelliJ.
- `verificar.sql`: consultas para mostrar en MySQL que los cambios quedaron guardados.
- `mvn test`: 12 pruebas automáticas que hacen el CRUD de los 5 catálogos y revisan la base de datos (H2 en memoria con el esquema de `sql/Escuela.sql`).
