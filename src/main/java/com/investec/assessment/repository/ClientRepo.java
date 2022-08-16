package com.investec.assessment.repository;

import com.investec.assessment.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends CrudRepository<Client, Long> {
    Optional<Client> findByIdNumber(String idNumber);

    @Override
    Optional<Client> findById(Long aLong);
    Optional<Client> findByMobileNumber(String mobileNumber);
    Optional<List<Client>> findByFirstname(String firstname);
}
