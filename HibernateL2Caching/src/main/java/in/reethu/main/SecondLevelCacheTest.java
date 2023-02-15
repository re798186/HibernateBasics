package in.reethu.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.reethu.model.InsurancePolicy;
import in.reethu.util.HibernateUtil;

public class SecondLevelCacheTest {

	public static void main(String[] args) {

		Session session = null;
		InsurancePolicy policy = null;

		session = HibernateUtil.getSession();

		try {
			policy = session.get(InsurancePolicy.class, 1L);// fetch the record from DB, keep it in L1 and L2
			System.out.println("1 :"+ policy);
			
			policy = session.get(InsurancePolicy.class, 1L);// fetch the record from L1
			System.out.println("2 :"+policy);
			
			session.evict(policy);//remove policy from L1
			policy = session.get(InsurancePolicy.class, 1L);// fetch the record from L2, keep it in L1
			System.out.println("3 :"+policy);
			session.clear();//remove record from L1
			
			Thread.sleep(20000);//remove record from L2
					
			policy = session.get(InsurancePolicy.class, 1L);// fetch the record from DB, keep it in L1 and L2
			System.out.println("4 :"+policy);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
}
