/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.controladores;


import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author invitado
 */
@Controller
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")// localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        
    
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito!","La editorial fue registrada correctamente!");
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());
        }
      
              

      return "editorial_form.html";
    }
 
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Editorial> editoriales= editorialServicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editoriales);
        
        return "editorial_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("editorial", editorialServicio.getOne(id));
        
        return "editorial_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
        try {
           editorialServicio.modificarEditorial(nombre, id);
            return "redirect:../editorial_list.html";
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "editorial_modificar.html";
        }
        
      
    }
    
    
    
    
    
    
}
