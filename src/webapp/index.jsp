<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Escuela - Sistema de Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Escuela</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuCatalogos"
                aria-controls="menuCatalogos" aria-expanded="false" aria-label="Mostrar menú">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menuCatalogos">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Alumnos.jsp">Alumnos</a></li>
                <li class="nav-item"><a class="nav-link" href="Materias.jsp">Materias</a></li>
                <li class="nav-item"><a class="nav-link" href="Profesores.jsp">Profesores</a></li>
                <li class="nav-item"><a class="nav-link" href="ProfesorMaterias.jsp">Profesor - Materia</a></li>
                <li class="nav-item"><a class="nav-link" href="AlumnoMaterias.jsp">Alumno - Materia</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h1 class="mt-4 mb-4">Sistema de Administración Escolar</h1>
    <p class="lead">Selecciona un catálogo para administrar:</p>
    <div class="row g-3">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Alumnos</h5>
                    <p class="card-text">Administrar el catálogo de alumnos.</p>
                    <a href="Alumnos.jsp" class="btn btn-primary">Ir a Alumnos</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Materias</h5>
                    <p class="card-text">Administrar el catálogo de materias.</p>
                    <a href="Materias.jsp" class="btn btn-primary">Ir a Materias</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Profesores</h5>
                    <p class="card-text">Administrar el catálogo de profesores.</p>
                    <a href="Profesores.jsp" class="btn btn-primary">Ir a Profesores</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Profesor - Materia</h5>
                    <p class="card-text">Administrar asignación de materias a profesores.</p>
                    <a href="ProfesorMaterias.jsp" class="btn btn-primary">Ir a Profesor Materias</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Alumno - Materia</h5>
                    <p class="card-text">Administrar inscripción de alumnos a materias.</p>
                    <a href="AlumnoMaterias.jsp" class="btn btn-primary">Ir a Alumno Materias</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>