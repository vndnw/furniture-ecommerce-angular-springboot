package com.store.furniture.service;

import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.dto.response.PaginatedResponse;
import com.store.furniture.entity.Order;
import com.store.furniture.entity.OrderItem;
import com.store.furniture.entity.Product;
import com.store.furniture.enums.OrderStatus;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.OrderMapper;
import com.store.furniture.repository.OrderRepository;
import com.store.furniture.repository.ProductRepository;
import com.store.furniture.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    ProductRepository productRepository;

    public OrderResponse createOrder(OrderCreationRequest orderRequest) {
        var user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(itemRequest -> {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice() * itemRequest.getQuantity());
            return orderItem;
        }).toList();

        order.setOrderItems(orderItems);
        order.setTotalAmount(orderItems.stream().mapToDouble(OrderItem::getPrice).sum());

        orderRepository.save(order);
var te = orderMapper.toResponse(order);
        return orderMapper.toResponse(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        order.setStatus(status);
        orderRepository.save(order);

        return orderMapper.toResponse(order);
    }

    public PaginatedResponse<OrderResponse> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var orderPage = orderRepository.findAll(pageable);
        var orders = orderPage.stream().map(orderMapper::toResponse).toList();
        return PaginatedResponse.<OrderResponse>builder()
                .data(orders)
                .currentPage(orderPage.getNumber())
                .totalPages(orderPage.getTotalPages())
                .totalItems(orderPage.getTotalElements())
                .build();
    }

    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return orderMapper.toResponse(order);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}