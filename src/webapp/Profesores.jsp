<%@ page import="org.gerdoc.service.ProfesorService" %>
<%@ page import="org.gerdoc.service.impl.ProfesorServiceImpl" %>
<%@ page import="org.gerdoc.model.Profesor" %>
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
    <h1>Profesores</h1>
    <%@ include file="Mensajes.jspf" %>
    <%
        ProfesorService profesorService = new ProfesorServiceImpl( );
        List<Profesor> profesores = profesorService.findAll( );
    %>
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#profesorModal" onclick="nuevoProfesor()" >
        Agregar profesor
    </button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre</th>
            <th scope="col">Especialidad</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <%
            int i = 0;
            for (Profesor profesor : profesores)
            {
        %>
                <tbody>
                    <tr>
                        <th scope="row"><%=++i%></th>
                        <td>
                            <a href="Profesor.jsp?id=<%= profesor.getId( ) %>" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                                <%= profesor.getNombre( ) %>
                            </a>
                        </td>
                        <td><%= profesor.getEspecialidad( )%></td>
                        <td>
                            <!-- BOTÓN EDITAR -->

                            <button type="button" class="btn btn-warning btn-sm btn-editar" data-bs-toggle="modal" data-bs-target="#profesorModal"
                                    data-id="<%= profesor.getId( ) %>" data-nombre="<%= profesor.getNombre( ) %>" data-especialidad="<%= profesor.getEspecialidad( ) %>" >
                                Editar
                            </button>
                            <!-- BOTÓN BORRAR -->
                            <form action="ProfesorServlet" method="post" style="display:inline;">
                                <input type="hidden" name="accion" value="delete">
                                <input type="hidden" name="id" value="<%= profesor.getId( ) %>">
                                <button type="submit" class="btn btn-danger btn-sm"
                                        onclick="return confirm('¿Seguro que deseas borrar este profesor?');">
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
<div class="modal fade" id="profesorModal" tabindex="-1" aria-labelledby="profesorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="profesorModalLabel">Formulario de profesor</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <form action="ProfesorServlet" method="post" id="formProfesor">
                <input type="hidden" id="accion" name="accion" value="save" />
                <input type="hidden" id="id" name="id" value="">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>

                    <div class="mb-3">
                        <label for="especialidad" class="form-label">Especialidad</label>
                        <input type="text" class="form-control" id="especialidad" name="especialidad" required>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cerrar
                    </button>

                    <button type="submit" class="btn btn-primary" id="btnGuardar">
                        Guardar profesor
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<!-- JAVASCRIPT -->

<script>
    function nuevoProfesor()
    {
        document.getElementById("formProfesor").reset();
        document.getElementById("id").value = "0";
        document.getElementById("accion").value = "save";
        document.getElementById("profesorModalLabel").textContent = "Agregar profesor";
        document.getElementById("btnGuardar").textContent = "Guardar profesor";
    }
    const modalProfesor = document.getElementById("profesorModal");
    modalProfesor.addEventListener("show.bs.modal",
        function(event)
        {
            const boton = event.relatedTarget;
            if (!boton || !boton.classList.contains("btn-editar"))
            {
                return;
            }
            const id = boton.getAttribute("data-id");
            const nombre = boton.getAttribute("data-nombre");
            const especialidad = boton.getAttribute("data-especialidad");
            document.getElementById("id").value = id;
            document.getElementById("nombre").value = nombre;
            document.getElementById("especialidad").value = especialidad;
            document.getElementById("accion").value = "update";
            document.getElementById("profesorModalLabel").textContent = "Editar profesor";
            document.getElementById("btnGuardar").textContent = "Actualizar profesor";
        }
    );
</script>
</body>
</html>