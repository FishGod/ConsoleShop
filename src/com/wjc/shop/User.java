package com.wjc.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable{
	private String username;
	private String password;
	private boolean isLogin;
	
	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public void showGoodList() {
		System.out.println("******商品列表如下******");
		for(Good good : Shop.goodList){
			System.out.println(good);
		}
	}
	
	public void showMyGoodList() {
		System.out.println("******您购买商品列表如下******");
		BigDecimal total = new BigDecimal("0");
		for(Good good : Shop.myGoodList){
			System.out.println(good);
			BigDecimal price = good.getPrice();
			int num = good.getNum();
			total = total.add(price).multiply(new BigDecimal(num));
		}
		System.out.println("总价格为："+total);
	}
	
	public void reg() {
		System.out.println("欢迎注册！");

		System.out.println("请输入用户名：");
		String username = Shop.sc.next();
		boolean c = false;
		boolean d = false;

		while (true) {
			// 用户名长度不能小于6，至少包含一个数字一个字母
			if (username.length() >= 6) {
				for (int i = 0; i < username.length(); i++) {
					char a = username.charAt(i);
					if (Character.isDigit(a) == true) {
						c = true;
						break;
					}
				}
				for (int j = 0; j < username.length(); j++) {
					char a = username.charAt(j);
					if (Character.isDigit(a) == true) {
						d = true;
						break;
					}
				}
			}
			if (c == true && d == true) {
				break;
			} else {
				System.out.println("用户名长度不能小于6，至少包含一个数字一个字母！");
				System.out.println("请重新输入用户名：");
				username = Shop.sc.next();
			}

		}

		while (true) {

			System.out.println("请输入密码：");
			String password = Shop.sc.next();
			System.out.println("请再次输入密码：");
			String repassword = Shop.sc.next();
			if (password.equals(repassword)) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				Shop.userList.add(user);
				Shop.saveListToFile();
				break;
			} else {
				System.out.println("两次密码输入不一致，请重新输入！");
			}
		}
		System.out.println("注册成功");
	}
	
	public void login(){
		System.out.println("欢迎登录！");
		boolean loginResult = false;
		while (true) {
			System.out.println("请输入用户名：");
			String login_username = Shop.sc.next();
			System.out.println("请输入密码：");
			String login_password = Shop.sc.next();
			for (User user : Shop.userList) {
				if (login_username.equals(user.getUsername()) && login_password.equals(user.getPassword())) {
					System.out.println("登陆成功");
					this.setLogin(true);
					loginResult = true;
					break;
				}
			}
			if (loginResult == true) {
				break;
			} else {
				System.out.println("登陆失败,请重新登录！");
			}
		}

	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Good findGoodById(int id){
		Good returnGood = null;
		for(Good good : Shop.goodList){
			if(good.getId()==id){
				returnGood = good;
				break;
			}
		}
		return returnGood;
	}

	public void buy() {
		while(true){
			System.out.println("请选择你要购买的商品编号：");
			int id = Shop.sc.nextInt();
			System.out.println("您将要购买的商品信息如下：");
			Good shopGood = this.findGoodById(id);
			System.out.println(shopGood);
			System.out.println("请输入要购买的数量：");
			int num = Shop.sc.nextInt();
			Good myGood = new Good();
			//myGood.setId(shopGood.getId());
			//myGood.setName(shopGood.getName());
			//myGood.setPrice(shopGood.getPrice());
			myGood = shopGood.clone();
			myGood.setNum(num);
			Shop.myGoodList.add(myGood);
			System.out.println("是否继续购买：Y/N");
			String choice = Shop.sc.next();
			choice = choice.toUpperCase();
			if(choice.equals("N")){
				break;
			}
		}
		this.showMyGoodList();
	}

}
