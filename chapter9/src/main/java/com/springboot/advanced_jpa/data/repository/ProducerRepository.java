package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
