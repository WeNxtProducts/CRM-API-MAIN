/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.fileattributes;

import com.vi.model.dao.FileAttributesDAO;
import com.vi.model.dto.FileAttributesDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

public class FileAttributesJPAAdapterCustom implements FileAttributesPersistentCustom {
	@Autowired
	FileAttributesRepositoryCustom fileAttributesRepositoryCustom;

	@Override
	public List<FileAttributesDTOCustom> fetchAll() {
		var fileAttributesDAOList = fileAttributesRepositoryCustom.findAll();
		return FileAttributesMapperCustom.INSTANCE.fileAttributesDAOListToFileAttributesDTOListCustom(fileAttributesDAOList);
	}

	@Override
	public FileAttributesDTOCustom get(Long id) {
		var fileAttributesDAO = fileAttributesRepositoryCustom.findById(id);
		if (fileAttributesDAO.isPresent()) {
			return FileAttributesMapperCustom.INSTANCE.fileAttributesDAOToFileAttributesDTOCustom(fileAttributesDAO.get());
		}
		return null;
	}

	@Override
	public FileAttributesDTOCustom create(FileAttributesDTOCustom fileAttributesDTOCustom) {
		var fileAttributesDAO = FileAttributesMapperCustom.INSTANCE.fileAttributesDTOCustomToFileAttributesDAO(fileAttributesDTOCustom);
		FileAttributesDAO newFileAttributes = fileAttributesRepositoryCustom.save(fileAttributesDAO);
		return FileAttributesMapperCustom.INSTANCE.fileAttributesDAOToFileAttributesDTOCustom(newFileAttributes);
	}

	//updated
	@Override
	public FileAttributesDTOCustom update(FileAttributesDTOCustom fileAttributesDTOCustom) {
		var fileAttributesDAO = fileAttributesRepositoryCustom.findById(fileAttributesDTOCustom.getSysId())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + fileAttributesDTOCustom.getSysId()));
		var oldData = FileAttributesMapperCustom.INSTANCE.fileAttributesDAOToFileAttributesDTOCustom(fileAttributesDAO);
		FileAttributesMapperCustom.INSTANCE.assignValuesCustom(fileAttributesDTOCustom, fileAttributesDAO);
		FileAttributesDAO newFileAttributes = fileAttributesRepositoryCustom.save(fileAttributesDAO);
		return FileAttributesMapperCustom.INSTANCE.fileAttributesDAOToFileAttributesDTOCustom(newFileAttributes);
	}
	

	@Override
	public Boolean delete(Long id) {
		var fileAttributesDAO = fileAttributesRepositoryCustom.getById(id);
	//	fileAttributesDAO.setDeleted(true);
		fileAttributesRepositoryCustom.save(fileAttributesDAO);
		return true;
	}

	
	
	
}
