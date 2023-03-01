package in.reethu.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import in.reethu.model.Project;
import in.reethu.util.HibernateUtil;

public class SelectRecordApp2 {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		try {
			Criteria criteria = session.createCriteria(Project.class);

			Criterion cond1 = Restrictions.ge("teamSize", 10);
			Criterion cond2 = Restrictions.le("teamSize", 20);
			criteria.add(cond1);
			criteria.add(cond2);

			Order order1 = Order.asc("projName");
			Order order2 = Order.asc("location");
			criteria.addOrder(order1);
			criteria.addOrder(order2);

			List<Project> list = criteria.list();
			list.forEach(System.out::println);

		} catch (Exception e) {

		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
