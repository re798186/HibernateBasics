package in.reethu.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import in.reethu.model.InsurancePolicy;
import in.reethu.util.HibernateUtil;

public class NativeQueryApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		boolean flag = false;
		int deleteCount = 0,insertCount = 0;

		try {
			NativeQuery<Object[]> query = session.getNamedNativeQuery("GET_ALL_POLICY_TYPE");
			query.setParameter("type", "yearly");

			List<Object[]> list = query.getResultList();
			System.out.println("POLICYID\tPOLICYNAME\tTYPE");
			list.forEach(row -> {
				for (Object obj : row) {
					System.out.print(obj + "\t\t");
				}
				System.out.println();
			});
			System.out.println();

			NativeQuery<InsurancePolicy> query2 = session.getNamedNativeQuery("GET_ALL_POLICIES");
			List<InsurancePolicy> list2 = query2.getResultList();
			list2.forEach(System.out::println);

			System.out.println();

			if (!session.getTransaction().isActive()) {
				transaction = session.beginTransaction();
				NativeQuery<InsurancePolicy> query3 = session.getNamedNativeQuery("DELETE_POLICY_BY_ID");
				query3.setParameter("id", 2);
				deleteCount = query3.executeUpdate();
				flag = true;
			}
			
			System.out.println();
			if (session.getTransaction().isActive()) {
				NativeQuery<InsurancePolicy> query4 = session.createSQLQuery("insert into insurancepolicy(company,policyname,policytype,tenure) values(?,?,?,?)");
				query4.setParameter(1, "TATA");
				query4.setParameter(2, "Life");
				query4.setParameter(3, "yearly");
				query4.setParameter(4,25);
				insertCount = query4.executeUpdate();
				flag = true;
			}
			

		} catch (HibernateException he) {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("No of records deleted is :: " + deleteCount);
				System.out.println("No of records inserted is :: " + insertCount);
			} else {
				transaction.rollback();
				System.out.println("Record not found to delete....");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
