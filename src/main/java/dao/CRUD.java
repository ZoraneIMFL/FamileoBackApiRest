package dao;


public interface CRUD<E> {
    E create(E e);

    E update(E e);

    void delete(Class<E> clazz, long id);
    void delete(E entity);

    E read(Class<E> clazz, long id);
}
