package com.vi.corelib.filter;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterCriteria {
  private String key;
  private String operation;
  private Object value;
}
