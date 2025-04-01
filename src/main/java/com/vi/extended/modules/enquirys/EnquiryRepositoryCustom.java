/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.enquirys;

import com.vi.model.dao.EnquiryDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepositoryCustom extends JpaRepository<EnquiryDAO,Long> {

}
