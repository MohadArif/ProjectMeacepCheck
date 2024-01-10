package com.hibernate.Project;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Id;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmNativeQueryJoinReturnType;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("project start");
		String name, address;
		int salary;
		Scanner input = new Scanner(System.in);
		int no = 1;
		SessionFactory sessionFactory = new Configuration().configure("hibernate.config.xml").buildSessionFactory();
		Employee emp = new Employee();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

//             System.out.println("transcation is working..");

		do {
			System.out.println("**********************Hibernate CURD Operation********************");
			System.out.println("1: INSERT DATA");
			System.out.println("2: DISPLAY DATA");
			System.out.println("3: UPDATE DATA");
			System.out.println("4: SEARCH DATA");
			System.out.println("5: DELETE DATA");
			System.out.println("####################################################");
			System.out.println("enter your choice");
			int ch = input.nextInt();
			switch (ch) {
			case 1:
				System.out.println("***********Insert Data***********");
				System.out.println("enter employee name");
				name = input.next();
				System.out.println("enter employee salary");
				salary = input.nextInt();
				System.out.println("enter employee address");
				address = input.next();
				emp.setName(name);
				emp.setSalary(salary);
				emp.setAddress(address);
//				sessionFactory.openSession();
//				session.beginTransaction();
				session.save(emp);
//				session.persist(emp);  //persist method is also used to save data.
				transaction.commit();
				System.out.println("data inserted successfully...");
//				sessionFactory.close();
				break;
			case 2:
//				 sessionFactory.openSession();
//				session.beginTransaction();
				org.hibernate.query.Query query = session.createQuery("from Employee");
				List<Employee> list = query.getResultList();
				System.out.println("***********Display Data***********");
				for (Employee employee : list) {
					System.out.println(employee.toString());
				}
//				transaction.commit();
//				sessionFactory.close();
				break;
			case 3:
				System.out.println("***********Update Data***********");
				System.out.println("enter empolyee id");
				int id = input.nextInt();
				Employee emp1 = (Employee) session.get(Employee.class, id);
				if (emp1 != null) {
					System.out.println("enter employee name");
					name = input.next();
					System.out.println("enter employee salary");
					salary = input.nextInt();
					System.out.println("enter employee address");
					address = input.next();
					emp1.setName(name);
					emp1.setSalary(salary);
					emp1.setAddress(address);
//				sessionFactory.openSession();
//				session.beginTransaction();
					session.saveOrUpdate(emp1);
					transaction.commit();
					System.out.println("data updated successfully...");
				}
				break;
			case 4:
				System.out.println("enter empolyee id");
				id = input.nextInt();
				Employee employee = (Employee) session.load(Employee.class, id);
				transaction.commit();
				System.out.println("***********Search Data***********");
				System.out.println(employee);
				break;
			case 5:
				System.out.println("***********Delete Data***********");
				System.out.println("enter empolyee id");
				id = input.nextInt();
				Employee employee1 = (Employee) session.get(Employee.class, id);
				if (employee1 != null) {
					session.delete(employee1);
					transaction.commit();
					System.out.println("data deleted successfully...");
				}
				break;
			default:
				break;
			}
		} while (no > 0);

	}
}
