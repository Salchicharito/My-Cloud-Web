package org.gerdoc.servlet;

import org.gerdoc.model.Alumno;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.model.Materia;
import org.gerdoc.service.AlumnoMateriaService;
import org.gerdoc.service.AlumnoService;
import org.gerdoc.service.MateriaService;
import org.gerdoc.service.impl.AlumnoMateriaServiceImpl;
import org.gerdoc.service.impl.AlumnoServiceImpl;
import org.gerdoc.service.impl.MateriaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/AlumnoMateriaServlet")
public class AlumnoMateriaServlet extends HttpServlet
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final AlumnoMateriaService alumnoMateriaService = new AlumnoMateriaServiceImpl( );
    private final AlumnoService alumnoService = new AlumnoServiceImpl( );
    private final MateriaService materiaService = new MateriaServiceImpl( );

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String accion = req.getParameter("accion");
        if ("delete".equals(accion))
        {
            Long id = Long.parseLong( req.getParameter("id" ) );
            alumnoMateriaService.deleteById( id );
            resp.sendRedirect("AlumnoMaterias.jsp" );
            return;
        }
        Long alumnoId = Long.parseLong( req.getParameter("alumnoId" ) );
        Long materiaId = Long.parseLong( req.getParameter("materiaId" ) );
        Double calificacion = Double.parseDouble( req.getParameter("calificacion" ) );
        Long id = Long.parseLong( req.getParameter("id" ) );
        Alumno alumno = alumnoService.findById( alumnoId );
        Materia materia = materiaService.findById( materiaId );

        AlumnoMateria alumnoMateria = AlumnoMateria.builder( )
                .alumno(alumno)
                .materia(materia)
                .calificacion(calificacion)
                .build();
        if( "update".equals(accion) )
        {
            alumnoMateria.setId( id );
            alumnoMateriaService.update( alumnoMateria );
        }
        else
        {
            alumnoMateriaService.save( alumnoMateria );
        }
        resp.sendRedirect("AlumnoMaterias.jsp");
    }
}
