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
	/*ҵ������*/
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
	/*���Order*/
	public String addOrder() throws Exception{
		customer = customerDao.queryCustomerInfo(customer.getName()).get(0);
		Order ord =new Order();
		ord.setCustomer(customer);
		ord.setFood(food);
		//��Ϊ�ǡ���һ�ݡ���������Ϊ1
		ord.setFoodnum(1);
		ord.setTotal((double)(foodDao.getFoodById(food.getFoodid()).getUintprice()*1));
		orderDao.addOrder(ord);
		return "order_message";
	}
	
	/*��ʾ����Order*/
    public String showOrder() {
        //��ϵͳ�趨Ϊ�û������ظ��������ϵͳ�в�ѯ����һ���������û�����
    	System.out.println(customer.getName());
        Customer cus= customerDao.queryCustomerInfo(customer.getName()).get(0);
        //ע�ⲻ��Ҫfood�Ĳ�ѯ����ʱ����������д����ֱ�ӽ�food��������Ϊnull
        orderList = orderDao.getOrders(cus,null);
        return "show_view";
    }

    /*��ʾĳһOrder����ϸ��Ϣ*/
    public String showDetail() {
    	System.out.print(order.getOrderid());
    	order = orderDao.getOrderById(order.getOrderid());
        return "detail_view";
    }
    
    /*��ʾOrder���޸���*/
    public String showEdit() throws Exception {
    	order = orderDao.getOrderById(order.getOrderid());
        return "edit_view";
    }

//    /*�༭Order*/
//    public String editOrder() throws Exception {
//    	orderDao.updateOrder(order);
//        return "edit_message";
//    }
//    
//    /*ɾ��Order*/
//    public String deleteOrder() throws Exception {
//    	orderDao.deleteOrder(food.getFoodid());
//        return "delete_message";
//    }
    
    /*��ѯOrder*/
    public String queryOrders() throws Exception {
    	orderList = orderDao.getOrders(customer,food);
    	return "show_view";
    }
}
