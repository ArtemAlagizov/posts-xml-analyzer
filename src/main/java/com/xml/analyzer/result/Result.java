package com.xml.analyzer.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * Result of xml analysis. Contains static method to return current date.
 */
@Component
public class Result {
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime analysisDate;

    @Getter
    @Setter
    @JsonProperty("details")
    private ResultDetails resultDetails;

    public static ZonedDateTime getAnalysisDate() {
        return ZonedDateTime.now();
    }
}
