package com.egg.biblioteca.entidades;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author invitado
 */
@Table(name = "Libro")
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Libro {
    
    @Id
    private Long isbn;
    private String titulo;
    private Integer ejemplares;
    
    @Temporal(TemporalType.DATE)
    private Date alta;

    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;
    @OneToOne
    private Imagen imagen;

    
    
}
