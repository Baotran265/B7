package com.baotran.b7;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Article> articleList;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Nhận lại danh sách bài viết từ DetailActivity gửi về (nếu có)
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ARTICLE_LIST")) {
            articleList = intent.getParcelableArrayListExtra("ARTICLE_LIST");
        }

        // Nếu khởi chạy ứng dụng lần đầu tiên -> Tạo dữ liệu mẫu
        if (articleList == null) {
            articleList = new ArrayList<>();
            articleList.add(new Article("Hoa hướng dương",
                    "Bông hoa hướng dương vươn mình rạng rỡ giữa cánh đồng, thu trọn nguồn năng lượng ấm áp cùng sắc vàng rực rỡ của bầu trời lúc chiều tà",
                    R.drawable.anh1, 0));
            articleList.add(new Article("Hoa hồng",
                    "Những cành hồng đỏ đọng sương khẽ tựa lên nền vải trắng, bắt trọn vệt nắng ấm áp dịu dàng",
                    R.drawable.ic_launcher_background, 0));
            articleList.add(new Article("Hoa linh lan",
                    "Những đóa linh lan xanh dịu đọng trọn từng giọt sương long lanh như pha lê, tỏa ra nét đẹp tinh khôi và bình yên giữa nền đêm tĩnh lặng",
                    R.drawable.ic_launcher_background, 0));
        }

        // Khởi tạo Adapter theo đúng form gốc Mobile(5) của Thầy
        articleAdapter = new ArticleAdapter(this, articleList);
        recyclerView.setAdapter(articleAdapter);
    }
}