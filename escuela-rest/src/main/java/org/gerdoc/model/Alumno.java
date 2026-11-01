package org.gerdoc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ALUMNO")
public class Alumno
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOMBRE")
    private String nombre;

    @NotNull
    @Min( 0 )
    @Column(name = "EDAD")
    private Integer edad;

    @NotNull
    @DecimalMin( "0.0" )
    @DecimalMax( "10.0" )
    @Column(name = "PROMEDIO")
    private Double promedio;
}