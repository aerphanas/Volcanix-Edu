package org.acme;

public class Developer {
    static long counter = 1;
    private long id;
    private String name;
    
    public long getId() {
    return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void persist() {
        this.id = counter++;
    }
}
