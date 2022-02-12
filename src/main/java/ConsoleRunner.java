import models.HttpResponse;
import models.UserResponseData;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for running and looping console application.
 * @author Maxim Kokuyskiy
 */
public class ConsoleRunner {


    /**
     * Method for running application loop.
     */
    public static void run() throws IOException {
        int userId = ConsoleRunner.readLineAndGetUserId();

        // Make request
        HttpRequester http = new HttpRequester("https://reqres.in");
        HttpResponse response = http.makeGetRequest("/api/users/" + userId);
        UserResponseData user;

        // Check response status and decide exit app or continue.
        switch (response.getStatus()) {
            case 200:
                break;
            case 404:
                System.out.println("User not found.");
                return;
            default:
                System.out.println("An error accrued.");
                return;
        }

        // Instantiate "UserResponseData" and catch exception if JSON is not as valid as expected.
        try {
            user = new UserResponseData(response.getData());
        } catch (JSONException e) {
            System.out.println("Exception in models.UserResponseData constructor: " + e.getMessage());
            System.out.println("Probably the problem cause is in JSON structure.");
            return;
        }

        System.out.println(user.getFirst_name() + " " + user.getLast_name());

        ConsoleRunner.run();
    }

    /**
     * Method for reading value from console, converting to int and returning.
     * @return user id as int.
     */
    private static int readLineAndGetUserId() throws IOException {
        System.out.print("Please, enter user id: ");
        BufferedReader actionReader = new BufferedReader(new InputStreamReader(System.in));
        String userId = actionReader.readLine();

        try {
            return Integer.parseInt(userId);
        } catch (NumberFormatException exception) {
            System.out.println("User id is not valid, please enter it again.");
            return ConsoleRunner.readLineAndGetUserId();
        }
    }

}
