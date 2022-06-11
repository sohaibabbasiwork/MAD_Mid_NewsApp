package com.example.mid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mid.R;
import com.example.mid.adapter.NewsAdapter;
import com.example.mid.databinding.ActivityMainBinding;
import com.example.mid.model.News;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    NewsAdapter newsAdapter;
    ArrayList<News> news;

    private OkHttpClient mClient; //handel netwok request and response
    private String Strurl="https://alasartothepoint.alasartechnologies.com/listItem.php?id=1 "; //Get Request

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView =findViewById(R.id.newsList);
        initNews();
    }

//    void initNews(){
//        news = new ArrayList<>();
//
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1501));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1502));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1503));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1504));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1505));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1506));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1507));
//        news.add(new News("Nike Shoes","https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/60f05e0f-25a1-46c8-8a05-8a13ba8f5e05/sb-shane-skate-shoes-mQcf7z.png","Available",1508));
//
//        newsAdapter = new NewsAdapter(this,news);
//
//        //use if layout is Grid
//
//        GridLayoutManager layoutManager = new GridLayoutManager(this,1); // 2 mean k 2 coloum may show krwana ha
//        binding.newsList.setLayoutManager(layoutManager);
//        binding.newsList.setAdapter(newsAdapter);
//
//    }



    void initNews(){
        news = new ArrayList<>();
        mClient=new OkHttpClient();
        NewsAdapter newsadapter;
        final String[] myResponse = new String[1];
//        ListView lv;

        OkHttpClient client = new OkHttpClient();
        String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1 ";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    myResponse[0] = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject reader = new JSONObject(myResponse[0]);
                                JSONArray superheros = reader.getJSONArray("data"); // get the whole json array list
                                System.out.println("json size is : "+superheros.length());
                                for(int i = 0;i<superheros.length();i++)
                                {
                                    JSONObject hero = superheros.getJSONObject(i);
                                    String url = hero.getString("url");
                                    String description = hero.getString("description");
                                    String heading = hero.getString("heading");
                                    String id = hero.getString("id");
                                    String refr = hero.getString("reference");

                                    news.add(new News( heading,url,description,Integer.parseInt(id),refr));
                                    System.out.println("Data Added In News Array \n =>" + heading + ""+ description);
                                    System.out.println("Count =>"+ i +"\n Data Added In News Array \n =>" + news.get(i));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });
        newsAdapter = new NewsAdapter(this,news);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1); // 2 mean k 2 coloum may show krwana ha
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        binding.newsList.setLayoutManager(layoutManager);
        binding.newsList.setAdapter(newsAdapter);

    }

    //End of class
}
