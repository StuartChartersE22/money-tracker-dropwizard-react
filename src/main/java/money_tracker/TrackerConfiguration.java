package money_tracker;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class TrackerConfiguration extends Configuration {
    @NotEmpty
    private String dateFormat;

    public String getDateFormat() {
        return dateFormat;
    }
}}
