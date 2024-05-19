package net.fiap.postech.fastburger.adapters.feignClients.client;

import net.fiap.postech.fastburger.adapters.persistence.dto.ClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "cadastroClient", url = "${MS-FB-CADASTRO}")
public interface MsFbCadastroClientFeignClient {

    @GetMapping("/api/v1/client/{cpf}")
    ClientResponseDTO findClientByCpf(@PathVariable("cpf") String cpf);

}
