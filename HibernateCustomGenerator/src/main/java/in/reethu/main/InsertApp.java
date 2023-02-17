package in.reethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.reethu.model.Employee;
import in.reethu.util.HibernateUtil;

public class InsertApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				Employee employee = new Employee();
				employee.setEmpName("reethu");
				employee.setEmpSal(4534.5);
				session.save(employee);
				Employee employee2 = new Employee();
				employee2.setEmpName("reethu2");
				employee2.setEmpSal(4534.5);
				session.save(employee2);
				Employee employee3 = new Employee();
				employee3.setEmpName("reethu3");
				employee3.setEmpSal(4534.5);
				session.save(employee3);

				System.out.println(employee);
				System.out.println(employee2);
				System.out.println(employee3);
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(flag == true)
				transaction.commit();
			else
				transaction.rollback();
			
			HibernateUtil.closeSession(session);
		}

	}
}
