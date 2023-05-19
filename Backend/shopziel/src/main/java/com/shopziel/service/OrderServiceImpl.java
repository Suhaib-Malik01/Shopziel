package com.shopziel.service;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public List<OrderDto> getAllOrdersOfCustomer(Integer customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrdersOfCustomer'");
    }

    @Override
    public OrderDto getOrderById(Integer orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public OrderDto updateOrder(Integer orderId, OrderDto updatedOrderDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

    @Override
    public OrderDto cancelOrder(Integer orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

}
