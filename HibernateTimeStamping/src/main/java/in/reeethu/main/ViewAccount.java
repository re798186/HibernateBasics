package in.reeethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.reethu.model.BankAccount;
import in.reethu.util.HibernateUtil;

public class ViewAccount {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		BankAccount account = null;
		Long id =1L;

		try {
			if (session != null)
				account = session.load(BankAccount.class, id);

			System.out.println(account);
		} catch (HibernateException e) {
			System.out.println(account);
			System.out.println("Employee record not found for the given id :: " + id);
		}
	}
}
