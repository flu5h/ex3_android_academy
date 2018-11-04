package e.mipro.business_card;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

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
import e.mipro.business_card.data.DataUtils;
import e.mipro.business_card.data.NewsItem;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public CustomAdapter mAdapter ;
     public List<NewsItem> newsList=DataUtils.generateNews();

    public List<NewsDTO> news;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String TAG = "MYLOGS";
    private static final String API_KEY = "6a561a096d2044e18e5a6fa108606185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);
        loadNews("science");
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

            return;
        }

    }


    private void checkResponseAndShowState(@NonNull NewsResponse response) {
        //Here I use Guard Clauses. You can find more here:
        //https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html

        //Here we have 4 clauses:


        final List<NewsDTO> data = response.getData();

        mAdapter= new CustomAdapter(getApplicationContext(),data );
        recyclerView.setAdapter(mAdapter);

    }

}
