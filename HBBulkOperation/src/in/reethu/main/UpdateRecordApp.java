package in.reethu.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.reethu.model.Employee;
import in.reethu.util.HibernateUtil;

public class UpdateRecordApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int noOfRows = 0;
		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				transaction = session.beginTransaction();

				Query query = session.createQuery("UPDATE in.reethu.model.Employee SET empSal=empSal +:sal where empId > :id");
				query.setParameter("sal", 3000.00);
				query.setParameter("id", 1);
				noOfRows = query.executeUpdate();
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Employee records updated :: " + noOfRows);
			} else {
				transaction.rollback();
				System.out.println("Employee record not found for the given id ");
			}
		}
	}
}
