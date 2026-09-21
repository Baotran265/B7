package com.baotran.b7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetailCover;
    private TextView tvDetailTitle;
    private TextView tvDetailViews;
    private TextView tvDetailContent;
    private Button btnBack;

    private ArrayList<Article> articleList;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgDetailCover = findViewById(R.id.imgDetailCover);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailViews = findViewById(R.id.tvDetailViews);
        tvDetailContent = findViewById(R.id.tvDetailContent);
        btnBack = findViewById(R.id.btnBack);

        // Lấy danh sách ArrayList<Article> và vị trí từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            articleList = intent.getParcelableArrayListExtra("ARTICLE_LIST");
            selectedIndex = intent.getIntExtra("SELECTED_INDEX", -1);
        }

        // Hiển thị thông tin bài viết đã chọn
        if (articleList != null && selectedIndex >= 0 && selectedIndex < articleList.size()) {
            Article article = articleList.get(selectedIndex);
            tvDetailTitle.setText(article.getTitle());
            tvDetailViews.setText("Views: " + article.getViews());
            tvDetailContent.setText(article.getContent());
            imgDetailCover.setImageResource(article.getImg_cover());
        }

        // 1. Xử lý sự kiện bấm nút "Quay lại" trên giao diện
        btnBack.setOnClickListener(v -> navigateBackToMain());

        // 2. Xử lý phím Back/cử chỉ vuốt Back của hệ thống theo chuẩn AndroidX mới
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigateBackToMain();
            }
        });
    }

    // Tách logic chuyển về MainActivity thành hàm riêng
    private void navigateBackToMain() {
        Intent mainIntent = new Intent(DetailActivity.this, MainActivity.class);
        mainIntent.putParcelableArrayListExtra("ARTICLE_LIST", articleList);
        startActivity(mainIntent);
        finish();
    }
}