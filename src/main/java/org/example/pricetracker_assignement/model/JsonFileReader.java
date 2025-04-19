package org.example.pricetracker_assignement.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

@Component
@Slf4j
public class JsonFileReader {

    public double readFromJsonFile() throws FileNotFoundException {
        double actcualPrice =0.0;
        File file = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            file = new File(classLoader.getResource("jsonfiles/actual_price.json").getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(file);
            actcualPrice = jsonNode.get("actualPrcie").asDouble();
        }catch(Exception e){
           throw new FileNotFoundException("Json file containing the actual price is not available");
        }
        return actcualPrice;
    }
}
