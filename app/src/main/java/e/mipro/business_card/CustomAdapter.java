package e.mipro.business_card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private List<NewsItem> data = new ArrayList<>();
    private LayoutInflater inflater;
    private final Context context;
    public CustomAdapter(Context context, List<NewsItem> data){
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);

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
        holder.textView.setText(news.getFullText().substring(0,150).replaceAll("\n\n","\n")+"...");
        holder.dateView.setText(news.getPublishDate().toString());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView titleView;
        public final TextView textView;
        public final TextView dateView;
        public final ImageView imgView;

        public  ViewHolder(View itemView) {
            super(itemView);
            titleView=itemView.findViewById(R.id.news_title);
            textView=itemView.findViewById(R.id.news_text);
            dateView=itemView.findViewById(R.id.news_date);
            imgView=itemView.findViewById(R.id.imageView);

        }
    }


}