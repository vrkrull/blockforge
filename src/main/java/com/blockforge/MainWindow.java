package com.blockforge;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;

public class MainWindow {

    private final BorderPane root = new BorderPane();

    private final VBox resultsBox = new VBox(10);
    private final TextField searchField = new TextField();

    private final ModrinthClient client = new ModrinthClient();

    private final ComboBox<String> versionBox = new ComboBox<>();
    private final ComboBox<MinecraftLoader> loaderBox = new ComboBox<>();

    public MainWindow() {
        buildUI();
        showHome();
    }

    public Parent getRoot() {
        return root;
    }

    private void buildUI() {

        // LEFT SIDEBAR
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.getStyleClass().add("sidebar");

        Label title = new Label("BlockForge");
        title.getStyleClass().add("title");

        Button homeBtn = new Button("Home");
        Button modsBtn = new Button("Mods");
        Button packsBtn = new Button("Resource Packs");
        Button shadersBtn = new Button("Shaders");

        homeBtn.setOnAction(e -> showHome());
        modsBtn.setOnAction(e -> search(ContentType.MOD));
        packsBtn.setOnAction(e -> search(ContentType.RESOURCE_PACK));
        shadersBtn.setOnAction(e -> search(ContentType.SHADER));

        sidebar.getChildren().addAll(
                title,
                new Separator(),
                homeBtn,
                modsBtn,
                packsBtn,
                shadersBtn
        );

        // TOP BAR
        searchField.setPromptText("Search Modrinth...");

        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(e -> search(ContentType.MOD));

        versionBox.getItems().addAll("1.21.1", "1.20.4", "1.20.1");
        versionBox.setValue("1.21.1");

        loaderBox.getItems().addAll(
                MinecraftLoader.FABRIC,
                MinecraftLoader.FORGE,
                MinecraftLoader.NEOFORGE
        );
        loaderBox.setValue(MinecraftLoader.FABRIC);

        HBox topBar = new HBox(10,
                searchField,
                searchBtn,
                versionBox,
                loaderBox
        );

        topBar.setPadding(new Insets(10));

        VBox center = new VBox(10, topBar, resultsBox);
        center.setPadding(new Insets(10));

        ScrollPane scroll = new ScrollPane(center);
        scroll.setFitToWidth(true);

        root.setLeft(sidebar);
        root.setCenter(scroll);
    }

    private void search(ContentType type) {

        String query = searchField.getText().trim();

        if (query.isEmpty()) {
            return;
        }

        resultsBox.getChildren().clear();
        resultsBox.getChildren().add(new Label("Searching..."));

        client.searchAsync(query, results -> {

            Platform.runLater(() -> {

                resultsBox.getChildren().clear();

                InstallContext ctx = new InstallContext(
                        versionBox.getValue(),
                        loaderBox.getValue()
                );

                for (ModrinthClient.ModInfo mod : results) {

                    resultsBox.getChildren().add(
                            new ModCard(mod, ctx, type)
                    );
                }
            });
        });
    }

    private void showHome() {

        resultsBox.getChildren().setAll(
                new Label("Welcome to BlockForge")
        );
    }
}