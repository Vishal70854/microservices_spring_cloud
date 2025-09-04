package com.in28minutes.microservices.limits_service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// this annotation will fetch values from application.properties and
// it takes tag name under parenthesis further that will be stored in variable mentioned below from app.properties
@Component
@ConfigurationProperties("limits-service")
public class Configuration {

    private int minimum;    //limits-service.minimum value will be mapped to this minimum varialbe from application.properties

    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
