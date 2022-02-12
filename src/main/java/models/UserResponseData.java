package models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for converting JSON object to java object.
 * @author Maxim Kokuyskiy
 */
public class UserResponseData {

    /** User's first name */
    private String first_name;
    /** User's last name */
    private String last_name;


    /**
     * UserResponseData constructor.
     * @param response - user data as JSON object
     */
    public UserResponseData(JSONObject response) throws JSONException {
        JSONObject data = response.getJSONObject("data");

        first_name = data.getString("first_name");
        last_name = data.getString("last_name");
    }

    /**
     * Getter for first_name private property. {@link UserResponseData#first_name}
     * @return User's first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Getter for last_name private property. {@link UserResponseData#last_name}
     * @return User's last name
     */
    public String getLast_name() {
        return last_name;
    }
}
