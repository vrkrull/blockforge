package com.blockforge;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModrinthClient {

    private static final String API = "https://api.modrinth.com/v2";

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public static class ModInfo {
        public String id;
        public String title;
        public String description;

        public ModInfo(String id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }
    }

    public static class ModVersion {
        public String fileName;
        public String fileUrl;

        public ModVersion(String fileName, String fileUrl) {
            this.fileName = fileName;
            this.fileUrl = fileUrl;
        }
    }

    // async search used by MainWindow
    public void searchAsync(String query, Consumer<List<ModInfo>> callback) {

        HttpUrl url = HttpUrl.parse(API + "/search")
                .newBuilder()
                .addQueryParameter("query", query)
                .build();

        Request req = new Request.Builder().url(url).build();

        client.newCall(req).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                callback.accept(List.of());
            }

            @Override
            public void onResponse(Call call, Response res) throws IOException {

                List<ModInfo> out = new ArrayList<>();

                JsonNode root = mapper.readTree(res.body().string());
                JsonNode hits = root.get("hits");

                if (hits != null) {
                    for (JsonNode h : hits) {
                        out.add(new ModInfo(
                                h.get("project_id").asText(),
                                h.get("title").asText(),
                                h.path("description").asText("")
                        ));
                    }
                }

                callback.accept(out);
            }
        });
    }

    public ModVersion getLatestCompatibleVersion(String modId, String mcVersion, String loader)
            throws IOException {

        Request req = new Request.Builder()
                .url(API + "/project/" + modId + "/version")
                .build();

        try (Response res = client.newCall(req).execute()) {

            JsonNode root = mapper.readTree(res.body().string());

            if (!root.isArray() || root.size() == 0) return null;

            for (JsonNode v : root) {

                JsonNode files = v.get("files");
                if (files == null || files.size() == 0) continue;

                JsonNode file = files.get(0);

                return new ModVersion(
                        file.get("filename").asText(),
                        file.get("url").asText()
                );
            }

            return null;
        }
    }
}