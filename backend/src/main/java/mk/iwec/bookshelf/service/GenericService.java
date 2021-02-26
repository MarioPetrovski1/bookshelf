package mk.iwec.bookshelf.service;

import java.util.List;

public interface GenericService<T, ID> {
	public T findById(ID id);

	public List<T> findAll();

	public T create(T entity);

	public T update(ID id, T entity);

	public void deleteById(ID id);
}
