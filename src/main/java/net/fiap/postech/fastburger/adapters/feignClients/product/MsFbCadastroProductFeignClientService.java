package net.fiap.postech.fastburger.adapters.feignClients.product;

import net.fiap.postech.fastburger.adapters.persistence.dto.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductResponseDTO;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsFbCadastroProductFeignClientService implements IMsFbCadastroProduct {
    private final MsFbCadastroProductFeignClient msFbCadastroProductFeignClient;

    public MsFbCadastroProductFeignClientService(MsFbCadastroProductFeignClient msFbCadastroProductFeignClient) {
        this.msFbCadastroProductFeignClient = msFbCadastroProductFeignClient;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        ResponseEntity<ProductDTO> productDTOResponseEntity = this.msFbCadastroProductFeignClient.saveProduct(productDTO);
        return productDTOResponseEntity.getBody();
    }

    @Override
    public List<ProductResponseDTO> findProductByCategory(CategoryEnum categoryEnum) {
        return this.msFbCadastroProductFeignClient.findProductByCategory(categoryEnum).getBody();
    }

    @Override
    public ProductResponseDTO updateProduct(String sku, ProductDTO productDTO) {
        return this.msFbCadastroProductFeignClient.updateProduct(sku, productDTO).getBody();
    }

    @Override
    public ProductResponseDTO findProductById(String id) {
        return this.msFbCadastroProductFeignClient.findProductById(id).getBody();
    }

    @Override
    public void deleteProductBySKU(String sku) {
        this.msFbCadastroProductFeignClient.deleteProductBySKU(sku);
    }
}
