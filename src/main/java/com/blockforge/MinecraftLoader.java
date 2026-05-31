package com.blockforge;

public enum MinecraftLoader {
    FABRIC("fabric"),
    FORGE("forge"),
    NEOFORGE("neoforge");

    private final String id;

    MinecraftLoader(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}