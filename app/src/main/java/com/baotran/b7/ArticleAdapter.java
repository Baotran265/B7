package com.baotran.b7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private ArrayList<Article> articleList;
    private LayoutInflater mInflater; // Khai báo mInflater giống code mẫu của Thầy
    private Context context;

    // Constructor chuẩn theo form Mobile(5)
    public ArticleAdapter(Context context, ArrayList<Article> list) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context); // Khởi tạo mInflater
        this.articleList = list;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Dùng mInflater để nạp layout
        View view = mInflater.inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvContent.setText(article.getContent());
        holder.tvViews.setText("Views: " + article.getViews());
        holder.imgCover.setImageResource(article.getImg_cover());

        // Xử lý click và chuyển Activity trực tiếp trong Adapter giống code mẫu của Thầy
        holder.itemView.setOnClickListener(v -> {
            // 1. Tăng số lượt xem lên 1
            article.setViews(article.getViews() + 1);

            // 2. Tạo Intent và truyền dữ liệu
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putParcelableArrayListExtra("ARTICLE_LIST", articleList);
            intent.putExtra("SELECTED_INDEX", position);

            // 3. Chuyển màn hình
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articleList != null ? articleList.size() : 0;
    }
}