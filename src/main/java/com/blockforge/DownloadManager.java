package com.blockforge;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DownloadManager {

    public static boolean downloadFile(String url, Path target) {

        try {
            System.out.println("Downloading: " + url);
            System.out.println("To: " + target);

            Files.createDirectories(target.getParent());

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", "BlockForge");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(30000);

            int code = conn.getResponseCode();
            System.out.println("HTTP: " + code);

            if (code != 200) {
                throw new RuntimeException("HTTP " + code);
            }

            try (InputStream in = conn.getInputStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }

            return true;

        } catch (Exception e) {
            System.out.println("DOWNLOAD ERROR:");
            e.printStackTrace();
            return false;
        }
    }
}