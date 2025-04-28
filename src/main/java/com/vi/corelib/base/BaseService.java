package com.vi.corelib.base;

import java.time.LocalDate;
import java.util.List;

import com.vi.model.dto.LeadDTOCustom;

public interface BaseService<T> {
  List<T> fetchAll();

  T get(Long id);

  T create(T t);

  T update(T t);

  Boolean delete(Long id);

}
