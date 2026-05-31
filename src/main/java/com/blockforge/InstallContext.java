package com.blockforge;

public class InstallContext {

    private final String minecraftVersion;
    private final MinecraftLoader loader;

    public InstallContext(String minecraftVersion, MinecraftLoader loader) {
        this.minecraftVersion = minecraftVersion;
        this.loader = loader;
    }

    public String getMinecraftVersion() {
        return minecraftVersion;
    }

    public MinecraftLoader getLoader() {
        return loader;
    }
}