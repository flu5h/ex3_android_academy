package e.mipro.business_card;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import e.mipro.business_card.DTO.NewsDTO;
import e.mipro.business_card.DTO.NewsResponse;
import e.mipro.business_card.data.DataUtils;
import e.mipro.business_card.data.NewsItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public CustomAdapter mAdapter ;
     public List<NewsItem> newsList=DataUtils.generateNews();
     boolean f;
    @Nullable
    private  Call searchRequest;
    public List<NewsDTO> news;


    private static final String TAG = "MYLOGS";
    private static final String API_KEY = "6a561a096d2044e18e5a6fa108606185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);
        loadNews("");
        setupUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        MenuItem item = menu.findItem(R.id.info);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Intent i = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setupUI(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        if (getApplicationContext().getResources().getBoolean(R.bool.island))
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadNews(@NonNull String search) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url("http://api.nytimes.com/svc/topstories/v2/" + "science" + ".json?api-key=" + API_KEY)
                .build();

        Call call = client.newCall(request);
        Log.d(TAG, "try request");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "request fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "request complete");
                Gson gson = new Gson();
                String gsonResponse = response.body().string();
                //Log.d(TAG, gsonResponse);
                NewsResponse newsResponse = gson.fromJson(gsonResponse, NewsResponse.class);
                news = newsResponse.getData();
                Log.d(TAG, Thread.currentThread().getName());
                Log.d(TAG, news.get(1).getTitle());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter= new CustomAdapter(getApplicationContext(),news );
                        recyclerView.setAdapter(mAdapter);
                    }
                });

            }
        });
    }



}
