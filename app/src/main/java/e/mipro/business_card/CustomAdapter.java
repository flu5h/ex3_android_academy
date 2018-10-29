package e.mipro.business_card;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import e.mipro.business_card.data.NewsItem;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public List<NewsItem> data;
    private LayoutInflater inflater;
    private final Context context;
    private final RequestManager imageLoader;

    public CustomAdapter(Context context, List<NewsItem> data){
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);
        final RequestOptions imageOption = new RequestOptions()
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);

    }

    public NewsItem getItem(int i) {
        return data.get(i);
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        return new ViewHolder(
                inflater.inflate(R.layout.news_line,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        NewsItem news=data.get(i);
        holder.titleView.setText(news.getTitle());
        holder.textView.setText(news.getFullText().substring(0,110).replaceAll("\n\n","\n")+"...");
        holder.dateView.setText(news.getPublishDate().toString());
        imageLoader.load(news.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView imageView;
        public final TextView titleView;
        public final TextView textView;
        public final TextView dateView;
        public final CardView cardView;


        public  ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);;
            titleView=itemView.findViewById(R.id.news_title);
            textView=itemView.findViewById(R.id.news_text);
            dateView=itemView.findViewById(R.id.news_date);
            cardView=itemView.findViewById(R.id.card);

            cardView.setOnClickListener(v -> {
                int itemPosition = getAdapterPosition();
                NewsItem item = data.get(itemPosition);
                Intent i = new Intent(context, ShowSingleNewsActivity.class);
                i.putExtra("news_title", item.getTitle().toString());
                i.putExtra("news_text", item.getFullText().toString());
                i.putExtra("news_date", item.getPublishDate().toString());
                context.startActivity(i);
            });

        }
    }

}