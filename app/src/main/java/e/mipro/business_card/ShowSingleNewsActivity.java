package e.mipro.business_card;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSingleNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.news_show);
//        TextView title = (TextView)findViewById(R.id.textView5);
//        TextView text = (TextView)findViewById(R.id.textView4);
//        TextView date = (TextView)findViewById(R.id.textView6);
        WebView web = (WebView) findViewById(R.id.web);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
//        title.setText(getIntent().getStringExtra("news_title"));
//        text.setText(getIntent().getStringExtra("news_text"));
//        date.setText(getIntent().getStringExtra("news_date"));
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        web.loadUrl(getIntent().getStringExtra("news_url"));

    }
}
