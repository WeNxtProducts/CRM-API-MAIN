package com.vi.corelib.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.vi.corelib.utils.JsonHelper;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FilterSpecificationsBuilder<T> {
  private final List<FilterCriteria> params;

  public FilterSpecificationsBuilder() {
    params = new ArrayList<FilterCriteria>();
  }

  public FilterSpecificationsBuilder with(String key, String operation, Object value) {
    params.add(new FilterCriteria(key, operation, value));
    return this;
  }

  public FilterSpecificationsBuilder with(String search) {
    var matcher = Filter.getMatcher(search);
    while (matcher.find()) {
      this.with(matcher.group(1), matcher.group(2), matcher.group(3));
    }
    return this;
  }
  public FilterSpecificationsBuilder with(JsonNode json) {
    List<String> str = JsonHelper.getKeys(json);
    String operator;
    for(String s : str) {
      if(json.get(s).getNodeType()==JsonNodeType.ARRAY &&  json.get(s).asText().contains(",")) {
        params.add(new FilterCriteria(s, "IN", json.get(s).asText()));
      } else if(json.get(s).getNodeType()==JsonNodeType.STRING &&  json.get(s).asText().contains(",")) {
        params.add(new FilterCriteria(s,"IN",json.get(s).asText()));
      } else if(json.get(s).getNodeType()==JsonNodeType.STRING && json.get(s).asText().contains("%")) {
        params.add(new FilterCriteria(s,"LIKE","%"+json.get(s).asText()+"%"));
      }  else if(json.get(s).getNodeType()==JsonNodeType.STRING && json.get(s).asText().contains("@")) {
        params.add(new FilterCriteria(s, "@", json.get(s).asText()));
      } else if(json.get(s).getNodeType()==JsonNodeType.BOOLEAN && json.get(s).asText().contains("@")) {
        params.add(new FilterCriteria(s, "@", json.get(s).asBoolean()));
      } else if(json.get(s).getNodeType()==JsonNodeType.NUMBER) {
        params.add(new FilterCriteria(s, ":", json.get(s).asText()));
      }  else if(json.get(s).getNodeType()==JsonNodeType.STRING && json.get(s).asText().contains("__")) {
    	System.out.print("-------------------heloo");
        params.add(new FilterCriteria(s, "__", json.get(s).asText()));
      } else {
        params.add(new FilterCriteria(s, ":", json.get(s).asText()));
      }
    }
    return this;
  }
  public Specification<T> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification> specs = new ArrayList<>();
    for (FilterCriteria param : params) {
      FilterSpecification bankSpecification = new FilterSpecification(param);
      specs.add(bankSpecification);
    }

    Specification result = specs.get(0);

    for (int i = 1; i < params.size(); i++) {
      result = Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
