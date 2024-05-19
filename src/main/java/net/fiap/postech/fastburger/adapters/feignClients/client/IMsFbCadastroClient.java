package net.fiap.postech.fastburger.adapters.feignClients.client;

import net.fiap.postech.fastburger.adapters.persistence.dto.ClientResponseDTO;

public interface IMsFbCadastroClient {
    ClientResponseDTO findClientByCpf(String cpf);
}
