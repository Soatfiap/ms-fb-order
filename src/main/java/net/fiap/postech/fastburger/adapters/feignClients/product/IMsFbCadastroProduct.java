package net.fiap.postech.fastburger.adapters.feignClients.product;

import net.fiap.postech.fastburger.adapters.persistence.dto.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductResponseDTO;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMsFbCadastroProduct {

    ProductDTO saveProduct(ProductDTO productDTO);
    List<ProductResponseDTO> findProductByCategory(CategoryEnum categoryEnum);
    ProductResponseDTO updateProduct(String sku, ProductDTO productDTO);
    ProductResponseDTO findProductById(String id);
    void deleteProductBySKU(String sku);
}
