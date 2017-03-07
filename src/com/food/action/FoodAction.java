package com.food.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.food.dao.FoodDao;
import com.food.model.Food;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype") // Ĭ�ϵ�������������������Ķ���,(value="foodAction")
public class FoodAction extends ActionSupport {
	/*ҵ������*/
	@Resource FoodDao foodDao;
	private List<Food> foodList;
	
	private Integer foodid;
	String keyWords;
	
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Integer getFoodid() {
		return foodid;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	public void setFoodid(Integer foodid) {
		this.foodid = foodid;
	}

	/* ���Food��Ϣ */
	private Food food;

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public String addFood() throws Exception {
		foodDao.addFood(food);
		return "message";
	}

	public String showFood() {
		foodList = foodDao.getAllFoods();
		return "show_view";
	}
	//show�������������ǰ�ǰ���food��Ϣ��������һ��ҳ��
	public String showDetail(){
		food=foodDao.getFoodById(food.getFoodid());
		return "detail_view";
	}
	public String showEdit() throws Exception{
		food=foodDao.getFoodById(food.getFoodid());
		return "edit_view";
	}
	
	public String editFood() throws Exception{
		foodDao.updateFood(food);
		return "edit_message";
	}
	public String deleteFood() throws Exception{
		foodDao.deleteFood(food.getFoodid());
		return "delete_message";
	}
	public String queryFoods() throws Exception{
		foodList=foodDao.getFoods(keyWords);
		return "show_view";
	}
}
