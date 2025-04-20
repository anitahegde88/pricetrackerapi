package org.example.pricetracker_assignement.eorrs;

public class MessagePool {

    private MessagePool() {
    }

    public static final String HTTP_OK_MESSAGE = "You will receive email at provided frequency";
    public static final String INVALID_FREQUENCY = "Invalid schedule frequency:";
    public static final String JSON_FILE_NOT_FOUND = "Json file containing the actual price is not available";
}
