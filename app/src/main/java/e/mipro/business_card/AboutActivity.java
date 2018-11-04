package e.mipro.business_card;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.email).setOnClickListener(v -> {
            Toast.makeText(this,"Sending...",Toast.LENGTH_LONG).show();
            TextView textView=findViewById(R.id.editText);
            Intent intent=new Intent (Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"sabziro1995@mail.ru"});
            intent.putExtra(Intent.EXTRA_SUBJECT,"");
            intent.putExtra(Intent.EXTRA_TEXT,textView.getText().toString());
            startActivity(intent);});

        findViewById(R.id.imageView5).setOnClickListener(v -> {
            Intent intent=new Intent (Intent.ACTION_VIEW,Uri.parse("https://t.me/flu5h"));
            startActivity(intent);});

        findViewById(R.id.imageView4).setOnClickListener(v -> {
            Intent intent=new Intent (Intent.ACTION_VIEW,Uri.parse("https://vk.com/flu5h"));
            startActivity(intent);});

        findViewById(R.id.imageView3).setOnClickListener(v -> {
            Toast.makeText(this,"Call...",Toast.LENGTH_LONG).show();
            Intent intent=new Intent (Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:89636543324"));
            startActivity(intent);});

    }

}
