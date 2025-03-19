package com.vi.corelib.utils;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class File {
  private File() {

  }

  public static String getResourceAsString(String resourceName) {
    return getStreamAsString(File.class.getClassLoader().getResourceAsStream(resourceName));
  }

  @SneakyThrows
  public static String getStreamAsString(InputStream inputStream) {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    for (int length; (length = inputStream.read(buffer)) != -1; ) {
      result.write(buffer, 0, length);
    }
    // StandardCharsets.UTF_8.name() > JDK 7
    return result.toString("UTF-8");
  }
}
