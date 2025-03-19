package com.vi.corelib.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.StringWriter;

@Getter
@Setter
public class TemplateParser {
  private String template;
  private Object data;

  public TemplateParser() {
  }

  public TemplateParser(String template, Object data) {
    this.template = template;
    this.data = data;
  }

  @SneakyThrows
  public String parse(String template, Object data) {
    this.template = template;
    this.data = data;
    return this.parse();
  }

  @SneakyThrows
  public String parse() {
    var instance = new Template("sm-parser", this.template, new Configuration(Configuration.VERSION_2_3_31));
    var out = new StringWriter();
    instance.process(this.data, out);
    return out.toString();
  }
}
