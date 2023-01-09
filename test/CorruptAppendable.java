import java.io.IOException;

/**
 * Represents a corrupted appendable. This will allow
 * for more coherent testing and better representation
 * of methods that throw IOExceptions.
 */
public class CorruptAppendable implements Appendable {

  /**
   * Represents a corrupted appendable. In this
   * case, appending will result in an IOException
   * instead of the typical functionality of the
   * append function. This will allow for testing
   * to be more thorough so that IOExceptions
   * can be tested.
   */
  public CorruptAppendable() {
    //empty constructor for testing purposes :)
  }

  /**
   * Overrides the traditional append function in
   * order to represent a corrupted version for
   * better testing (takes in a csq). Returns an IOException,
   * throwing it because the appendable is corrupted.
   *
   * @param csq The character sequence to append.  If {@code csq} is
   *            {@code null}, then the four characters {@code "null"} are
   *            appended to this Appendable.
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Please no!");
  }

  /**
   * Overrides the traditional append function in
   * order to represent a corrupted version for
   * better testing (takes in a csq, start, and end).
   * Returns an IOException, throwing it because the
   * appendable is corrupted.
   *
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Cannot append!");
  }

  /**
   * Overrides the traditional append function in
   * order to represent a corrupted version for
   * better testing (takes in a char). Returns an
   * IOException, throwing it because the appendable is corrupted.
   *
   * @param c The character to append
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Cannot append this character!");
  }
}
