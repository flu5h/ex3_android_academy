package e.mipro.business_card;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSingleNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.news_show);
        TextView title = (TextView)findViewById(R.id.textView5);
        TextView text = (TextView)findViewById(R.id.textView4);
        TextView date = (TextView)findViewById(R.id.textView6);
        title.setText(getIntent().getStringExtra("news_title"));
        text.setText(getIntent().getStringExtra("news_text"));
        date.setText(getIntent().getStringExtra("news_date"));
    }
}
