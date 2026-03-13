package com.klef.fsad.exam.Insem;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DepartmentDemo {
	public static void main(String args[]) {
		DepartmentDemo demo = new DepartmentDemo();
		demo.addDepartment();
		// demo.deleteDepartment();
	}

	public void addDepartment() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();

		Transaction t = session.beginTransaction();

		Department d = new Department();
		d.setName("CSE");
		d.setDescription("Computer Science Department");
		d.setDate("13-03-2026");
		d.setStatus(true);

		session.persist(d);
		t.commit();

		System.out.println("Department Added Successfully");

		session.close();
		sf.close();
	}

	public void deleteDepartment() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Department ID to Delete:");
		int id = sc.nextInt();

		Department d = session.find(Department.class, id);

		Transaction t = session.beginTransaction();

		if (d != null) {
			session.remove(d);
			t.commit();
			System.out.println("Department Record Deleted Successfully");
		} else {
			System.out.println("Department ID not Found To Delete");
		}

		session.close();
		sc.close();
		sf.close();
	}
}