package svnserver.repository;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Информация о файле.
 *
 * @author a.navrotskiy
 */
public interface FileInfo {
  @NotNull
  String getFileName();

  @NotNull
  Map<String, String> getProperties(boolean includeInternalProps);

  @NotNull
  String getMd5() throws IOException;

  long getSize() throws IOException;

  void copyTo(@NotNull OutputStream stream) throws IOException;

  @NotNull
  InputStream openStream() throws IOException;

  boolean isDirectory() throws IOException;

  @NotNull
  String getKind() throws IOException;

  @NotNull
  Iterable<FileInfo> getEntries() throws IOException;

  @NotNull
  RevisionInfo getLastChange();
}
