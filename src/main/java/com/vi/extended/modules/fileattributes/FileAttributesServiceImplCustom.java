/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.fileattributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vi.model.dao.FileAttributesDAO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@AllArgsConstructor
@NoArgsConstructor
public class FileAttributesServiceImplCustom implements FileAttributesServiceCustom {
	
	  @Autowired
	    private FileAttributesRepositoryCustom filerepo;
	
	@Value("${spring.dms.dms_status}")
	private String dmsStatusProperty;
 
	@Value("${spring.dms.tran_Id}")
	private String tranIdProperty;

	private String generateNewFilePath(String docModule, String fileName) {
	    String baseDirectory = "D:\\Upload" + File.separator + docModule;
	    File moduleDirectory = new File(baseDirectory);

	    if (!moduleDirectory.exists()) {
	        moduleDirectory.mkdirs(); // Ensure the directory exists
	    }

	    return baseDirectory + File.separator + fileName; // Construct the new file path
	}

	private String updateFileVersionArray(String filePath, String docModule) {
		File file = new File(filePath);
		String directory = file.getParent();
		String fileName = file.getName();
		String baseName = fileName;
		String extension = "";
 
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex > 0) {
			baseName = fileName.substring(0, dotIndex);
			extension = fileName.substring(dotIndex);
		}
 
		String directoryPath = directory + File.separator + docModule;
		File moduleDirectory = new File(directoryPath);
		if (!moduleDirectory.exists()) {
			moduleDirectory.mkdirs();
		}
 
		int version = 1;
		File newFile;
		do {
			String newFileName = baseName + "_v" + version + extension;
			newFile = new File(directoryPath, newFileName);
			version++;
		} while (newFile.exists());
 
		return newFile.getAbsolutePath();
	}
	
	public String saveFileDetails(String filename, String module, String tranId, String docType, String filePath,
			String param1, String param2) {
		FileAttributesDAO fileDetails = new FileAttributesDAO();
		fileDetails.setFileName(filename);
		fileDetails.setDocModule(module);
		fileDetails.setTranId(tranId);
		fileDetails.setDocType(docType);
		fileDetails.setFilePath(filePath);
		fileDetails.setAdditional1(param1);
		fileDetails.setAdditional2(param2);
		;
		fileDetails.setStatus("Y");

		FileAttributesDAO savedFileDetails = filerepo.save(fileDetails);

		return savedFileDetails.getSysId().toString();
	}
	
	private String uploadFileArray(byte[] byteArray, String filePath) {
		File file = new File(filePath);
		File parentDir = file.getParentFile();
 
		if (!parentDir.exists()) {
			boolean dirsCreated = parentDir.mkdirs();
			if (!dirsCreated) {
				throw new RuntimeException("Failed to create directory: " + parentDir.getAbsolutePath());
			}
		}
 
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(byteArray);
		} catch (IOException e) {
			e.printStackTrace(); // Log the exception
			throw new RuntimeException("Failed to upload file: " + filePath, e);
		}
		return filePath;
	}
	@Override
	public String uploadMultipleDocuments(List<Map<String, Object>> fileRequests) {
		JSONArray fileResponses = new JSONArray();
		JSONObject overallResponse = new JSONObject();
		for (Map<String, Object> fileRequest : fileRequests) {
			try {
				String base64String = (String) fileRequest.get("base64String");
				byte[] byteArray = Base64.getDecoder().decode(base64String);

				String docModule = (String) fileRequest.get("module");
				String tranId = (String) fileRequest.get("TranId");
				String docType = (String) fileRequest.get("DocType");
				String replaceFlag = (String) fileRequest.get("replaceFlag");
				String actfilename = (String) fileRequest.get("filename");
				String genType = (String) fileRequest.get("genType");
				String dmsStatus = (String) fileRequest.get("dms_status");
				String screenName = (String) fileRequest.get("screenName");
				String uploadscrn = (String) fileRequest.get("uploadscrn");
				String param1 = (String) fileRequest.get("param_add1");
				String param2 = (String) fileRequest.get("param_add2");
				String description = (String) fileRequest.get("description");

				JSONObject response = new JSONObject();
				JSONObject data = new JSONObject();

				String timestamp = String.valueOf(System.currentTimeMillis());
				String filePath = "";
				String fileName = actfilename + "_" + timestamp + "." + genType;

//				if ("DMS".equalsIgnoreCase(screenName)) {
//					Optional<String> pcDescOptional = lmrepo.findPcDescByPcTypeAndPcCode(screenName, uploadscrn);
//					if (pcDescOptional.isPresent()) {
//						String pcDesc = pcDescOptional.get();
//						filePath = pcDesc + fileName;
//					}
//				}
				
				filePath = "D:";

				// Handle file version replacement
				if ("Y".equalsIgnoreCase(replaceFlag)) {
					filePath = updateFileVersionArray(filePath, docModule);
				}
				String genid = saveFileDetails(fileName, docModule, tranId, docType, filePath, param1, param2);

				uploadFileArray(byteArray, filePath);
				data.put("filePath", filePath);
				data.put(dmsStatusProperty, "Y");
				data.put(tranIdProperty, tranId);
				data.put("DocType", docType);
				data.put("doc_sys_id", genid);
				data.put("filename", actfilename);
				response.put("Status", "Success");
				response.put("Message", "File Uploaded successfully");
				response.put("Data", data);

				fileResponses.put(response);

			} catch (Exception e) {
				e.printStackTrace();

				JSONObject errorResponse = new JSONObject();
				errorResponse.put("Status", "Failure");
				errorResponse.put("Message", e.getMessage());
				fileResponses.put(errorResponse);
			}
		}

		overallResponse.put("Overall", fileResponses);
		return overallResponse.toString();
	}
	@Override
	public List<String> getFileBase64Strings(List<String> paths)throws IOException {
		List<String> base64List = new ArrayList<>();

		for (String filePath : paths) {
			File file = new File(filePath);

			if (!file.exists()) {
				throw new IOException("File not found: " + filePath);
			}

			byte[] byteArray = Files.readAllBytes(Paths.get(filePath));
			String base64String = byteArrayToBase64(byteArray);
			base64List.add(base64String);
		}

		return base64List;
	}
	private String byteArrayToBase64(byte[] byteArray) {

		return Base64.getEncoder().encodeToString(byteArray);
	
	}
 
}