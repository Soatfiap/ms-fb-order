package net.fiap.postech.fastburger.application.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String cpf;
    private String nome;
    private String email;
}
