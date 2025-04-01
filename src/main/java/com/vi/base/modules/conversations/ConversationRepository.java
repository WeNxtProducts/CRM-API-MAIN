/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.conversations;

import com.vi.model.dao.ConversationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationDAO,Long>, JpaSpecificationExecutor<ConversationDAO> {

}
