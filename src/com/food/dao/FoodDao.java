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
import com.food.model.Food;

@Service @Transactional
public class FoodDao {
	@Resource  SessionFactory factory;
	/* ���Food��Ϣ */
	public void addFood(Food food) throws Exception {
		Session s = factory.getCurrentSession();
		s.save(food);
	}

	/* ɾ��Food��Ϣ */
	public void deleteFood(Integer foodid) throws Exception {
		Session s = factory.getCurrentSession();
		Food food = (Food)s.load(Food.class, foodid);
		s.delete(food);
	}

	/* ����Food��Ϣ */
	public void updateFood(Food food) throws Exception {
		Session s = factory.getCurrentSession();
		s.update(food);
	}

	/* ����������ȡ���� */
	public Food getFoodById(Integer foodid) {
		Session s = factory.getCurrentSession();
		Food food = (Food)s.get(Food.class, foodid);
		return food;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<Food> getAllFoods() {
		Session s = factory.getCurrentSession();
		String hql = "From Food";// =select *from Food
		Query q = s.createQuery(hql);
		List foodList = q.list();
		return (ArrayList<Food>) foodList;
	}

	/* ��ѯfood��Ϣ */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<Food> getFoods(String foodname) {
		Session s = factory.getCurrentSession();
		String hql = "From Food food where 1=1";
		if (!foodname.equals(""))
			hql = hql + " and food.foodname like '%" + foodname + "%'";
		Query q = s.createQuery(hql);
		List foodList = q.list();
		return (ArrayList<Food>) foodList;
	}
}
