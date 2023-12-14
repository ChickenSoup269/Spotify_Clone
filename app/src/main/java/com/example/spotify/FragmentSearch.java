package com.example.spotify;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private ListView listView;
    private EditText searchEditText;
    private FloatingActionButton floatingActionButton;

    private List<searchmodel> allAlbums;
    private List<searchmodel> filteredAlbums;

    private searchAdapter albumAdapter;

    private final String albumUrl = "https://b139d9db0c4c478c871449e27df6238f.api.mockbin.io/";

    public FragmentSearch() {
        // Khởi tạo fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Ánh xạ các thành phần giao diện
        listView = view.findViewById(R.id.listview);
        searchEditText = view.findViewById(R.id.editTextText);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        // Khởi tạo danh sách và adapter cho album
        allAlbums = new ArrayList<>();
        filteredAlbums = new ArrayList<>();
        albumAdapter = new searchAdapter(requireContext(), filteredAlbums);
        listView.setAdapter(albumAdapter);

        // Tải dữ liệu từ API cho album
        loadAlbumData();

        // Thêm sự kiện cho ô tìm kiếm
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterAlbums(editable.toString());
            }
        });

        // Thêm sự kiện click cho Floating Action Button
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nút Floating Action Button được nhấn
                goBackToTrangChu();
            }
        });

        // Thêm sự kiện click cho mỗi item trên ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                searchmodel selectedAlbum = filteredAlbums.get(position);
                Toast.makeText(requireContext(), "Đã chọn album: " + selectedAlbum.getAlbumTitle(), Toast.LENGTH_SHORT).show();
                // Thực hiện các hành động khác khi chọn một album
            }
        });

        return view;
    }

    // Phương thức tải dữ liệu từ API cho album
    private void loadAlbumData() {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                albumUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Log dữ liệu nhận được từ API
                        Log.d("API_RESPONSE", "Response: " + response.toString());

                        // Parse JSON và cập nhật danh sách album
                        allAlbums = parseJsonArray(response);
                        filteredAlbums.addAll(allAlbums);
                        albumAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleApiError(error);
                    }
                }
        );

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonArrayRequest);
    }

    // Phương thức parse JSON array thành danh sách album
    private List<searchmodel> parseJsonArray(JSONArray jsonArray) {
        List<searchmodel> albums = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String albumTitle = jsonObject.optString("albumTitle", "");
                String artistName = jsonObject.optString("artistName", "");
                String albumThumbnail = jsonObject.optString("albumThumbnail", "");

                searchmodel album = new searchmodel(albumTitle, artistName, albumThumbnail);
                albums.add(album);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private void filterAlbums(String query) {
        filteredAlbums.clear();
        for (searchmodel album : allAlbums) {
            if (album.getAlbumTitle().toLowerCase().contains(query.toLowerCase()) ||
                    album.getArtistName().toLowerCase().contains(query.toLowerCase())) {
                filteredAlbums.add(album);
            }
        }
        albumAdapter.notifyDataSetChanged();
    }

    // Phương thức xử lý lỗi từ API
    private void handleApiError(VolleyError error) {
        if (error.networkResponse == null) {
            // Log lỗi khi không có kết nối internet
            Log.e("API_ERROR", "Không có kết nối internet", error);
            Toast.makeText(requireContext(), "Không có kết nối internet", Toast.LENGTH_SHORT).show();
        } else {
            // Log lỗi khi có lỗi trong quá trình thực hiện yêu cầu API
            Log.e("API_ERROR", "Lỗi khi tải dữ liệu từ API", error);
            Toast.makeText(requireContext(), "Lỗi khi tải dữ liệu từ API", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức quay trở lại màn hình TrangChu
    private void goBackToTrangChu() {
        if (getActivity() instanceof TrangChu) {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
