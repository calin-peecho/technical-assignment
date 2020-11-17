package com.example.demo.repository;

import com.example.demo.entity.Bier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BierRepository extends CrudRepository<Bier, Long> {

    long countByUser_Id(long id);

    Optional<Bier> findByIdAndUser_Id(long id, long userId);

    Optional<Bier[]> findAllByUser_Id(long userId);

    void deleteByIdAndUser_Id(long id, long userId);
}
