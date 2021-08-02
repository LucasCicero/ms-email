package com.ms.email.models;

import com.ms.email.enums.StatusEmail;
import lombok.Data;// com o lombok não precisa colocar os metodos get, set e construtores

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long emailId;
    private String ownerRef;// passa o id do usuario pra quem vai ser enviado o  e-mail
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT") // inserir mais caracteres se for preciso
    private String text;// corpo do e-mail
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail; // sucesso(enviado) ou erro
}
