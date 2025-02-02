package com.demo.controllers;

import com.demo.dtos.Email;
import com.demo.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private MailService mailService;

    @GetMapping("findAll")
    public String findAll() {
        return "findAll notification";
    }


    @PostMapping(value = "sendEmail",  produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmail(@RequestBody Email email) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = mailService.send(email.getFrom(), email.getTo(), email.getSubject(), email.getContent());
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
