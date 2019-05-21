package demo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class CustomerDaoImpl implements CustomerDao {

	public void save() {
		System.out.println("保存。。");
	}
	public void update() {
		System.out.println("更新客户...");
	}

}
