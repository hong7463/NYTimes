package com.haisenhong.nytimes.ui.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haisenhong.nytimes.R;
import com.haisenhong.nytimes.data.responses.Doc;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hison7463 on 10/20/16.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Doc> list;
    private Context context;

    public ArticleAdapter(List<Doc> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {

        holder.title.setText(list.get(holder.getAdapterPosition()).getHeadline().getMain());
        if(list.get(position).getMultipleMedia() != null && list.get(position).getMultipleMedia().length > 0) {
            Glide.with(context).load(context.getString(R.string.nytimes_url) + list.get(position).getMultipleMedia()[0].getUrl()).centerCrop().into(holder.image);
        }
        else {
            holder.image.setImageResource(R.drawable.new_york_times_logo);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                PendingIntent pendingIntent = getPendingIntent(list.get(position).getWeb_url());
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_share);
                builder.setActionButton(bitmap, "Share", pendingIntent);
                CustomTabsIntent intent = builder.build();
                intent.launchUrl((Activity) context, Uri.parse(list.get(position).getWeb_url()));
            }
        });
    }

    private PendingIntent getPendingIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        return PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        ImageView image;
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_view)
        LinearLayout view;

        public ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
