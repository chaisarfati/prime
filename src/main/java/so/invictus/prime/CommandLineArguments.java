/**
 * CommandLineArguments.java
 * @author Chris Vig (chris@invictus.so)
 */

package so.invictus.prime;

/**
 * Class for parsing command line arguments.
 */
public class CommandLineArguments
{

  /* ---- Fields ---- */

  private int threadCount = 1;
  private int duration = 0;

  /* ---- Constructors ---- */

  /**
   * Constructs a new <code>CommandLineArguments</code> class.
   * @param args the command line arguments to parse.
   */
  public CommandLineArguments(String[] args)
    throws CommandLineArgumentsException
  {
    for (int index = 0; index < args.length; index++)
    {
      int additionalArgumentsConsumed = parseArgument(args, index);
      index += additionalArgumentsConsumed;
    }
    validateArguments();
  }

  /* ---- Properties ---- */

  /**
   * The number of threads which should be run.
   */
  public int getThreadCount()
  {
    return threadCount;
  }

  /**
   * The duration (in milliseconds) that calculations should be run.
   */
  public int getDuration()
  {
    return duration;
  }

  /* ---- Argument Parsing ---- */

  /**
   * Parses an argument.
   * @param args the command line arguments to parse.
   * @param index the index of the next argument to parse.
   * @return the number of additional argument strings consumed.
   */
  private int parseArgument(String[] args, int index)
    throws CommandLineArgumentsException
  {
    String arg = args[index];
    switch (arg)
    {
    case "-t":
    case "--threads":
      return parseThreadsArgument(args, index);

    case "-d":
    case "--duration":
      return parseDurationArgument(args, index);

    default:
      throw new CommandLineArgumentsException("Unrecognized argument: " + arg);
    }
  }

  /**
   * Parses the -t or --threads argument.
   */
  private int parseThreadsArgument(String[] args, int index)
    throws CommandLineArgumentsException
  {
    final int MIN_THREAD_COUNT = 1;
    final int MAX_THREAD_COUNT = 16;

    threadCount = parseInteger(args, index, 1);
    if (threadCount < MIN_THREAD_COUNT || MAX_THREAD_COUNT < threadCount)
    {
      String message = String.format("Argument %s is invalid - thread count must be between %d and %d.",
				     args[index],
				     MIN_THREAD_COUNT,
				     MAX_THREAD_COUNT);
      throw new CommandLineArgumentsException(message);
    }

    return 1;
  }

  /**
   * Parses the -d or --duration argument.
   */
  private int parseDurationArgument(String[] args, int index)
    throws CommandLineArgumentsException
  {
    final int MIN_DURATION = 1;

    duration = parseInteger(args, index, 1);
    if (duration < MIN_DURATION)
    {
      String message = String.format("Argument %s is invalid - duration must be greater than or equal to %d.",
				     args[index],
				     MIN_DURATION);
      throw new CommandLineArgumentsException(message);
    }

    return 1;
  }

  /* ---- Argument Validation ---- */

  /**
   * Validates that all arguments are correctly specified.
   */
  private void validateArguments()
    throws CommandLineArgumentsException
  {
  }

  /* ---- Utility ---- */

  /**
   * Parses an integer argument.
   */
  private int parseInteger(String[] args, int index, int offset)
    throws CommandLineArgumentsException
  {
    verifyEnoughArguments(args, index, offset);
    try
    {
      return Integer.parseInt(args[index + offset]);
    }
    catch (NumberFormatException ex)
    {
      String message = String.format("Argument %s is invalid - %s is not a valid number.",
				     args[index],
				     args[index + offset]);
      throw new CommandLineArgumentsException(message);
    }
  }

  /**
   * Verifies that there are enough arguments left to parse.
   */
  private void verifyEnoughArguments(String[] args, int index, int required)
    throws CommandLineArgumentsException
  {
    if (index + required >= args.length)
    {
      String message = String.format("Argument %s is invalid - expected at least %d parameter%s.",
				     args[index],
				     required,
				     (required != 1 ? "s" : ""));
      throw new CommandLineArgumentsException(message);
    }
  }

}
