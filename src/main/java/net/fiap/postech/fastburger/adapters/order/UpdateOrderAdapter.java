package net.fiap.postech.fastburger.adapters.order;

import jakarta.transaction.Transactional;
import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.UpdateOrderOutPutPort;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class UpdateOrderAdapter implements UpdateOrderOutPutPort {
    private final OrderRepository orderRepository;
    private final ListOrderByNumberAdapter listOrderByIdAdapter;
    private final OrderMapper orderMapper;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UpdateOrderAdapter(OrderRepository orderRepository, ListOrderByNumberAdapter listOrderByIdAdapter, OrderMapper orderMapper, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.listOrderByIdAdapter = listOrderByIdAdapter;
        this.orderMapper = orderMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    @Transactional
    public Order update(String orderNumber, Order order) {
        OrderEntity orderEntity = this.orderMapper.orderToOrderEntity(order);
        OrderEntity saved = this.orderRepository.save(orderEntity);
        Order order1 = this.orderMapper.orderEntityToOrder(saved);
        return order1;
    }

    @RabbitListener(queues = "saga-payment-order")
    public void processOrderCreated(Order order) {
        order.setWasPaid(true);
        OrderEntity orderEntity = this.orderMapper.orderToOrderEntity(order);
        OrderEntity saved = this.orderRepository.save(orderEntity);
        Order order1 = this.orderMapper.orderEntityToOrder(saved);
        rabbitTemplate.convertAndSend("paymentExchange", "payment.completed", order1.toString());
    }

}
