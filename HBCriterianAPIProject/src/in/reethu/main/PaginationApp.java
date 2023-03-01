package in.reethu.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import in.reethu.model.Project;
import in.reethu.util.HibernateUtil;

public class PaginationApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		try {
			Criteria criteria = session.createCriteria(Project.class);
			criteria.setFirstResult(1);
			criteria.setMaxResults(2);

			List<Project> list = criteria.list();
			list.forEach(System.out::println);

		} catch (Exception e) {

		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
