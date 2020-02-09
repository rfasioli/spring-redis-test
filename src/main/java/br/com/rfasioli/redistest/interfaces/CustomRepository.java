package br.com.rfasioli.redistest.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CustomRepository<T, ID> extends CrudRepository<T, ID> {
    <S extends T> List<S> saveAll(Iterable<S> entities);
    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);
}
