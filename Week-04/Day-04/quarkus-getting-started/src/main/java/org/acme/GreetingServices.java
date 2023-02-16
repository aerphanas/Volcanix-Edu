package org.acme;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingServices {
    
    public String greeting(String name){
        return "Hello " + name;
    }
}
