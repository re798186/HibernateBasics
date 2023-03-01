package in.reethu.main;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import in.reethu.model.Project;
import in.reethu.util.HibernateUtil;

public class JPACriteria2 {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();

		try {
			
			//Create criteriaBuilder object
			CriteriaBuilder builder = session.getCriteriaBuilder();

			//Creating a criteriaQuery object
			CriteriaQuery<Object[]> cQuery = builder.createQuery(Object[].class);

			//Creating a root object to specify the Entity class name
			Root<Project> root = cQuery.from(Project.class);
			
			//adding the root object to Criteria Query object
			cQuery.multiselect(root.get("pid"),root.get("projName"));

			//Preparing a query having criteria query object
			Query<Object[]> query = session.createQuery(cQuery);
			
			//Executing the JPA QBC logic
			List<Object[]> list = query.getResultList();
			
			//processing the result
			list.forEach(row->{
				for (Object obj : row) {
					System.out.print(obj +"\t");
				}
				System.out.println();
			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
