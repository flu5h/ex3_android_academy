package e.mipro.business_card;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import e.mipro.business_card.DTO.NewsDTO;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public List<NewsDTO> data;
    private LayoutInflater inflater;
    private final Context context;
    private final RequestManager imageLoader;

    public CustomAdapter(Context context, List<NewsDTO> data){
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);
        final RequestOptions imageOption = new RequestOptions()
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);

    }

    public NewsDTO getItem(int i) {
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
        holder.progressBar.setVisibility(View.VISIBLE);
        NewsDTO news=data.get(i);
        holder.titleView.setText(news.getTitle());
        //holder.textView.setText(news.getAbstract1().substring(0,110).replaceAll("\n\n","\n")+"...");
        holder.textView.setText(news.getAbstract1());
        holder.dateView.setText(news.getPublishedDate().toString());
        if (!news.getMultimedia().isEmpty())
        imageLoader.load(news.getImg())
        .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                }, 333);

                return false;
            }
        })
                .into(holder.imageView);
        else
            holder.progressBar.setVisibility(View.GONE);
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
        public final ProgressBar progressBar;

        public  ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            titleView=itemView.findViewById(R.id.news_title);
            textView=itemView.findViewById(R.id.news_text);
            dateView=itemView.findViewById(R.id.news_date);
            cardView=itemView.findViewById(R.id.card);
            progressBar=itemView.findViewById(R.id.progressBar);

            cardView.setOnClickListener(v -> {
                int itemPosition = getAdapterPosition();
                NewsDTO item = data.get(itemPosition);
                Intent i = new Intent(context, ShowSingleNewsActivity.class);
                i.putExtra("news_title", item.getTitle().toString());
                i.putExtra("news_text", item.getAbstract1().toString());
                i.putExtra("news_date", item.getPublishedDate().toString());
                context.startActivity(i);

            });

        }
    }

}