package com.blockforge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Theme {

    public static String createTempStylesheet() {

        String css = """

                /* =========================
                   ROOT / BASE
                   ========================= */
                .root {
                    -fx-background-color: #0f1115;
                    -fx-font-family: "Segoe UI";
                }

                /* =========================
                   SIDEBAR (Fluent style)
                   ========================= */
                .sidebar {
                    -fx-background-color: #151922;
                    -fx-padding: 16;
                    -fx-spacing: 10;
                }

                .sidebar Label {
                    -fx-text-fill: #cfd3dc;
                    -fx-font-size: 13;
                    -fx-padding: 6 10;
                    -fx-background-radius: 8;
                }

                .sidebar Label:hover {
                    -fx-background-color: #232838;
                }

                /* =========================
                   TITLE
                   ========================= */
                .title {
                    -fx-text-fill: white;
                    -fx-font-size: 22;
                    -fx-font-weight: bold;
                }

                /* =========================
                   BUTTONS (general)
                   ========================= */
                .button {
                    -fx-background-color: #2a3447;
                    -fx-text-fill: white;
                    -fx-background-radius: 10;
                    -fx-padding: 8 14;
                }

                .button:hover {
                    -fx-background-color: #3a4a66;
                    -fx-cursor: hand;
                }

                /* Primary install button */
                .install-btn {
                    -fx-background-color: #4c8dff;
                    -fx-text-fill: white;
                    -fx-background-radius: 10;
                    -fx-padding: 8 14;
                }

                .install-btn:hover {
                    -fx-background-color: #6aa4ff;
                }

                /* =========================
                   SEARCH FIELD
                   ========================= */
                .search-field {
                    -fx-background-color: #1c2230;
                    -fx-text-fill: white;
                    -fx-background-radius: 10;
                    -fx-padding: 10;
                }

                .search-field:focused {
                    -fx-border-color: #4c8dff;
                    -fx-border-radius: 10;
                    -fx-background-color: #232b3d;
                }

                /* =========================
                   COMBOBOX (FIXED LOOK)
                   ========================= */
                .combo-box {
                    -fx-background-color: #1c2230;
                    -fx-background-radius: 10;
                    -fx-padding: 4;
                }

                .combo-box .arrow-button {
                    -fx-background-color: transparent;
                }

                .combo-box .arrow {
                    -fx-background-color: #cfd3dc;
                }

                .combo-box .list-cell {
                    -fx-background-color: #1c2230;
                    -fx-text-fill: #cfd3dc;
                    -fx-padding: 6 10;
                }

                .combo-box-popup .list-view {
                    -fx-background-color: #171c28;
                    -fx-background-radius: 10;
                    -fx-padding: 4;
                }

                .combo-box-popup .list-cell {
                    -fx-background-color: transparent;
                    -fx-text-fill: #cfd3dc;
                }

                .combo-box-popup .list-cell:hover {
                    -fx-background-color: #2a3447;
                    -fx-text-fill: white;
                }

                .combo-box:focused {
                    -fx-border-color: #4c8dff;
                    -fx-border-radius: 10;
                }

                /* =========================
                   MOD CARDS
                   ========================= */
                .mod-card {
                    -fx-background-color: #171c28;
                    -fx-background-radius: 14;
                    -fx-padding: 14;
                    -fx-spacing: 6;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.45), 14, 0.25, 0, 4);
                }

                .mod-card:hover {
                    -fx-background-color: #1d2433;
                }

                .mod-title {
                    -fx-text-fill: white;
                    -fx-font-size: 15;
                    -fx-font-weight: bold;
                }

                .mod-desc {
                    -fx-text-fill: #aab2c0;
                    -fx-font-size: 12;
                }

                /* =========================
                   SCROLL PANE
                   ========================= */
                .scroll-pane {
                    -fx-background: #0f1115;
                    -fx-border-color: transparent;
                }

                .scroll-bar:vertical {
                    -fx-background-color: transparent;
                }

                .scroll-bar:vertical .thumb {
                    -fx-background-color: #2a3447;
                    -fx-background-radius: 10;
                }

                /* =========================
                   SEPARATOR
                   ========================= */
                .separator {
                    -fx-opacity: 0.2;
                }

                """;

        try {
            File temp = File.createTempFile(
                    "blockforge-theme",
                    ".css"
            );

            temp.deleteOnExit();

            try (FileWriter writer = new FileWriter(temp)) {
                writer.write(css);
            }

            return temp.toURI().toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}