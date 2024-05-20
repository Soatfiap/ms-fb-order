package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static net.fiap.postech.fastburger.application.domain.enums.StatusOrder.READY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderUseCaseTest {
    private DeleteOrderOutPutPort deleteOrderOutPutPort;
    private ListOrderByNumberOutPutPort listOrderByNumberOutPutPort;
    private ListOrdersOutPutPort listOrderOutPutPort;
    private SaveOrderOutPutPort saveOrderOutPutPort;
    private UpdateOrderOutPutPort updateOrderOutPutPort;
    private ListOrderByIdOutPutPort listOrderByIdOutPutPort;
    private ListOrderByStatusOutPutPort listOrderByStatusOutPutPort;
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        deleteOrderOutPutPort = mock(DeleteOrderOutPutPort.class);
        listOrderByNumberOutPutPort = mock(ListOrderByNumberOutPutPort.class);
        listOrderOutPutPort = mock(ListOrdersOutPutPort.class);
        saveOrderOutPutPort = mock(SaveOrderOutPutPort.class);
        updateOrderOutPutPort = mock(UpdateOrderOutPutPort.class);
        listOrderByIdOutPutPort = mock(ListOrderByIdOutPutPort.class);
        listOrderByStatusOutPutPort = mock(ListOrderByStatusOutPutPort.class);
        orderUseCase = new OrderUseCase(deleteOrderOutPutPort, listOrderByNumberOutPutPort, listOrderOutPutPort, saveOrderOutPutPort, updateOrderOutPutPort, listOrderByIdOutPutPort, listOrderByStatusOutPutPort);
    }

    @Test
    void delete() {
        String id = "1";
        orderUseCase.delete(id);
        verify(deleteOrderOutPutPort, times(1)).delete(id);
    }

    @Test
    void listByNumber() {
        String number = "123";
        Order order = new Order();
        when(listOrderByNumberOutPutPort.listByNumber(number)).thenReturn(order);

        Order result = orderUseCase.listByNumber(number);

        assertEquals(order, result);
        verify(listOrderByNumberOutPutPort, times(1)).listByNumber(number);
    }

    @Test
    void list() {
        List<Order> orders = Collections.singletonList(new Order());
        when(listOrderOutPutPort.list()).thenReturn(orders);

        List<Order> result = orderUseCase.list();

        assertEquals(orders, result);
        verify(listOrderOutPutPort, times(1)).list();
    }

    @Test
    void save() {
        Order order = new Order();
        when(saveOrderOutPutPort.save(order)).thenReturn(order);

        Order result = orderUseCase.save(order);

        assertEquals(order, result);
        verify(saveOrderOutPutPort, times(1)).save(order);
    }

    @Test
    void update() {
        String id = "1";
        Order order = new Order();
        when(updateOrderOutPutPort.update(id, order)).thenReturn(order);

        Order result = orderUseCase.update(id, order);

        assertEquals(order, result);
        verify(updateOrderOutPutPort, times(1)).update(id, order);
    }

    @Test
    void listById() {
        String id = "1";
        Order order = new Order();
        when(listOrderByIdOutPutPort.listById(id)).thenReturn(order);

        Order result = orderUseCase.listById(id);

        assertEquals(order, result);
        verify(listOrderByIdOutPutPort, times(1)).listById(id);
    }

    @Test
    void listByStatus() {
        String status = "OPEN";
        List<Order> orders = Collections.singletonList(new Order());
        when(listOrderByStatusOutPutPort.listByStatus(READY)).thenReturn(orders);

        List<Order> result = orderUseCase.listByStatus(READY);

        assertEquals(orders, result);
        verify(listOrderByStatusOutPutPort, times(1)).listByStatus(READY);
    }
}