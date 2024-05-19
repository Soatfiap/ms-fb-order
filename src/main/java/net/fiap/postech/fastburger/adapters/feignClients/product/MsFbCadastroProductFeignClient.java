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

    @PostMapping("/api/v1/products")
    ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO);

    @GetMapping("/api/v1/products")
    ResponseEntity<List<ProductResponseDTO>> findProductByCategory(@RequestParam("category") CategoryEnum categoryEnum);

    @PutMapping("/api/v1/products/{sku}")
    ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("sku") Long sku, @RequestBody @Valid ProductDTO productDTO);

    @PutMapping("/api/v1/products/{id}")
    ResponseEntity<ProductResponseDTO> findProductById(@PathVariable("id") Long id);

    @DeleteMapping("/api/v1/products/{sku}")
    ResponseEntity<Void> deleteProductBySKU(@PathVariable("sku") Long sku);
}
