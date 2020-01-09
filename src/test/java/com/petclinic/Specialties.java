package com.petclinic;

public class Specialties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialties{" +
                "name='" + name + '\'' +
                '}';
    }
}
