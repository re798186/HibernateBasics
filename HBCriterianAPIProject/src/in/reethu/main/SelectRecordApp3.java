package in.reethu.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.criterion.Restrictions;

import in.reethu.model.Project;
import in.reethu.util.HibernateUtil;

public class SelectRecordApp3 {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		try {
			Criteria criteria = session.createCriteria(Project.class);

			PropertyProjection projection1 = Projections.property("projName");
			PropertyProjection projection2 = Projections.property("location");
			ProjectionList plist = Projections.projectionList();
			plist.add(projection1);
			plist.add(projection2);
			criteria.setProjection(plist);

			Criterion cond1 = Restrictions.ge("teamSize", 10);
			Criterion cond2 = Restrictions.le("teamSize", 20);
			criteria.add(cond1);
			criteria.add(cond2);

			Order order1 = Order.asc("projName");
			Order order2 = Order.asc("location");
			criteria.addOrder(order1);
			criteria.addOrder(order2);

			List<Object[]> list = criteria.list();
			list.forEach(row -> {
				for (Object obj : row)
					System.out.print(obj);
				System.out.println();
			});

		} catch (Exception e) {

		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
