package com.wjc.shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	static Scanner sc = new Scanner(System.in);
	static List<Good> goodList = new ArrayList();
	static List<Good> myGoodList = new ArrayList();
	static List<User> userList = new ArrayList();
	static File userFile = new File("E:\\eclipse\\workspace\\ConsoleShop\\src\\com\\wjc\\shop\\UserFile");
	static File goodFile = new File("E:\\eclipse\\workspace\\ConsoleShop\\src\\com\\wjc\\shop\\GoodFile");
	User user = new User();
	Admin admin = new Admin();

	public static void saveListToFile() {
		try {
			FileOutputStream fos = new FileOutputStream(userFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readListFromFile(){
		try {
			FileInputStream fis = new FileInputStream(userFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			userList = (List) object;
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveGoodToFile() {
		try {
			FileOutputStream fos = new FileOutputStream(goodFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(goodList);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readGoodFromFile(){
		try {
			FileInputStream fis = new FileInputStream(goodFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			goodList = (List) object;
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.run();
	}

	private void run() {
		this.readListFromFile();
		this.readGoodFromFile();
		boolean go_on = true;
		while (go_on) {
			int choice = this.showMainMenu();
			go_on = this.chooseMenu(choice);
		}
	}


	private int showMainMenu() {
		System.out.println("******欢迎进入电子商城******");
		System.out.println("\t1.注册");
		System.out.println("\t2.登录");
		System.out.println("\t3.查看商城");
		System.out.println("\t4.查看我购买的商品");
		System.out.println("\t5.管理员登录");
		System.out.println("\t6.退出系统");
		System.out.println("*********************************");
		System.out.println("请选择菜单：");
		int choice = sc.nextInt();
		return choice;
	}

	private boolean chooseMenu(int choice) {
		boolean result = true;
		switch (choice) {
		case 1:
			System.out.println("您选择的菜单是：1.注册");
			user.reg();
			break;
		case 2:
			System.out.println("您选择的菜单是：2.登录");
			user.login();
			break;
		case 3:
			System.out.println("您选择的菜单是：3.查看商城");
			user.showGoodList();
			if (user.isLogin() == true) {
				user.buy();
			} else {
				System.out.println("您还未登陆，请先登录，再购买商品");
			}
			break;
		case 4:
			System.out.println("您选择的菜单是：4.查看我购买的商品");
			user.showMyGoodList();
			break;
		case 5:
			System.out.println("您选择的菜单是：5.管理员登录");
			admin.adminLogin();
			break;
		case 6:
			System.out.println("谢谢使用！");
			result = false;
			break;
		default:
			System.out.println("您输入的信息有错！");
			break;
		}
		return result;
	}
}
