package com.example.mid.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mid.R;
import com.example.mid.databinding.NewsListBinding;
import com.example.mid.model.News;

import java.util.ArrayList;

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


        Context context;
        ArrayList<News> newslist;

    public NewsAdapter(Context context, ArrayList<News> news) {
            this.context = context;
            this.newslist = news;
        }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_list,parent,false));

    }
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newslist.get(position);

        Glide.with(context).load(news.getUrl()).into(holder.binding.image);
        holder.binding.heading.setText(news.getHeading());
        holder.binding.description.setText(news.getDescription());

        //implicitily share link
        holder.binding.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, news.getRef());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "News Link");
                context.startActivity(shareIntent);
            }
        });


        holder.binding.morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=news.getRef().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }



    public class NewsViewHolder extends RecyclerView.ViewHolder{

        NewsListBinding binding;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            //we use bind in Adopter because inflate is not available in Adopter inflate is available in mann Activity
            binding= NewsListBinding.bind(itemView);
        }
    }
}

