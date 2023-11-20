/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.entidades;

import com.egg.biblioteca.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author invitado
 */
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Usuario {
    
     @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy ="uuid2")
    private String id;
    private String nombre;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToOne
    private Imagen imagen;

    public Imagen getImagen() {
        return imagen;
    }

    public Rol getRol() {
        return rol;
    }
    

}
