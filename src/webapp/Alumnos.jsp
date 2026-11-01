<%@ page import="org.gerdoc.service.AlumnoService" %>
<%@ page import="org.gerdoc.service.impl.AlumnoServiceImpl" %>
<%@ page import="org.gerdoc.model.Alumno" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Alumnos</h1>
    <%@ include file="Mensajes.jspf" %>
    <%
        AlumnoService alumnoService = new AlumnoServiceImpl( );
        List<Alumno> alumnos = alumnoService.findAll( );
    %>
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#alumnoModal" onclick="nuevoAlumno()" >
        Agregar alumno
    </button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre</th>
            <th scope="col">Edad</th>
            <th scope="col">Promedio</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <%
            int i = 0;
            for (Alumno alumno : alumnos)
            {
        %>
                <tbody>
                    <tr>
                        <th scope="row"><%=++i%></th>
                        <td>
                            <a href="Alumno.jsp?id=<%= alumno.getId( ) %>" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                                <%= alumno.getNombre( ) %>
                            </a>
                        </td>
                        <td><%= alumno.getEdad( )%></td>
                        <td><%= alumno.getPromedio( )%></td>
                        <td>
                            <!-- BOTÓN EDITAR -->

                            <button type="button" class="btn btn-warning btn-sm btn-editar" data-bs-toggle="modal" data-bs-target="#alumnoModal"
                                    data-id="<%= alumno.getId( ) %>" data-nombre="<%= alumno.getNombre( ) %>" data-edad="<%= alumno.getEdad( ) %>"
                                    data-promedio="<%= alumno.getPromedio( ) %>" >
                                Editar
                            </button>
                            <!-- BOTÓN BORRAR -->
                            <form action="AlumnoServlet" method="post" style="display:inline;">
                                <input type="hidden" name="accion" value="delete">
                                <input type="hidden" name="id" value="<%= alumno.getId( ) %>">
                                <button type="submit" class="btn btn-danger btn-sm"
                                        onclick="return confirm('¿Seguro que deseas borrar este alumno?');">
                                    Borrar
                                </button>
                            </form>
                        </td>
                    </tr>
                </tbody>
        <%
            }
        %>
    </table>
    <a href="index.jsp" class="btn btn-secondary">Home</a>
</div>
<!-- Popup / Modal con formulario -->
<div class="modal fade" id="alumnoModal" tabindex="-1" aria-labelledby="alumnoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="alumnoModalLabel">Formulario de alumno</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <form action="AlumnoServlet" method="post" id="formAlumno">
                <input type="hidden" id="accion" name="accion" value="save" />
                <input type="hidden" id="id" name="id" value="">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>

                    <div class="mb-3">
                        <label for="edad" class="form-label">Edad</label>
                        <input type="number" class="form-control" id="edad" name="edad" required>
                    </div>

                    <div class="mb-3">
                        <label for="promedio" class="form-label">Promedio</label>
                        <input type="number" step="0.01" class="form-control" id="promedio" name="promedio" required>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cerrar
                    </button>

                    <button type="submit" class="btn btn-primary" id="btnGuardar">
                        Guardar alumno
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<!-- JAVASCRIPT -->

<script>
    function nuevoAlumno()
    {
        document.getElementById("formAlumno").reset();
        document.getElementById("id").value = "0";
        document.getElementById("accion").value = "save";
        document.getElementById("alumnoModalLabel").textContent = "Agregar alumno";
        document.getElementById("btnGuardar").textContent = "Guardar alumno";
    }
    const modalAlumno = document.getElementById("alumnoModal");
    modalAlumno.addEventListener("show.bs.modal",
        function(event)
        {
            const boton = event.relatedTarget;
            if (!boton || !boton.classList.contains("btn-editar"))
            {
                return;
            }
            const id = boton.getAttribute("data-id");
            const nombre = boton.getAttribute("data-nombre");
            const edad = boton.getAttribute("data-edad");
            const promedio = boton.getAttribute("data-promedio");
            document.getElementById("id").value = id;
            document.getElementById("nombre").value = nombre;
            document.getElementById("edad").value = edad;
            document.getElementById("promedio").value = promedio;
            document.getElementById("accion").value = "update";
            document.getElementById("alumnoModalLabel").textContent = "Editar alumno";
            document.getElementById("btnGuardar").textContent = "Actualizar alumno";
        }
    );
</script>
</body>
</html>