package models;

import org.json.JSONObject;

/**
 * Class for http response data representation.
 * @author  Maxim Kokuyskiy
 */
public class HttpResponse {

    /** Response status. */
    private int status;
    /** Response data as JSON object. */
    private JSONObject data;

    /**
     * HttpResponse constructor.
     * @param status - response status.
     * @param data - response data as JSON object.
     */
    public HttpResponse(int status, JSONObject data) {
        this.status = status;
        this.data = data;
    }

    /**
     * Getter for status private property. {@link HttpResponse#status}
     * @return response status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Getter for JSON data private property. {@link HttpResponse#data}
     * @return response JSON data.
     */
    public JSONObject getData() {
        return data;
    }
}
