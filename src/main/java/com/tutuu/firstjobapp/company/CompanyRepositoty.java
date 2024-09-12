package com.tutuu.firstjobapp.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepositoty extends JpaRepository<Company,Long> {
}
