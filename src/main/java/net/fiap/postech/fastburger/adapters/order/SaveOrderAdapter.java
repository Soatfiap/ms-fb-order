package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.SaveOrderOutPutPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class SaveOrderAdapter implements SaveOrderOutPutPort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RabbitTemplate rabbitTemplate;
    private static final String ORDER_EXCHANGE = "orderExchange";
    private static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    @Autowired
    public SaveOrderAdapter(OrderRepository orderRepository, OrderMapper orderMapper, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Order save(Order order) {
        var lastOrderId = this.orderRepository.getLastOrderId();

        if (lastOrderId == null || lastOrderId == 0) {
            order.setOrderNumber(generateOrderNumber().concat(BigDecimal.ONE.toString()));
        } else {
            order.setOrderNumber(generateOrderNumber().concat((BigDecimal.ONE.longValue() + lastOrderId) + ""));
        }
        OrderEntity orderSaved = Optional.of(this.orderRepository.save(this.orderMapper.orderToOrderEntity(order))).orElseThrow(() -> new BusinessException("Erro ao salvar Pedido"));
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CREATED_ROUTING_KEY, orderSaved.toString());
        return this.orderMapper.orderEntityToOrder(orderSaved);
    }

    private String generateOrderNumber() {
        return String.format("%02d%02d%d", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }
}
