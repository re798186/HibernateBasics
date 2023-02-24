package in.reethu.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.reethu.model.Employee;
import in.reethu.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) {

		try {
			Session session = HibernateUtil.getSession();

			if (session != null) {
				Query<Employee> query = session.createQuery("FROM in.reethu.model.Employee where empSal>:sal");
				query.setParameter("sal", 1000.00);
				List<Employee> resultList = query.getResultList();
				for (Employee employee2 : resultList) {
					System.out.println(employee2);
				}

				Query<Employee> query1 = session.createQuery("FROM in.reethu.model.Employee");
				List<Employee> employees = query1.list();
				employees.forEach(e -> System.out.println(e));

				Query<Employee> query2 = session.createQuery("FROM in.reethu.model.Employee where empId>:id");
				query2.setParameter("id", 1);
				Iterator<Employee> iterator = query2.iterate();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
			}

		} catch (HibernateException e) {
			System.out.println("Employee record not found for the given id ");
		}
	}
}
