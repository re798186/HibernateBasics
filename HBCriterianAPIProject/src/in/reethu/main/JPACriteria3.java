package in.reethu.main;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import in.reethu.model.Project;
import in.reethu.util.HibernateUtil;

public class JPACriteria3 {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();

		try {
			
			//Create criteriaBuilder object
			CriteriaBuilder builder = session.getCriteriaBuilder();

			//Creating a criteriaQuery object
			CriteriaQuery<Project> cQuery = builder.createQuery(Project.class);

			//Creating a root object to specify the Entity class name
			Root<Project> root = cQuery.from(Project.class);
			
			//adding the root object to Criteria Query object
			cQuery.select(root);

			Predicate equal = builder.equal(root.get("projName"),"Base");
			Predicate lessthan = builder.lessThanOrEqualTo(root.get("teamSize"),23);
			Predicate and = builder.and(equal,lessthan);
			cQuery.where(and);
			
			Order order = builder.desc(root.get("projName"));
			cQuery.orderBy(order);
			
			//Preparing a query having criteria query object
			Query<Project> query = session.createQuery(cQuery);
			
			//Executing the JPA QBC logic
			List<Project> list = query.getResultList();
			
			//processing the result
			list.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
