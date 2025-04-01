package com.vi.corelib.filter;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.corelib.base.BaseService;

public interface FilterService<T> extends BaseService<T> {
  List<T> filterData(String search);
  List<T> filterData(JsonNode search, int page, int size);

}
