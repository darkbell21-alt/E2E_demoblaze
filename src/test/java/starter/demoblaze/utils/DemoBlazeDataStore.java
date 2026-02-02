package starter.demoblaze.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class DemoBlazeDataStore {

    public static JsonNode getUserData(String userKey) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = DemoBlazeDataStore.class.getClassLoader()
                .getResourceAsStream("test-data/purchase-data-demoblaze.json");
        return mapper.readTree(inputStream).get(userKey);
    }

    public static List<Map<String, String>> getCheckoutList(String userKey) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = DemoBlazeDataStore.class.getClassLoader()
                .getResourceAsStream("test-data/purchase-data-demoblaze.json");
        JsonNode node = mapper.readTree(inputStream).get(userKey).get("checkoutData");
        return mapper.convertValue(node, new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, String>>>() {});
    }
}
