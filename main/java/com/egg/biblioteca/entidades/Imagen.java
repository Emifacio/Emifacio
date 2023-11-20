/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.entidades;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author invitado
 */
@Data
@NoArgsConstructor
@Entity
public class Imagen {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name= "uuid", strategy = "uuid2")
    private String id;
    private String mime;
    private String nombre;
    //@Lob @Basic(fetch = FetchType.EAGER)
    //@Lob @Column (columnDefinition = "LONGBLOB")
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    public byte[] getContenido() {
        return contenido;
    }
}
