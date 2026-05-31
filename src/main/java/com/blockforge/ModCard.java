package com.blockforge;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

public class ModCard extends VBox {

    private final ModrinthClient.ModInfo mod;
    private final InstallContext ctx;
    private final ContentType type;

    private final ModrinthClient client = new ModrinthClient();

    public ModCard(ModrinthClient.ModInfo mod,
                   InstallContext ctx,
                   ContentType type) {

        this.mod = mod;
        this.ctx = ctx;
        this.type = type;

        build();
    }

    private void build() {

        Label title = new Label(mod.title);
        Label desc = new Label(mod.description);

        Button install = new Button("Install");

        install.setOnAction(e -> installNow());

        getChildren().addAll(title, desc, install);
    }

    private void installNow() {

        new Thread(() -> {

            try {

                System.out.println("INSTALL TYPE = " + type);

                ModrinthClient.ModVersion v =
                        client.getLatestCompatibleVersion(
                                mod.id,
                                ctx.getMinecraftVersion(),
                                ctx.getLoader().getId()
                        );

                if (v == null) {
                    throw new RuntimeException("No version found");
                }

                Path folder = switch (type) {

                    case MOD -> MinecraftLocator.getModsFolder();

                    case RESOURCE_PACK -> MinecraftLocator.getResourcePacksFolder();

                    case SHADER -> MinecraftLocator.getShaderPacksFolder();
                };

                Path target = folder.resolve(v.fileName);

                boolean ok = DownloadManager.downloadFile(v.fileUrl, target);

                if (!ok) throw new RuntimeException("Download failed");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}