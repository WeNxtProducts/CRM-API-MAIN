/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.fileattributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonArray;


public interface FileAttributesServiceCustom {

	String uploadMultipleDocuments(List<Map<String, Object>> fileRequests);

	List<String> getFileBase64Strings(List<String> paths) throws IOException;

}
