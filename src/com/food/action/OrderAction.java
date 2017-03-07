package com.food.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.CustomerDao;
import com.food.dao.FoodDao;
import com.food.dao.OrderDao;
import com.food.model.Customer;
import com.food.model.Food;
import com.food.model.Order;

@Controller @Scope("prototype")
public class OrderAction {
	/*业务层对象*/
    @Resource OrderDao orderDao;
    @Resource CustomerDao customerDao;
    @Resource FoodDao foodDao;
    
    private Order order;
    private List<Order> orderList;
    private Customer customer;
    private Food food;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	/*添加Order*/
	public String addOrder() throws Exception{
		customer = customerDao.queryCustomerInfo(customer.getName()).get(0);
		Order ord =new Order();
		ord.setCustomer(customer);
		ord.setFood(food);
		//因为是“来一份”，所以置为1
		ord.setFoodnum(1);
		ord.setTotal((double)(foodDao.getFoodById(food.getFoodid()).getUintprice()*1));
		orderDao.addOrder(ord);
		return "order_message";
	}
	
	/*显示所有Order*/
    public String showOrder() {
        //将系统设定为用户名不重复，因此在系统中查询到第一个该名称用户即可
    	System.out.println(customer.getName());
        Customer cus= customerDao.queryCustomerInfo(customer.getName()).get(0);
        //注意不需要food的查询条件时，下面语句的写法，直接将food的条件置为null
        orderList = orderDao.getOrders(cus,null);
        return "show_view";
    }

    /*显示某一Order的详细信息*/
    public String showDetail() {
    	System.out.print(order.getOrderid());
    	order = orderDao.getOrderById(order.getOrderid());
        return "detail_view";
    }
    
    /*显示Order的修改项*/
    public String showEdit() throws Exception {
    	order = orderDao.getOrderById(order.getOrderid());
        return "edit_view";
    }

//    /*编辑Order*/
//    public String editOrder() throws Exception {
//    	orderDao.updateOrder(order);
//        return "edit_message";
//    }
//    
//    /*删除Order*/
//    public String deleteOrder() throws Exception {
//    	orderDao.deleteOrder(food.getFoodid());
//        return "delete_message";
//    }
    
    /*查询Order*/
    public String queryOrders() throws Exception {
    	orderList = orderDao.getOrders(customer,food);
    	return "show_view";
    }
}
