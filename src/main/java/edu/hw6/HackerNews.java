package edu.hw6;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;

public class HackerNews {
    private final static String TOP_NEWS_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private final static String STORY_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private final static Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"(?<title>(.*?))\"");

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        long[] storiesIds;
        try {
            var uri = new URI(TOP_NEWS_URL);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri).build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            storiesIds = gson.fromJson(response.body(), long[].class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return storiesIds;
    }

    public static String news(long id) {
        String title = "";
        try {
            var uri = new URI(String.format(STORY_URL, id));
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri).build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var matcher = TITLE_PATTERN.matcher(response.body());
            if (matcher.find()) {
                title = matcher.group("title");
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return title;
    }

}
