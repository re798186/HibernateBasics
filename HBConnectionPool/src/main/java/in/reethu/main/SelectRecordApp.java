package in.reethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.reethu.model.InsurancePolicy;
import in.reethu.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		InsurancePolicy policy = null;
		long id = 1L;

		try {
			if (session != null)
				policy = session.get(InsurancePolicy.class, id);

			System.out.println(policy);
		} catch (HibernateException e) {
			System.out.println(policy);
			System.out.println("Employee record not found for the given id :: " + id);
		}
	}
}
