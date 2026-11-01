package org.gerdoc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Prueba los 5 catálogos REST y revisa directamente en la base de datos
 * (con JdbcTemplate) que cada operación quedó reflejada.
 * Antes de cada prueba se recarga la BD con los datos de sql/Escuela.sql.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Sql( "/escuela.sql" )
class CatalogosRestTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbc;

    private int contar( String sql, Object... parametros )
    {
        return jdbc.queryForObject( sql, Integer.class, parametros );
    }

    // ---------- ALUMNO ----------

    @Test
    void alumnoCrudCompleto( ) throws Exception
    {
        mockMvc.perform( get( "/api/alumnos" ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( jsonPath( "$", hasSize( 3 ) ) );

        mockMvc.perform( post( "/api/alumnos" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"nombre\":\"LUIS\",\"edad\":21,\"promedio\":8.5}" ) )
                .andExpect( status( ).isCreated( ) )
                .andExpect( jsonPath( "$.id" ).value( 4 ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO WHERE NOMBRE = 'LUIS'" ) ).isEqualTo( 1 );

        mockMvc.perform( put( "/api/alumnos/4" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"nombre\":\"LUIS PEREZ\",\"edad\":22,\"promedio\":9.0}" ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( jsonPath( "$.nombre" ).value( "LUIS PEREZ" ) );
        assertThat( jdbc.queryForObject( "SELECT NOMBRE FROM ALUMNO WHERE ID = 4", String.class ) ).isEqualTo( "LUIS PEREZ" );

        mockMvc.perform( delete( "/api/alumnos/4" ) ).andExpect( status( ).isNoContent( ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO WHERE ID = 4" ) ).isZero( );

        mockMvc.perform( get( "/api/alumnos/4" ) ).andExpect( status( ).isNotFound( ) );
    }

    @Test
    void noSePuedeBorrarAlumnoConMateriasInscritas( ) throws Exception
    {
        mockMvc.perform( delete( "/api/alumnos/1" ) )
                .andExpect( status( ).isConflict( ) )
                .andExpect( jsonPath( "$.mensaje", containsString( "ALUMNO_MATERIA" ) ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO WHERE ID = 1" ) ).isEqualTo( 1 );
    }

    @Test
    void sePuedeBorrarAlumnoDespuesDeBorrarSusInscripciones( ) throws Exception
    {
        // El alumno 3 (MARIA) tiene las inscripciones 6 y 7
        mockMvc.perform( delete( "/api/alumno-materias/6" ) ).andExpect( status( ).isNoContent( ) );
        mockMvc.perform( delete( "/api/alumno-materias/7" ) ).andExpect( status( ).isNoContent( ) );
        mockMvc.perform( delete( "/api/alumnos/3" ) ).andExpect( status( ).isNoContent( ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO WHERE ID = 3" ) ).isZero( );
    }

    @Test
    void alumnoConDatosIncompletosRegresa400( ) throws Exception
    {
        mockMvc.perform( post( "/api/alumnos" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"edad\":21}" ) )
                .andExpect( status( ).isBadRequest( ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO" ) ).isEqualTo( 3 );
    }

    // ---------- MATERIA ----------

    @Test
    void materiaCrudCompleto( ) throws Exception
    {
        mockMvc.perform( get( "/api/materias" ) ).andExpect( jsonPath( "$", hasSize( 5 ) ) );

        mockMvc.perform( post( "/api/materias" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"nombre\":\"SISTEMAS OPERATIVOS\",\"creditos\":6}" ) )
                .andExpect( status( ).isCreated( ) )
                .andExpect( jsonPath( "$.id" ).value( 6 ) );

        mockMvc.perform( put( "/api/materias/6" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"nombre\":\"SISTEMAS OPERATIVOS\",\"creditos\":8}" ) )
                .andExpect( status( ).isOk( ) );
        assertThat( contar( "SELECT CREDITOS FROM MATERIA WHERE ID = 6" ) ).isEqualTo( 8 );

        mockMvc.perform( delete( "/api/materias/6" ) ).andExpect( status( ).isNoContent( ) );
        assertThat( contar( "SELECT COUNT(*) FROM MATERIA WHERE ID = 6" ) ).isZero( );
    }

    @Test
    void noSePuedeBorrarMateriaConAlumnosOProfesores( ) throws Exception
    {
        mockMvc.perform( delete( "/api/materias/1" ) )
                .andExpect( status( ).isConflict( ) );
        // La materia 5 (DESARROLLO WEB) solo la tiene asignada un profesor
        mockMvc.perform( delete( "/api/materias/5" ) )
                .andExpect( status( ).isConflict( ) )
                .andExpect( jsonPath( "$.mensaje", containsString( "PROFESOR_MATERIA" ) ) );
        assertThat( contar( "SELECT COUNT(*) FROM MATERIA" ) ).isEqualTo( 5 );
    }

    // ---------- PROFESOR ----------

    @Test
    void profesorCrudCompleto( ) throws Exception
    {
        mockMvc.perform( get( "/api/profesores" ) ).andExpect( jsonPath( "$", hasSize( 4 ) ) );

        mockMvc.perform( post( "/api/profesores" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"nombre\":\"LAURA DIAZ\",\"especialidad\":\"FISICA\"}" ) )
                .andExpect( status( ).isCreated( ) )
                .andExpect( jsonPath( "$.id" ).value( 5 ) );

        mockMvc.perform( put( "/api/profesores/5" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"nombre\":\"LAURA DIAZ\",\"especialidad\":\"QUIMICA\"}" ) )
                .andExpect( status( ).isOk( ) );
        assertThat( jdbc.queryForObject( "SELECT ESPECIALIDAD FROM PROFESOR WHERE ID = 5", String.class ) ).isEqualTo( "QUIMICA" );

        mockMvc.perform( delete( "/api/profesores/5" ) ).andExpect( status( ).isNoContent( ) );
        assertThat( contar( "SELECT COUNT(*) FROM PROFESOR WHERE ID = 5" ) ).isZero( );
    }

    @Test
    void noSePuedeBorrarProfesorConMateriasAsignadas( ) throws Exception
    {
        mockMvc.perform( delete( "/api/profesores/1" ) )
                .andExpect( status( ).isConflict( ) )
                .andExpect( jsonPath( "$.mensaje", containsString( "PROFESOR_MATERIA" ) ) );
        assertThat( contar( "SELECT COUNT(*) FROM PROFESOR WHERE ID = 1" ) ).isEqualTo( 1 );
    }

    // ---------- ALUMNO_MATERIA ----------

    @Test
    void alumnoMateriaCrudCompleto( ) throws Exception
    {
        mockMvc.perform( get( "/api/alumno-materias" ) ).andExpect( jsonPath( "$", hasSize( 7 ) ) );

        // CARLOS (2) se inscribe a DESARROLLO WEB (5)
        mockMvc.perform( post( "/api/alumno-materias" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"alumno\":{\"id\":2},\"materia\":{\"id\":5},\"calificacion\":7.5}" ) )
                .andExpect( status( ).isCreated( ) )
                .andExpect( jsonPath( "$.id" ).value( 8 ) )
                .andExpect( jsonPath( "$.alumno.nombre" ).value( "CARLOS" ) )
                .andExpect( jsonPath( "$.materia.nombre" ).value( "DESARROLLO WEB" ) );

        mockMvc.perform( put( "/api/alumno-materias/8" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"alumno\":{\"id\":2},\"materia\":{\"id\":5},\"calificacion\":9.5}" ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( jsonPath( "$.calificacion" ).value( 9.5 ) );
        assertThat( jdbc.queryForObject( "SELECT CALIFICACION FROM ALUMNO_MATERIA WHERE ID = 8", Double.class ) ).isEqualTo( 9.5 );

        mockMvc.perform( delete( "/api/alumno-materias/8" ) ).andExpect( status( ).isNoContent( ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO_MATERIA WHERE ID = 8" ) ).isZero( );
    }

    @Test
    void alumnoMateriaDuplicadaRegresa409( ) throws Exception
    {
        // ANA (1) ya está inscrita en la materia 1 (UNIQUE ALUMNO_ID, MATERIA_ID)
        mockMvc.perform( post( "/api/alumno-materias" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"alumno\":{\"id\":1},\"materia\":{\"id\":1},\"calificacion\":7}" ) )
                .andExpect( status( ).isConflict( ) );
        assertThat( contar( "SELECT COUNT(*) FROM ALUMNO_MATERIA" ) ).isEqualTo( 7 );
    }

    @Test
    void alumnoMateriaConAlumnoInexistenteRegresa400( ) throws Exception
    {
        mockMvc.perform( post( "/api/alumno-materias" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"alumno\":{\"id\":99},\"materia\":{\"id\":1}}" ) )
                .andExpect( status( ).isBadRequest( ) )
                .andExpect( jsonPath( "$.mensaje", containsString( "99" ) ) );
    }

    // ---------- PROFESOR_MATERIA ----------

    @Test
    void profesorMateriaCrudCompleto( ) throws Exception
    {
        mockMvc.perform( get( "/api/profesor-materias" ) ).andExpect( jsonPath( "$", hasSize( 5 ) ) );

        // MARIA LOPEZ (2) también da DESARROLLO WEB (5)
        mockMvc.perform( post( "/api/profesor-materias" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"profesor\":{\"id\":2},\"materia\":{\"id\":5}}" ) )
                .andExpect( status( ).isCreated( ) )
                .andExpect( jsonPath( "$.id" ).value( 6 ) )
                .andExpect( jsonPath( "$.profesor.nombre" ).value( "MARIA LOPEZ" ) );

        // Se cambia a MATEMATICAS (3)
        mockMvc.perform( put( "/api/profesor-materias/6" ).contentType( MediaType.APPLICATION_JSON )
                        .content( "{\"profesor\":{\"id\":2},\"materia\":{\"id\":3}}" ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( jsonPath( "$.materia.nombre" ).value( "MATEMATICAS" ) );
        assertThat( contar( "SELECT MATERIA_ID FROM PROFESOR_MATERIA WHERE ID = 6" ) ).isEqualTo( 3 );

        mockMvc.perform( delete( "/api/profesor-materias/6" ) ).andExpect( status( ).isNoContent( ) );
        assertThat( contar( "SELECT COUNT(*) FROM PROFESOR_MATERIA WHERE ID = 6" ) ).isZero( );
    }
}
