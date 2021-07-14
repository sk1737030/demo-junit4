package step10;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class AddressRetriever {

    private Http http;

    public AddressRetriever(Http http) {
        this.http = http;
    }

    public Address retrieve(double latitude, double longitude) throws IOException, ParseException {
        String params = String.format("lat=%.6f&lon=%.6f", latitude, longitude);

        String response = http.get("http://open.mapquestapi.com/nominatim/v1/reverse?format=json&" + params);

        JSONObject obj = (JSONObject) new JSONParser().parse(response);
        JSONObject address = (JSONObject) obj.get("address");

        String country = (String) address.get("country_code");
        if (!country.equals("us")) {
            throw new UnsupportedOperationException("cannot support non-US addresses at this time");
        }

        String house_number = (String) address.get("house_number");
        String road = (String) address.get("road");
        String city = (String) address.get("city");
        String state = (String) address.get("state");
        String zip = (String) address.get("postcode");
        return new Address(house_number, road, city, state, zip);

    }

}
