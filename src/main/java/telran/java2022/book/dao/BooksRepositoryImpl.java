package telran.java2022.book.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import telran.java2022.book.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class BooksRepositoryImpl implements BookRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Stream<Book> findByAuthorsName(String authorName) {
		TypedQuery<Book> query = em.createQuery("select b from Author a join a.books b where a.name=?1"   , Book.class);
		query.setParameter(1, authorName);
		return query.getResultStream();
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String publisherName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
//	@Transactional
	public Book save(Book book) {
		em.persist(book);
//		em.merge(book);
		return book;
	}

	@Override
	@Transactional
	public void deleteById(String isbn) {
		Book book = em.find(Book.class, isbn);
		em.remove(book);

	}

}
