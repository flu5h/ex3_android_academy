package e.mipro.business_card;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import e.mipro.business_card.data.DataUtils;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.news_list);
        showProgress(true);
        mAdapter = new CustomAdapter(getBaseContext(), DataUtils.generateNews());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(mAdapter);
        if (getApplicationContext().getResources().getBoolean(R.bool.island))
            recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        Log.d("MYLOGS", Thread.currentThread().getName() + "  main onCreate");

        h.sendEmptyMessageDelayed(777, 2000);

    }

    Handler h = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 777) showProgress(false);
        }
    };

    public void showProgress(boolean shouldShow) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        if (shouldShow) {
            progressBar.setVisibility(View.VISIBLE);

        } else {
            progressBar.setVisibility(View.GONE);
        }
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


}
