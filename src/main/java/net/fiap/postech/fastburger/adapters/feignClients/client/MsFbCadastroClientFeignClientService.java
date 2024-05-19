package net.fiap.postech.fastburger.adapters.feignClients.client;

import net.fiap.postech.fastburger.adapters.persistence.dto.ClientResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MsFbCadastroClientFeignClientService implements IMsFbCadastroClient {
    private final MsFbCadastroClientFeignClient msFbCadastroClientFeignClient;

    public MsFbCadastroClientFeignClientService(MsFbCadastroClientFeignClient msFbCadastroClientFeignClient) {
        this.msFbCadastroClientFeignClient = msFbCadastroClientFeignClient;
    }

    @Override
    public ClientResponseDTO findClientByCpf(String cpf) {
        return this.msFbCadastroClientFeignClient.findClientByCpf(cpf);
    }
}
