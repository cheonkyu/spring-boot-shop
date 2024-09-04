package app.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import app.shop.controller.dto.CreateOrderDto;
import app.shop.core.BaseService;
import app.shop.domain.entity.Order;
import app.shop.domain.repository.OrderItemRepository;
import app.shop.domain.repository.OrderRepository;
import app.shop.domain.repository.OrdererRepository;

@RequiredArgsConstructor
@Service
public class OrderService extends BaseService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrdererRepository ordererRepository;

    public Order findById(Long id) {
        // return null;
        return orderRepository.findById(id).get();
    }

    public void createOrder(CreateOrderDto createOrderDto) {
        logger.info("createOrderDto: {}", createOrderDto);
        Order order = createOrderDto.toEntity();
        logger.info("OrderItems: {}", order.getOrderItems());
        logger.info("Orderer {}", order.getOrderer());
        // ordererRepository.save(order.getOrderer());
        orderRepository.save(order);
    }
}
