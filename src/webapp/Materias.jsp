<%@ page import="org.gerdoc.service.MateriaService" %>
<%@ page import="org.gerdoc.service.impl.MateriaServiceImpl" %>
<%@ page import="org.gerdoc.model.Materia" %>
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
    <h1>Materias</h1>
    <%@ include file="Mensajes.jspf" %>
    <%
        MateriaService materiaService = new MateriaServiceImpl( );
        List<Materia> materias = materiaService.findAll( );
    %>
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#materiaModal" onclick="nuevoMateria()" >
        Agregar Materias
    </button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre</th>
            <th scope="col">Créditos</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <%
            int i = 0;
            for (Materia materia : materias)
            {
        %>
                <tbody>
                    <tr>
                        <th scope="row"><%=++i%></th>
                        <td>
                            <a href="Materia.jsp?id=<%= materia.getId( ) %>" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                                <%= materia.getNombre( ) %>
                            </a>
                        </td>
                        <td><%= materia.getCreditos( )%></td>
                        <td>
                            <!-- BOTÓN EDITAR -->
                            <button type="button" class="btn btn-warning btn-sm btn-editar" data-bs-toggle="modal" data-bs-target="#materiaModal"
                                    data-id="<%= materia.getId( ) %>" data-nombre="<%= materia.getNombre( ) %>" data-creditos="<%= materia.getCreditos( ) %>" >
                                Editar
                            </button>
                            <!-- BOTÓN BORRAR -->
                            <form action="MateriaServlet" method="post" style="display:inline;">
                                <input type="hidden" name="accion" value="delete">
                                <input type="hidden" name="id" value="<%= materia.getId( ) %>">
                                <button type="submit" class="btn btn-danger btn-sm"
                                        onclick="return confirm('¿Seguro que deseas borrar este materia?');">
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
<div class="modal fade" id="materiaModal" tabindex="-1" aria-labelledby="materiaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="materiaModalLabel">Formulario de materia</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <form action="MateriaServlet" method="post" id="formMateria">
                <input type="hidden" id="accion" name="accion" value="save" />
                <input type="hidden" id="id" name="id" value="">
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>

                    <div class="mb-3">
                        <label for="creditos" class="form-label">Créditos</label>
                        <input type="text" class="form-control" id="creditos" name="creditos" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cerrar
                    </button>
                    <button type="submit" class="btn btn-primary" id="btnGuardar">
                        Guardar materia
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<!-- JAVASCRIPT -->

<script>
    function nuevoMateria()
    {
        document.getElementById("formMateria").reset();
        document.getElementById("id").value = "0";
        document.getElementById("accion").value = "save";
        document.getElementById("materiaModalLabel").textContent = "Agregar materia";
        document.getElementById("btnGuardar").textContent = "Guardar materia";
    }
    const modalMateria = document.getElementById("materiaModal");
    modalMateria.addEventListener("show.bs.modal",
        function(event)
        {
            const boton = event.relatedTarget;
            if (!boton || !boton.classList.contains("btn-editar"))
            {
                return;
            }
            const id = boton.getAttribute("data-id");
            const nombre = boton.getAttribute("data-nombre");
            const creditos = boton.getAttribute("data-creditos");
            document.getElementById("id").value = id;
            document.getElementById("nombre").value = nombre;
            document.getElementById("creditos").value = creditos;
            document.getElementById("accion").value = "update";
            document.getElementById("materiaModalLabel").textContent = "Editar materia";
            document.getElementById("btnGuardar").textContent = "Actualizar materia";
        }
    );
</script>
</body>
</html>