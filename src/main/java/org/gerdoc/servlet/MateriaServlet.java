package org.gerdoc.servlet;

import org.gerdoc.model.Materia;
import org.gerdoc.service.MateriaService;
import org.gerdoc.service.impl.MateriaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/MateriaServlet")
public class MateriaServlet extends HttpServlet
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final MateriaService materiaService = new MateriaServiceImpl( );

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String accion = req.getParameter("accion");
        if ("delete".equals(accion))
        {
            Long id = Long.parseLong( req.getParameter("id" ) );
            boolean borrado = materiaService.deleteById( id );
            resp.sendRedirect( borrado ? "Materias.jsp" : "Materias.jsp?error=delete" );
            return;
        }
        String nombre = req.getParameter("nombre" );
        Integer creditos = Integer.parseInt( req.getParameter("creditos" ) );
        Long id = Long.parseLong( req.getParameter("id" ) );
        Materia materia = Materia.builder( )
                .nombre( nombre )
                .creditos( creditos )
                .build();
        boolean guardado;
        if( "update".equals( accion ) )
        {
            materia.setId( id );
            guardado = materiaService.update( materia );
        }
        else
        {
            guardado = materiaService.save( materia );
        }
        resp.sendRedirect( guardado ? "Materias.jsp" : "Materias.jsp?error=save" );
    }
}
