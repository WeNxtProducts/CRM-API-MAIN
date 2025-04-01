/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.conversations;

import com.vi.model.dao.ConversationDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepositoryCustom extends JpaRepository<ConversationDAO,Long> {

}
