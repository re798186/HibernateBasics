package in.reethu.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.reethu.util.HibernateUtil;

public class InsertRecordApp {

	private static final String HQL_INSERT_QUERY = "INSERT INTO in.reethu.model.PremiumInsurancePolicy"
			+ "(policyId,policyName,policyType,company,tenure) "
			+ "SELECT policyId,policyName,policyType,company,tenure FROM in.reethu.model.InsurancePolicy"
			+ " WHERE tenure>:tenure";

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int noOfRows = 0;
		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				transaction = session.beginTransaction();

				Query query = session.createQuery(HQL_INSERT_QUERY);
				query.setParameter("tenure",10);
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
