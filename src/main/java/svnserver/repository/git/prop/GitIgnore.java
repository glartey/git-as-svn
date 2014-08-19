package svnserver.repository.git.prop;

import org.jetbrains.annotations.NotNull;
import org.tmatesoft.svn.core.SVNProperty;

import java.util.Map;

/**
 * Parse and processing .gitignore.
 *
 * @author Artem V. Navrotskiy <bozaro@users.noreply.github.com>
 */
public class GitIgnore implements GitProperty {
  @NotNull
  private final static String EOL_PREFIX = "eol=";
  @NotNull
  private final String content;

  /**
   * Parse and store .gitignore data.
   *
   * @param content Original file content.
   */
  public GitIgnore(@NotNull String content) {
    this.content = content;
  }

  @Override
  public void apply(@NotNull Map<String, String> props) {
    if (!content.isEmpty()) {
      props.put(SVNProperty.INHERITABLE_IGNORES, content);
    }
  }

  @Override
  public void applyOnChild(@NotNull String path, @NotNull Map<String, String> props) {
  }
}