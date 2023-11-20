package com.egg.biblioteca;

import com.egg.biblioteca.servicios.AppServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;



@SpringBootApplication
public class BibliotecaApplication {
    @Autowired
    private AppServicio appServicio;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

        @EventListener(ApplicationReadyEvent.class)
        public void sendMail(){
            appServicio.sendEmail("facio.gabrielemiliano@gmail.com","this is the subject","this is the body of the email");
        }
}
