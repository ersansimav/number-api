package com.ersan.assignment.number.repository;

import com.ersan.assignment.number.model.Number;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends MongoRepository<Number, String> {

    Number findByNumber(Integer numberVal);

}