package com.vi.corelib.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.corelib.base.BaseService;

import java.util.List;

public interface FilterService<T> extends BaseService<T> {
  List<T> filterData(String search);
  List<T> filterData(JsonNode search);

}
