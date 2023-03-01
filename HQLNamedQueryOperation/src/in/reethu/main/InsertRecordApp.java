package in.reethu.main;

import in.reethu.dao.InsurancePolicyDao;
import in.reethu.dao.InsurancePolicyDaoImpl;
import in.reethu.util.HibernateUtil;

public class InsertRecordApp {

	

	public static void main(String[] args) {
		InsurancePolicyDao dao = null;
		dao = new InsurancePolicyDaoImpl();
		String result = dao.transferPremiumPolicies(1);
		System.out.println(result);
		HibernateUtil.closeSessionFactory();	}
}
