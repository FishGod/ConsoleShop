package com.wjc.shop;

import java.math.BigDecimal;

public class Admin extends User{
	public void adminLogin() {
		System.out.println("欢迎管理员登录！");
		while (true) {
			System.out.println("请输入管理员用户名：");
			String admin_username = Shop.sc.next();
			System.out.println("请输入密码：");
			String admin_password = Shop.sc.next();
			if (admin_username.equals("admin") && admin_password.equals("admin")) {
				System.out.println("******管理员登陆成功******");
				while(true){
					int choice = this.showAdminMainMenu();
					if(choice == 5){
						break;
					}
					this.chooseAdminMenu(choice);
				}
				
				break;
			} else {
				System.out.println("登陆失败,请重新登录！");
			}

		}
	}
	
	public int showAdminMainMenu() {
		System.out.println("******管理员菜单******");
		System.out.println("\t1.添加商品");
		System.out.println("\t2.修改商品");
		System.out.println("\t3.删除商品");
		System.out.println("\t4.查看商品列表品");
		System.out.println("\t5.退出");
		System.out.println("*********************************");
		System.out.println("请选择菜单：");
		int choice = Shop.sc.nextInt();
		return choice; 
	}

	public String isGo_on(){
		System.out.println("您是否继续？Y/N");
		String go_on = Shop.sc.next();
		return go_on.toUpperCase();
	}
	public boolean chooseAdminMenu(int choice) {
        String go_on = "Y";
		boolean result = true;
		switch (choice) {
		case 1:
			System.out.println("您选择的菜单是：1.添加商品");
			while(go_on.equals("Y")){
				this.addGood();
				go_on = this.isGo_on();
			}
			
			break;
		case 2:
			System.out.println("您选择的菜单是2.修改商品");
			this.updateGood();
			break;
		case 3:
			System.out.println("您选择的菜单是：3.删除商品");
			this.deleteGood();
			break;
		case 4:
			System.out.println("您选择的菜单是：4.查看商品列表品");
			super.showGoodList();
			break;
		case 5:
			System.out.println("管理员已退出！");
			result = false;
			break;
		default:
			System.out.println("您输入的信息有错！");
			break;
		}
		return result;
	
	}

	public void addGood() {
		System.out.println("******开始添加商品******");
		System.out.println("请输入商品编号：");
		int id = Shop.sc.nextInt();
		System.out.println("请输入商品名称：");
		String name = Shop.sc.next();
		System.out.println("请输入商品价格：");
		String price = Shop.sc.next();
		System.out.println("请输入商品数量：");
		int num = Shop.sc.nextInt();
		
		Good good = new Good();
		good.setId(id);
		good.setName(name);
		good.setPrice(new BigDecimal(price));
		good.setNum(num);
		Shop.goodList.add(good);
		Shop.saveGoodToFile();
		System.out.println("******商品添加成功******");
	}
	
	public void updateGood() {
		System.out.println("******开始修改商品******");
		System.out.println("请输入需要修改的商品编号：");
		int id = Shop.sc.nextInt();
		Good good = this.findGoodById(id);
		if(good == null){
			System.out.println("未找到该商品");
		}else{
			System.out.println("该商品信息如下：");
			System.out.println("商品id\t商品名称\t商品价格\t商品数量");
			System.out.println(good.getId()+"\t"+good.getName()+"\t"+
			good.getPrice()+"\t"+good.getNum());
		}
		System.out.println("请输入修改后的商品名称：");
		String name = Shop.sc.next(); 
		good.setName(name);
		System.out.println("请输入修改后的商品价格：");
		Double price = Shop.sc.nextDouble();
		good.setPrice(new BigDecimal(price));
		System.out.println("请输入修改后的商品数量：");
		int num = Shop.sc.nextInt();
		good.setNum(num);
		Shop.saveGoodToFile();
	}
	
	public void deleteGood() {
		System.out.println("******开始删除商品******");
		System.out.println("请输入需要删除的商品编号：");
		int id = Shop.sc.nextInt();
		Good good = this.findGoodById(id);
		Shop.goodList.remove(good);
		Shop.saveGoodToFile();
		System.out.println("******删除商品成功******");
	}

	
}
