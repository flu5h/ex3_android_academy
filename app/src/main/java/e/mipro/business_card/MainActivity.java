package e.mipro.business_card;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import e.mipro.business_card.DTO.NewsDTO;
import e.mipro.business_card.DTO.NewsResponse;
import e.mipro.business_card.NET.Network;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

;


public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public CustomAdapter mAdapter;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String[] categories ={"home", "world", "opinion", "national", "politics", "upshot", "nyregion", "business", "technology", "science", "health", "sports", "arts", "books", "movies",
            "theater", "sundayreview", "fashion", "tmagazine", "food", "travel", "magazine", "realestate", "automobiles", "obituaries", "insider"};
    private static final String TAG = "MYLOGS";
    private static final String API_KEY = "6a561a096d2044e18e5a6fa108606185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
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

    void setupUI() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (getApplicationContext().getResources().getBoolean(R.bool.island))
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(this ,
                android.R.layout.simple_spinner_item ,
                categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadNews((String)spinner.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    private void loadNews(@NonNull String search) {

        final Disposable searchDisposable = Network.getInstance()
                .news()
                .search(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkResponseAndShowState, this::handleError);
        compositeDisposable.add(searchDisposable);
    }

    private void handleError(Throwable throwable) {
        if (throwable instanceof IOException) {
            Log.d(TAG,"Loading err");
            return;
        }

    }


    private void checkResponseAndShowState(@NonNull NewsResponse response) {
        final List<NewsDTO> data = response.getData();

        mAdapter = new CustomAdapter(getApplicationContext(), data);
        recyclerView.setAdapter(mAdapter);

    }

}
