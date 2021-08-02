package com.ms.email.controllers;


import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;
    // metodo post, recebe dto, faz validação e passa do dto para o Model para salvar e enviar o e-mail
    @PostMapping("/sending-email") // sempre que enviar requisição  /sending-email dispara método senddingEmail
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) { // recebe e-mail dto
        // transforma dto em model para salvar no banco de dados
        EmailModel emailModel = new EmailModel(); // cria instância
        BeanUtils.copyProperties(emailDto, emailModel);// converte dto para model
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED); //retorna para o cliente o email salvo e o status
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        Page<EmailModel> emailModelPage = emailService.findAll(pageable);
        if(emailModelPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(emailModelPage, HttpStatus.OK);
        }
    }
}
