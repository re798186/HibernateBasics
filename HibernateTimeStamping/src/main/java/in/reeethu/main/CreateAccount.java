package in.reeethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.reethu.model.BankAccount;
import in.reethu.util.HibernateUtil;

public class CreateAccount {


	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				BankAccount account = new BankAccount();
				account.setBalance(1000.00);
				account.setHolderName("Reethu");
				account.setType("FD");
				session.save(account);
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
