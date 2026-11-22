package com.nova.client.module;

public class BoolSetting {
    private final String name;
    private boolean value;

    public BoolSetting(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public boolean get() { return value; }
    public void toggle() { value = !value; }
}
