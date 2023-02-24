package in.reethu.main;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.reethu.model.Employee;
import in.reethu.util.HibernateUtil;

public class SelectRecordApp2 {

	public static void main(String[] args) {

		try {
			Session session = HibernateUtil.getSession();

			if (session != null) {
				System.out.println("**********SINGLE ROW***********");
				Query query = session.createQuery("SELECT empName FROM in.reethu.model.Employee where empSal>:sal");
				query.setParameter("sal", 1000.00);
				List<String> resultList = query.getResultList();
				resultList.forEach(ename -> System.out.println(ename));
				
				System.out.println("**********MULTIPLE ROW***********");
				Query query3 = session.createQuery("SELECT empName, empSal FROM in.reethu.model.Employee where empSal>:sal");
				query3.setParameter("sal", 1000.00);
				List<Object[]> resultList3 = query3.getResultList();
				resultList3.forEach(arr -> {for(Object obj :arr) {System.out.println(obj);}});

				System.out.println("**********UNIQUE RESULT***********");
				Query<Employee> query1 = session.createQuery("FROM in.reethu.model.Employee where empId = :id");
				query1.setParameter("id",1);
				Employee employee = query1.uniqueResult();
				System.out.println(employee);

				System.out.println("**********OPTIONAL RESULT***********");
				Query<Employee> query2 = session.createQuery("FROM in.reethu.model.Employee where empId=:id");
				query2.setParameter("id",1);
				Optional<Employee> optional = query2.uniqueResultOptional();
				if(optional.isPresent()) {
					Employee emp = optional.get();
					System.out.println(emp);
				}
				
			}

		} catch (HibernateException e) {
			System.out.println("Employee record not found for the given id ");
		}
	}
}
