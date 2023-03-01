package in.reethu.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import in.reethu.model.Project;
import in.reethu.util.HibernateUtil;

public class SelectRecordApp1 {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		try {
			Criteria criteria = session.createCriteria(Project.class);

			List<Project> list = criteria.list();
			list.forEach(System.out::println);

		} catch (Exception e) {

		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
