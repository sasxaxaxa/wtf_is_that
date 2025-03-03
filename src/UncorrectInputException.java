public class UncorrectInputException extends RuntimeException {
  public UncorrectInputException(String message) {
    System.out.println(message);
  }

  public UncorrectInputException(String message, Throwable cause) {
    super(message, cause);
  }
}
