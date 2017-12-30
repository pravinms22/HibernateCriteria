package HibernateCriteria;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class BookTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()	
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		Book b1=new Book("java",100);
		Book b2=new Book("c",50);
		Book b3=new Book("Hibernate",200);
		Book b4=new Book("Spring",300);
		
		Transaction t = session.beginTransaction();
		
		session.save(b1);
		session.save(b2);
		session.save(b3);
		session.save(b4);
		
		
		//printing books with price greater than 150
		
		Criteria cr = session.createCriteria(Book.class);
		cr.add(Restrictions.gt("price",150));
		List result = cr.list();
		
		
		
		Iterator<Book> itr=result.iterator();
		
		while(itr.hasNext())
		{
			Book b=(Book) itr.next();
			System.out.println(b);
		}
		
	
		t.commit();
		session.close();
		
		
		
		
		
	}
}
