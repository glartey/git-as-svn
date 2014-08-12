package svnserver.server.command;

import org.jetbrains.annotations.NotNull;
import svnserver.parser.SvnServerWriter;
import svnserver.server.SessionContext;
import svnserver.server.error.ClientErrorException;
import svnserver.server.step.CheckPermissionStep;

import java.io.IOException;

/**
 * SVN client command base class.
 * Must be stateless and thread-safe.
 *
 * @author a.navrotskiy
 */
public abstract class BaseCmd<T> {
  /**
   * Arguments class.
   *
   * @return Arguments class.
   */
  @NotNull
  public abstract Class<T> getArguments();

  public final void process(@NotNull SessionContext context, @NotNull T args) throws IOException {
    context.push(new CheckPermissionStep(sessionContext -> processCommand(sessionContext, args)));
  }

  /**
   * Process command.
   *
   * @param context Session context.
   * @param args    Command arguments.
   */
  protected abstract void processCommand(@NotNull SessionContext context, @NotNull T args) throws IOException, ClientErrorException;

  protected int getRevision(int[] rev, int defaultRevision) {
    return rev.length > 0 ? rev[0] : defaultRevision;
  }

  public static void sendError(SvnServerWriter writer, int code, String msg) throws IOException {
    writer
        .listBegin()
        .word("failure")
        .listBegin()
        .listBegin()
        .number(code)
        .string(msg)
        .string("...")
        .number(0)
        .listEnd()
        .listEnd()
        .listEnd();
  }
}