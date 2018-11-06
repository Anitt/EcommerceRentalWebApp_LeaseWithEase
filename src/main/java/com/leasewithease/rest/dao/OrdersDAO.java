package com.leasewithease.rest.dao;

import com.leasewithease.rest.model.Orders;

public class OrdersDAO implements IDataAccessObject {
	private Orders orders;

	public OrdersDAO(Orders orders) {
		this.orders = orders;
	}
}