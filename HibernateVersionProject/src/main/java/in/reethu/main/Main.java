package in.reethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.reethu.model.MobileCustomer;
import in.reethu.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				MobileCustomer cust = new MobileCustomer();
				cust.setCname("Reethu");
				cust.setMobileNo(999999999);
				cust.setCno(1);
				cust.setCallerTune("Hello");
				session.save(cust);
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (flag == true)
				transaction.commit();
			else
				transaction.rollback();

			HibernateUtil.closeSession(session);
		}

	}
}
