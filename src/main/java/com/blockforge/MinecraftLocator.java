package com.blockforge;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MinecraftLocator {

    public static Path getJavaMinecraft() {
        return Paths.get(System.getProperty("user.home"), ".minecraft");
    }

    public static Path getModsFolder() {
        return getJavaMinecraft().resolve("mods");
    }

    public static Path getResourcePacksFolder() {
        return getJavaMinecraft().resolve("resourcepacks");
    }

    public static Path getShaderPacksFolder() {
        return getJavaMinecraft().resolve("shaderpacks");
    }
}