/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.users;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.UserDAO;
import com.vi.model.dto.UserDTO;
import com.vi.model.dto.UserDTO;

import jakarta.persistence.EntityNotFoundException;

import com.vi.base.modules.users.UserMapper;
import com.vi.base.modules.users.UserMapper;
//import com.vi.corelib.events.EventPublisher;
import com.vi.corelib.filter.FilterSpecificationsBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class UserJPAAdapter implements UserPersistent {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserDTO> fetchAll() {
		var userDAOList = userRepository.findAll();
		return UserMapper.INSTANCE.userDAOListToUserDTOList(userDAOList);
	}

	@Override
	public UserDTO get(Long id) {
		var userDAO = userRepository.findById(id);
		if(userDAO.isPresent()) {
			return UserMapper.INSTANCE.userDAOToUserDTO(userDAO.get());
		}
		return null;
	}

  @SneakyThrows
	@Override
	public UserDTO create(UserDTO userDTO) {
		var userDAO = UserMapper.INSTANCE.userDTOToUserDAO(userDTO);
		var newUser = userRepository.save(userDAO);
		var newData = UserMapper.INSTANCE.userDAOToUserDTO(newUser);
		
		
		return newData;
	}

	@Override
 		public UserDTO update(UserDTO userDTO) {
			
		 var userDAO = userRepository.findById(userDTO.getUserSeqNo())
        .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + userDTO.getUserSeqNo()));
		var oldData = UserMapper.INSTANCE.userDAOToUserDTO(userDAO);
		UserMapper.INSTANCE.assignValues(userDTO, userDAO);
		var newUser = userRepository.save(userDAO);
		var newData = UserMapper.INSTANCE.userDAOToUserDTO(newUser); 
		
		return newData;   
	}

	@SneakyThrows
	@Override
	public Boolean delete(Long id) {
	    var userDAO = userRepository.findById(id)
	                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

	    userDAO.setDeleted(true); // Set deleted flag to true
	    userRepository.save(userDAO); // Persist the change

	    return true; 
	}


	@Override
	public List<UserDTO> filterData(String search) {
		Specification<UserDAO> result = new FilterSpecificationsBuilder<UserDAO>().with(search).build();
		return UserMapper.INSTANCE.userDAOListToUserDTOList(userRepository.findAll(result));
	}

	@Override
	public List<UserDTO> filterData(JsonNode search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("userSeqNo").descending());
    Specification<UserDAO> result = new FilterSpecificationsBuilder<UserDAO>().with(search).build();
		return UserMapper.INSTANCE.userDAOListToUserDTOList(userRepository.findAll(result, pageable).getContent());
	}
	
	@Override
	public List<UserDTO> filterData(JsonNode search) {
    Specification<UserDAO> result = new FilterSpecificationsBuilder<UserDAO>().with(search).build();
		return UserMapper.INSTANCE.userDAOListToUserDTOList(userRepository.findAll(result));
	}
}
