package step10;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddressRetrieverTest {

    @Mock
    private Http http;
    @InjectMocks
    private AddressRetriever retriever;

    @BeforeEach
    public void createRetriever() {
        retriever = new AddressRetriever(http);
        MockitoAnnotations.initMocks(this);        //mock(http.class)와 동일하다
        // 목 객체를 주입할려고 생성자를 탐색 -> 세터 메서드 -> 필드 탐색
    }

    @Test
    void answerAppropriateAddressForValidCoordinatesWithStub() throws IOException, ParseException {
        // Given
        Http http = url -> {
            if (!url.contains("lat=38.000000&lon=-104.000000")) {
                fail("url " + url + " does not contain correct params");
            }
            return "{\"address\": {"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}";
        };
        AddressRetriever retriever = new AddressRetriever(http);

        // When
        Address address = retriever.retrieve(38.0, -104.0);

        // Then
        assertEquals(address.houseNumber, "324");
        assertEquals(address.road, "North Tejon Street");
        assertEquals(address.city, "Colorado Springs");
        assertEquals(address.state, "Colorado");
        assertEquals(address.zip, "80903");
    }

    @Test
    void answerAppropriateAddressForValidCoordinatesWithMock() throws IOException, ParseException {
        // Given
        Http http = mock(Http.class);
        when(http.get(contains("lat=38.000000&lon=-104.000000")))
            .thenReturn("{\"address\": {"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}");

        AddressRetriever retriever = new AddressRetriever(http);

        // When
        Address address = retriever.retrieve(38.0, -104.0);

        // Then
        assertEquals(address.houseNumber, "324");
        assertEquals(address.road, "North Tejon Street");
        assertEquals(address.city, "Colorado Springs");
        assertEquals(address.state, "Colorado");
        assertEquals(address.zip, "80903");
    }

    @Test
    void answerAppropriateAddressForValidCoordinatesWithMockAnnotation() throws IOException, ParseException {
        // Given
        when(http.get(contains("lat=38.000000&lon=-104.000000")))
            .thenReturn("{\"address\": {"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}");

        // When
        Address address = retriever.retrieve(38.0, -104.0);

        // Then
        assertEquals(address.houseNumber, "324");
        assertEquals(address.road, "North Tejon Street");
        assertEquals(address.city, "Colorado Springs");
        assertEquals(address.state, "Colorado");
        assertEquals(address.zip, "80903");
    }
}