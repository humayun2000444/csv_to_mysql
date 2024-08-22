package com.example.csv_to_mysql.repository;

import com.example.csv_to_mysql.entity.TbMnp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TbMnpRepository extends JpaRepository<TbMnp, String> {
    void deleteByNumber(String number);
}
