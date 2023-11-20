/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//
///**
// *
// * @author invitado
// */
@Service
public class AppServicio{
    
@Autowired
private JavaMailSender mailSender;
public void sendEmail(String toEmail, String subject, String body){
////usar mailSender acá…
//String from = "sender@gmail.com";//dirección de correo que hace el envío.
//String to = "recipient@gmail.com";//dirección de correo que recibe el
//
//public void sendEmail(String from, String to) {
SimpleMailMessage message = new SimpleMailMessage();
message.setFrom("facio.gabrielemiliano@gmail.com");
message.setTo(toEmail);
message.setSubject("Asunto del correo");
message.setText("Este es un correo automático!");
mailSender.send(message); //método Send(envio), propio de Java Mail
System.out.println("Mail sent successfully...");

}
}

