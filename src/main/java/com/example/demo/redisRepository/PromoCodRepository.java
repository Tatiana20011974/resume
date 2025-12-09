package com.example.demo.redisRepository;


import com.example.demo.model.PromoCod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodRepository extends JpaRepository<PromoCod, Long> {
}
