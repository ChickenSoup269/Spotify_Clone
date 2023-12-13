package com.example.spotify;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ApiDataAlbum extends AsyncTask<Void, Void, ArrayList<Album>> {

    private static final String TAG = ApiDataAlbum.class.getSimpleName();
    private ApiDataListener listener;

    public interface ApiDataListener {
        void onDataFetched(ArrayList<Album> data);
        void onError(String errorMessage);
    }

    public ApiDataAlbum(ApiDataListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Album> doInBackground(Void... voids) {
        ArrayList<Album> dataList = new ArrayList<>();
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://6f198bba5dd7414594532bff4666c6b1.api.mockbin.io/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            String jsonResponse = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonResponse);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Phân tích dữ liệu json

                // lấy dữ liệu từ from JSON
                String albumId = jsonObject.getString("albumId");
                String albumTitle = jsonObject.getString("albumTitle");
                String albumThumbnail = jsonObject.getString("albumThumbnail");
                String sortDescription = jsonObject.getString("sortDescription");

                JSONArray songsArray = jsonObject.getJSONArray("songs");
                ArrayList<Songs> songsList = new ArrayList<>();

                for (int j = 0; j < songsArray.length(); j++) {
                    JSONObject songObject = songsArray.getJSONObject(j);
                    String songId = songObject.getString("songId");
                    String title = songObject.getString("title");
                    String artist = songObject.getString("artist");
                    String releaseDate = songObject.getString("releaseDate");
                    String thumbnail = songObject.getString("thumbnail");
                    String audio = songObject.getString("audio");

                    Songs song = new Songs(songId, title, artist, releaseDate, thumbnail, audio);
                    songsList.add(song);
                }

                Album data = new Album(albumId, albumTitle, albumThumbnail, sortDescription, songsList);
                dataList.add(data);
            }

        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error: " + e.getMessage());
            listener.onError(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing stream: " + e.getMessage());
                }
            }
        }

        return dataList;
    }

    @Override
    protected void onPostExecute(ArrayList<Album> dataList) {
        super.onPostExecute(dataList);
        if (dataList != null) {
            listener.onDataFetched(dataList);
        }
    }
}
