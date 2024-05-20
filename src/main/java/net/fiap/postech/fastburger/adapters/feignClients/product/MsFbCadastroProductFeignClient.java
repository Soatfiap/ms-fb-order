package net.fiap.postech.fastburger.adapters.feignClients.product;

import jakarta.validation.Valid;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductResponseDTO;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cadastroProduct", url = "${MS-FB-CADASTRO}")
public interface MsFbCadastroProductFeignClient {

    @PostMapping("/api/v1/product")
    ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO);

    @GetMapping("/api/v1/product")
    ResponseEntity<List<ProductResponseDTO>> findProductByCategory(@RequestParam("category") CategoryEnum categoryEnum);

    @PutMapping("/api/v1/product/{sku}")
    ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("sku") String sku, @RequestBody @Valid ProductDTO productDTO);

    @GetMapping("/api/v1/product/{id}")
    ResponseEntity<ProductResponseDTO> findProductById(@PathVariable("id") String id);

    @DeleteMapping("/api/v1/product/{sku}")
    ResponseEntity<Void> deleteProductBySKU(@PathVariable("sku") String sku);
}
