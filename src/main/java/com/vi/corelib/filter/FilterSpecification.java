package com.vi.corelib.filter;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FilterSpecification<T> implements Specification<T> {
  private FilterCriteria criteria;

  public FilterSpecification(FilterCriteria filterCriteria) {
    this.criteria = filterCriteria;
  }

  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return Filter.getFilter(root, query, builder, criteria);
  }
}
