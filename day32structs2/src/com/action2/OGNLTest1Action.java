package com.action2;

import java.util.LinkedList;
import java.util.List;

import com.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class OGNLTest1Action extends ActionSupport{
	/*private User user= new User("熊二","123");
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/


	public String test() throws Exception {
//		User user= new User("熊大", "123456");
		
		ValueStack vs = ActionContext.getContext().getValueStack();
//		vs.push("美美");
//		vs.set("user",user);
//		vs.push(user);
		
		/*放list*/
		User user1 = new User("熊大", "123");
		User user2 = new User("熊二", "456");
		User user3 = new User("熊三", "789");
		List<User> ulist = new LinkedList<User>();
		ulist.add(user1);
		ulist.add(user2);
		ulist.add(user3);
//		vs.push(ulist); //栈顶是list, [0].top 是一个list, [0].top 省略不写后，后面的是username password, 取值的时候不写[0].top,取得是后面的值
		vs.set("ulist", ulist); //栈顶是map,[0].top 是一个map,  [0].top 省略不写后,  ulist(key)，ulist.username
		//上面这些都是从root栈获值
		return SUCCESS;
	}
}	
