package org.example.pricetracker_assignement.utilities;

import lombok.NoArgsConstructor;
import org.example.pricetracker_assignement.eorrs.MessagePool;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class FrequencyParser {

    public long parseFrequency(String frequency) {
        Long number = Long.parseLong(frequency.substring(0, frequency.length() - 1));
        char unit = frequency.charAt(frequency.length() - 1);
        switch (unit) {
            case 'm':
                return number * 60 * 1000;
            default:
                throw new IllegalArgumentException(MessagePool.INVALID_FREQUENCY+ frequency);
        }
    }
}
