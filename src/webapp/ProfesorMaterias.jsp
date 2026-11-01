<%@ page import="org.gerdoc.service.ProfesorMateriaService" %>
<%@ page import="org.gerdoc.service.impl.ProfesorMateriaServiceImpl" %>
<%@ page import="org.gerdoc.model.ProfesorMateria" %>
<%@ page import="java.util.List" %>
<%@ page import="org.gerdoc.model.Profesor" %>
<%@ page import="org.gerdoc.service.ProfesorService" %>
<%@ page import="org.gerdoc.service.impl.ProfesorServiceImpl" %>
<%@ page import="org.gerdoc.service.impl.MateriaServiceImpl" %>
<%@ page import="org.gerdoc.service.MateriaService" %>
<%@ page import="org.gerdoc.model.Materia" %>
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
    <h1>ProfesorMaterias</h1>
    <%@ include file="Mensajes.jspf" %>
    <%
        ProfesorMateriaService profesorMateriaService = new ProfesorMateriaServiceImpl( );
        List<ProfesorMateria> profesorMaterias = profesorMateriaService.findAll( );
        ProfesorService profesorService = new ProfesorServiceImpl( );
        List<Profesor> profesores = profesorService.findAll( );

        MateriaService materiaService = new MateriaServiceImpl( );
        List<Materia> materias = materiaService.findAll( );
    %>
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#profesorMateriaModal" onclick="nuevoProfesorMateria()" >
        Agregar profesorMateria
    </button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Profesor</th>
            <th scope="col">Materia</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <%
            int i = 0;
            for (ProfesorMateria profesorMateria : profesorMaterias)
            {
        %>
                <tbody>
                    <tr>
                        <th scope="row"><%=++i%></th>
                        <td>
                            <a href="ProfesorMateria.jsp?id=<%= profesorMateria.getId( ) %>" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                                <%= profesorMateria.getProfesor( ).getNombre() %>
                            </a>
                        </td>
                        <td><%= profesorMateria.getMateria( ).getNombre()%></td>
                        <td>
                            <!-- BOTÓN EDITAR -->

                            <button type="button" class="btn btn-warning btn-sm btn-editar" data-bs-toggle="modal" data-bs-target="#profesorMateriaModal"
                                    data-id="<%= profesorMateria.getId( ) %>" data-profesorId="<%= profesorMateria.getProfesor( ).getId() %>" data-materiaId="<%= profesorMateria.getMateria( ).getId( ) %>"
                                    >
                                Editar
                            </button>
                            <!-- BOTÓN BORRAR -->
                            <form action="ProfesorMateriaServlet" method="post" style="display:inline;">
                                <input type="hidden" name="accion" value="delete">
                                <input type="hidden" name="id" value="<%= profesorMateria.getId( ) %>">
                                <button type="submit" class="btn btn-danger btn-sm"
                                        onclick="return confirm('¿Seguro que deseas borrar este profesorMateria?');">
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
<div class="modal fade" id="profesorMateriaModal" tabindex="-1" aria-labelledby="profesorMateriaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="profesorMateriaModalLabel">Formulario de Profesor Materia</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <form action="ProfesorMateriaServlet" method="post" id="formProfesorMateria">
                <input type="hidden" id="accion" name="accion" value="save" />
                <input type="hidden" id="id" name="id" value="">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="profesorId" class="form-label">Profesor</label>
                        <select class="form-select" id="profesorId" name="profesorId" required>
                            <option value="">Selecciona un profesor</option>
                            <%
                                for (Profesor profesor : profesores)
                                {
                            %>
                            <option value="<%= profesor.getId( ) %>">
                                <%= profesor.getNombre( ) %> - <%= profesor.getEspecialidad( ) %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="materiaId" class="form-label">Materia</label>
                        <select class="form-select" id="materiaId" name="materiaId" required>
                            <option value="">Selecciona una materia</option>
                            <%
                                for (Materia materia : materias)
                                {
                            %>
                            <option value="<%= materia.getId( ) %>">
                                <%= materia.getNombre( ) %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cerrar
                    </button>
                    <button type="submit" class="btn btn-primary" id="btnGuardar">
                        Guardar Profesor Materia
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<!-- JAVASCRIPT -->

<script>
    function nuevoProfesorMateria()
    {
        document.getElementById("formProfesorMateria").reset();
        document.getElementById("id").value = "0";
        document.getElementById("accion").value = "save";
        document.getElementById("profesorMateriaModalLabel").textContent = "Agregar Profesor Materia";
        document.getElementById("btnGuardar").textContent = "Guardar Profesor Materia";
    }
    const modalProfesorMateria = document.getElementById("profesorMateriaModal");
    modalProfesorMateria.addEventListener("show.bs.modal",
        function(event)
        {
            const boton = event.relatedTarget;
            if (!boton || !boton.classList.contains("btn-editar"))
            {
                return;
            }
            const id = boton.getAttribute( "data-id" );
            const profesorId = boton.getAttribute( "data-profesorId" );
            const materiaId = boton.getAttribute( "data-materiaId" );
            document.getElementById( "id" ).value = id;
            document.getElementById( "profesorId" ).value = profesorId;
            document.getElementById( "materiaId" ).value = materiaId;
            document.getElementById( "accion" ).value = "update";
            document.getElementById( "profesorMateriaModalLabel" ).textContent = "Editar Profesor Materia";
            document.getElementById( "btnGuardar" ).textContent = "Actualizar Profesor Materia";
        }
    );
</script>
</body>
</html>