package com.food.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.food.model.Customer;
import com.food.model.Food;
import com.food.model.Order;

@Service @Transactional
public class OrderDao {
	@Resource  SessionFactory factory;
	
	/* 添加Order信息 */
	public void addOrder(Order order) throws Exception {
		Session s = factory.getCurrentSession();
		s.save(order);
	}

	/* 删除Order信息 */
	public void deleteOrder(Integer orderid) throws Exception {
		Session s = factory.getCurrentSession();
		Order order = (Order)s.load(Order.class, orderid);
		s.delete(order);
	}

	/* 更新Order信息 */
	public void updateOrder(Order order) throws Exception {
		Session s = factory.getCurrentSession();
		s.update(order);
	}

	/* 根据主键获取对象 */
	public Order getOrderById(Integer orderid) {
		Session s = factory.getCurrentSession();
		Order order = (Order)s.get(Order.class, orderid);
		return order;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<Order> getAllOrders() {
		Session s = factory.getCurrentSession();
		String hql = "From Order";// =select *from Order
		Query q = s.createQuery(hql);
		List orderList = q.list();
		return (ArrayList<Order>) orderList;
	}

	/* 查询order信息 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<Order> getOrders(Customer customer,Food food) {
		Session s = factory.getCurrentSession();
		String hql = "From Order order where 1=1";
		if (null!=customer && customer.getCustomerid()!=0)
			hql = hql + " and order.customer.customerid = '" + customer.getCustomerid() + "'";
		if (null!=food && null!=food.getFoodname())
			hql = hql + " and order.food.foodname like '%" + food.getFoodname() + "'";
		Query q = s.createQuery(hql);
		List orderList = q.list();
		return (ArrayList<Order>) orderList;
	}
}
