package com.vi.extended.modules.leads;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.LeadDAO;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeadRepositoryCustom extends JpaRepository<LeadDAO, Long> {
    
    
}