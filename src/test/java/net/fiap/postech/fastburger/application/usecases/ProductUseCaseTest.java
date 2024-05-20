package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import net.fiap.postech.fastburger.application.ports.outputports.product.DeleteProductOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.product.FindProductByCategoryOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.product.SaveProductOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.product.UpdateProductOutPutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductUseCaseTest {
    private SaveProductOutPutPort saveProductOutPutPort;
    private UpdateProductOutPutPort updateProductOutPutPort;
    private DeleteProductOutPutPort deleteProductOutPutPort;
    private FindProductByCategoryOutPutPort findProductByCategoryOutPutPort;
    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        saveProductOutPutPort = mock(SaveProductOutPutPort.class);
        updateProductOutPutPort = mock(UpdateProductOutPutPort.class);
        deleteProductOutPutPort = mock(DeleteProductOutPutPort.class);
        findProductByCategoryOutPutPort = mock(FindProductByCategoryOutPutPort.class);
        productUseCase = new ProductUseCase(saveProductOutPutPort, updateProductOutPutPort, deleteProductOutPutPort, findProductByCategoryOutPutPort);
    }

    @Test
    void delete() {
        String id = "1";
        productUseCase.delete(id);
        verify(deleteProductOutPutPort, times(1)).delete(id);
    }

    @Test
    void save() {
        Product product = new Product();
        product.setPrice(10.0);
        when(saveProductOutPutPort.save(product)).thenReturn(product);

        Product result = productUseCase.save(product);

        assertEquals(product, result);
        verify(saveProductOutPutPort, times(1)).save(product);
    }

    @Test
    void save_withInvalidPrice() {
        Product product = new Product();
        product.setPrice(0.0); // set an invalid price

        assertThrows(BusinessException.class, () -> productUseCase.save(product));
        verify(saveProductOutPutPort, never()).save(product); // ensure save method was not called
    }

    @Test
    void update() {
        String id = "1";
        Product product = new Product();
        product.setPrice(10.0);
        when(updateProductOutPutPort.update(id, product)).thenReturn(product);

        Product result = productUseCase.update(id, product);

        assertEquals(product, result);
        verify(updateProductOutPutPort, times(1)).update(id, product);
    }

    @Test
    void update_withInvalidPrice() {
        String id = "1";
        Product product = new Product();
        product.setPrice(0.0); // set an invalid price

        assertThrows(BusinessException.class, () -> productUseCase.update(id, product));
        verify(updateProductOutPutPort, never()).update(id, product); // ensure update method was not called
    }

    @Test
    void find() {
        CategoryEnum categoryEnum = CategoryEnum.BURGERS;
        List<Product> products = Collections.singletonList(new Product());
        when(findProductByCategoryOutPutPort.find(categoryEnum)).thenReturn(products);

        List<Product> result = productUseCase.find(categoryEnum);

        assertEquals(products, result);
        verify(findProductByCategoryOutPutPort, times(1)).find(categoryEnum);
    }
}