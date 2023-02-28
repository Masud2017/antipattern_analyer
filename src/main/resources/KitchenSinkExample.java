import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.sql.SQLException;

public class KitchenSinkExample {
    public void kitchenSink() throws FileNotFoundException, ParseException, SQLException, IOException {
        // Code that can throw FileNotFoundException, ParseException, SQLException, or IOException
    }

    public void anotherKitchenSink() throws FileNotFoundException, ParseException, SQLException, IOException {
        // Code that can throw FileNotFoundException, ParseException, SQLException, or IOException
    }

    public void rightWayOfHandleIt () throws IOException {
        // Code that throws only IOException
    }

    public void handleIndividualExceptions() throws FileNotFoundException, ParseException, SQLException, IOException {
        try {
            // Code that can throw any of the exceptions individually
        } catch (FileNotFoundException | ParseException | SQLException | IOException e) {
            // Handle each exception individually
        }
    }

    public void handleFewExceptions() throws IOException, ParseException {
        try {
            // Code that can throw IOException or ParseException
        } catch (IOException | ParseException e) {
            // Handle both exceptions together
        }
    }

    public void handleAllExceptions() {
        try {
            // Code that can throw any exception
        } catch (Exception e) {
            // Handle all exceptions together
        }
    }

    public void handleNoneOfTheExceptions() {
        try {
            // Code that doesn't throw any exceptions
        } catch (Exception e) {
            // No exceptions to handle
        }
    }

    public void rethrowExceptions() throws FileNotFoundException, ParseException, SQLException, IOException {
        try {
            // Code that can throw FileNotFoundException, ParseException, SQLException, or IOException
        } catch (Exception e) {
            // Rethrow the same exception
            throw e;
        }
    }

    public void throwCustomException() throws CustomException {
        // Code that throws a custom exception
        throw new CustomException();
    }

    public void throwSingleException() throws IOException {
        // Code that throws only IOException
        throw new IOException();
    }

    public void handleExceptionsInMethodCall() throws IOException {
        // Call a method that throws an exception
        anotherKitchenSink();
    }

    private static class CustomException extends Exception {}
}