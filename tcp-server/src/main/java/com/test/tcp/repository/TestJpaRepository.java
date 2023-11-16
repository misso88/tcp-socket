package com.test.tcp.repository;

import com.test.tcp.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestJpaRepository extends JpaRepository<Test, Long> {
}
