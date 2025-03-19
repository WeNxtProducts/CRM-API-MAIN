package com.vi.corelib.base;

import java.util.List;

public interface MasterBaseService<T> {
  List<T> fetchAll();

  T get(Long id);

  T create(T t);

  T update(T t);

  T delete(T t);

  T filter(T t);

  T lookup(T t);
}
