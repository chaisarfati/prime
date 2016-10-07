/**
 * CommandLineArgumentsException.java
 * @author Chris Vig (chris@invictus.so)
 */

package so.invictus.prime;

/**
 * Exception thrown to indicate a problem parsing command line arguments.
 */
public class CommandLineArgumentsException extends Exception
{

  /**
   * Constructs a new exception with the specified detail message.
   * @param message the detail message.
   */
  public CommandLineArgumentsException(String message)
  {
    super(message);
  }

}
