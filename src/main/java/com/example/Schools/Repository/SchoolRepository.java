package com.example.Schools.Repository;

import com.example.Schools.Domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository  extends JpaRepository<School,Long> {
}
