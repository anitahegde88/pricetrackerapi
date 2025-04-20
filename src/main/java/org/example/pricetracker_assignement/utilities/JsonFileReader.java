package org.example.pricetracker_assignement.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.pricetracker_assignement.eorrs.MessagePool;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

@Component
@Slf4j
public class JsonFileReader {

    public static final String JSON_FILE_NAME = "jsonfiles/actual_price.json";
    public static final String FIELD_ACTUAL_PRICE = "actualPrcie";

    public double readFromJsonFile() throws FileNotFoundException {
        double actcualPrice;
        File file;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            file = new File(classLoader.getResource(JSON_FILE_NAME).getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(file);
            actcualPrice = jsonNode.get(FIELD_ACTUAL_PRICE).asDouble();
        }catch(Exception e){
           throw new FileNotFoundException(MessagePool.JSON_FILE_NOT_FOUND);
        }
        return actcualPrice;
    }
}
