/**
 * Program.java
 * @author Chris Vig (chris@invictus.so)
 */

package so.invictus.prime;

/**
 * Main program class.
 */
public class Program
{

  /* ---- Constants ---- */

  private static final int NORMAL_EXIT_CODE = 0;
  private static final int FAILURE_EXIT_CODE = 1;

  /* ---- Public Methods ---- */

  /**
   * Main program entry point.
   */
  public static void main(String[] args)
  {
    try
    {
      CommandLineArguments parsedArgs = new CommandLineArguments(args);
      System.out.printf("threadCount: %d\n", parsedArgs.getThreadCount());
      System.out.printf("duration: %d\n", parsedArgs.getDuration());
    }
    catch (CommandLineArgumentsException ex)
    {
      System.out.println(ex.getMessage());
      System.exit(1);
    }
  }

}
