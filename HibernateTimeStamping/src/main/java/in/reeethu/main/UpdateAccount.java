package in.reeethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.reethu.model.BankAccount;
import in.reethu.util.HibernateUtil;

public class UpdateAccount {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				BankAccount account =session.load(BankAccount.class, 1L);
				account.setAccNo(1L);
				account.setBalance(3000.00);
				account.setHolderName("Reethu");
				account.setType("FD");
				session.saveOrUpdate(account);
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (flag == true) {
				transaction.commit();
				System.out.println("Record updated succesfully...");
			}

			else {
				transaction.rollback();
				System.out.println("Record updation failed...");
			}

			HibernateUtil.closeSession(session);
		}

	}
}
