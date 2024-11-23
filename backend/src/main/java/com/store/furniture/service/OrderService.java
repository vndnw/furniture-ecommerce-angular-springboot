package com.store.furniture.service;

import com.store.furniture.dto.request.EmailDetailsRequest;
import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.request.OrderUpdateRequest;
import com.store.furniture.dto.request.OrderUpdateStatusRequest;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.dto.response.PaginatedResponse;
import com.store.furniture.entity.Order;
import com.store.furniture.entity.OrderItem;
import com.store.furniture.entity.Product;
import com.store.furniture.entity.User;
import com.store.furniture.enums.OrderStatus;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.OrderMapper;
import com.store.furniture.repository.OrderRepository;
import com.store.furniture.repository.ProductRepository;
import com.store.furniture.repository.UserRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    ProductRepository productRepository;
    EmailService emailService;

    public OrderResponse createOrder(OrderCreationRequest orderRequest) {
        String authenticatedUsername =
                SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository
                .findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

        Order order = orderMapper.toOrder(orderRequest);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
                .map(itemRequest -> {
                    Product product = productRepository
                            .findById(itemRequest.getProductId())
                            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemRequest.getQuantity());
                    orderItem.setPrice(product.getPrice() * itemRequest.getQuantity());
                    return orderItem;
                })
                .toList();

        order.setOrderItems(orderItems);
        order.setTotalAmount(
                orderItems.stream().mapToDouble(OrderItem::getPrice).sum());

        Order savedOrder = orderRepository.save(order);

        // Send thank you email notification to the customer
        String message = String.format(
                "Cảm ơn %s đã đặt hàng tại cửa hàng của chúng tôi.\nMã đơn hàng: #%s\nTên khách hàng: %s\nSố điện thoại: %s\nĐịa chỉ giao hàng: %s\nTổng số tiền: %.2fđ",
                savedOrder.getFullName(),
                savedOrder.getId(),
                savedOrder.getFullName(),
                savedOrder.getPhoneNumber(),
                savedOrder.getShippingAddress(),
                savedOrder.getTotalAmount());

        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .recipient(savedOrder.getUser().getEmail())
                .message(message)
                .subject("Đơn đặt hàng thành công #" + savedOrder.getId())
                .build();

        emailService.sendSimpleMail(emailDetailsRequest);

        return orderMapper.toResponse(savedOrder);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse updateOrderStatus(String orderId, OrderUpdateStatusRequest orderUpdateStatusRequest) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        order.setStatus(orderUpdateStatusRequest.getStatus());

        // Send email notification to the customer
        String message = String.format(
                "Trạng thái đơn hàng của bạn đã được cập nhật thành: %s\nMã đơn hàng: #%s\nTên khách hàng: %s\nSố điện thoại: %s\nĐịa chỉ giao hàng: %s\nTổng số tiền: %.2fđ",
                order.getStatus(),
                order.getId(),
                order.getFullName(),
                order.getPhoneNumber(),
                order.getShippingAddress(),
                order.getTotalAmount());

        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .recipient(order.getUser().getEmail())
                .message(message)
                .subject("Cập nhật trạng thái đơn hàng #" + order.getId())
                .build();

        emailService.sendSimpleMail(emailDetailsRequest);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse updateOrder(String orderId, OrderUpdateRequest orderUpdateRequest) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        orderMapper.updateOrder(order, orderUpdateRequest);
        return orderMapper.toResponse(orderRepository.save(order));
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

    public PaginatedResponse<OrderResponse> getOrdersByUser(int page, int size) {
        String authenticatedUsername =
                SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository
                .findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        Pageable pageable = PageRequest.of(page, size);
        var orderPage = orderRepository.findByUser(user, pageable);
        var orders = orderPage.stream().map(orderMapper::toResponse).toList();
        return PaginatedResponse.<OrderResponse>builder()
                .data(orders)
                .currentPage(orderPage.getNumber())
                .totalPages(orderPage.getTotalPages())
                .totalItems(orderPage.getTotalElements())
                .build();
    }

    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return orderMapper.toResponse(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
