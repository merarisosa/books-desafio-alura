package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.modelos.DataAuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataAuthorRepository extends JpaRepository<DataAuthorModel, Long> {

}
