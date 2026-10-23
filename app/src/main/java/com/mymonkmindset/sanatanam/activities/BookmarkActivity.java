package com.mymonkmindset.sanatanam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationBarView;
import com.mymonkmindset.sanatanam.BottomMenuActivity;
import com.mymonkmindset.sanatanam.MainActivity;
import com.mymonkmindset.sanatanam.R;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;

public class BookmarkActivity extends BottomMenuActivity implements SelectItemInterface {
    private NavigationBarView navigationBarView;
    private TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bookmark);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initBottomNav();
        gatherResources();
        pageTitle.setText("Bookmarks");
        setOnClickListeners();
    }

    @Override
    public void gatherResources() {
        pageTitle = findViewById(R.id.pageTitle);
//        navigationBarView = findViewById(R.id.bottomNavigation);
    }

    @Override
    public void setOnClickListeners() {
//        navigationBarView.setOnItemSelectedListener(item -> {
//            if(item.getItemId() == R.id.item_1){
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                return true;
//            } else if (item.getItemId() == R.id.item_2) {
//                Intent intent = new Intent(this, BookmarkActivity.class);
//                startActivity(intent);
//                return true;
//            }
//            return false;
//        });
    }
}