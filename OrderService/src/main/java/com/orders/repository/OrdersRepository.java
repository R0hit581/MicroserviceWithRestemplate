package com.orders.repository;

import org.springframework.data.repository.CrudRepository;

import com.orders.entity.Orders;

public interface OrdersRepository extends CrudRepository<Orders,Integer>{

}
