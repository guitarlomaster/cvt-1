import models.HttpResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class for http requests.
 * @author Maxim Kokuyskiy
 */
public class HttpRequester {

    /** Resource url for requests. */
    private String url;

    /**
     * HttpRequester constructor.
     * @param url - resource url.
     */
    public HttpRequester(String url) {
        this.url = url;
    }

    /**
     *
     * @param endpoint - endpoint string (ex: /api/blabla).
     * @return HttpResponse instance.
     */
    public HttpResponse makeGetRequest(String endpoint) throws IOException {
        HttpURLConnection con = this.establishConnection("GET", endpoint);
        int status = con.getResponseCode();
        JSONObject responseJSON = this.getResponseData(
                this.getReaderAccordingToStatus(con, status)
        );

        con.disconnect();

        return new HttpResponse(status, responseJSON);
    }

    /**
     * Start requesting.
     * @param method - request method string.
     * @param endpoint - endpoint string (ex: /api/blabla).
     * @return HttpURLConnection instance.
     */
    private HttpURLConnection establishConnection(String method, String endpoint) throws IOException {
        URL url = new URL(this.url + endpoint);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);

        return con;
    }

    /**
     * Gets reader according to response status.
     * @param connection - HttpURLConnection instance.
     * @param status - response status.
     * @return InputStreamReader instance according to a HttpURLConnection stream.
     */
    private Reader getReaderAccordingToStatus(HttpURLConnection connection, int status) throws IOException {
        switch (status) {
            case 200:
                return new InputStreamReader(connection.getInputStream());
            case 404:
            default:
                return new InputStreamReader(connection.getErrorStream());
        }
    }

    /**
     * Collects response into string and generates JSON object.
     * @param reader - reader fetched by getReaderAccordingToStatus {@link HttpRequester#getReaderAccordingToStatus }
     * @return JSON object received from http connection response.
     */
    private JSONObject getResponseData(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return new JSONObject(content.toString());
    }
}
