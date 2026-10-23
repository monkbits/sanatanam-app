package com.mymonkmindset.sanatanam;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mymonkmindset.sanatanam.adapters.VerseAdapter;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;
import com.mymonkmindset.sanatanam.models.Verse;
import com.mymonkmindset.sanatanam.utils.AssetUtils;

import java.util.List;

public class ReadVersesActivity extends BottomMenuActivity implements SelectItemInterface {

    private TextView pageTitle;
    private RecyclerView verseRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_verses);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initPrefs(this);
        initBottomNav();
        gatherResources();
        setOnClickListeners();

        // 1. Get Intent extras with fallback defaults
        int chapterNum = getIntent().getIntExtra("EXTRA_CHAPTER_NUMBER", 1);
        String chapterTitle = getIntent().getStringExtra("EXTRA_CHAPTER_TITLE");

        if (chapterTitle != null && pageTitle != null) {
            pageTitle.setText(chapterTitle);
        }

        // 2. Load the corresponding chapter JSON from assets
        String lang = getPref("translation_language");
        if (lang == null || lang.trim().isEmpty()) lang = "en";
        String path = "geeta/" + lang + "/chapter_" + chapterNum + ".json";
        List<Verse> verses = AssetUtils.loadVersesFromPath(this, path);

        // 3. Bind to RecyclerView once
        verseRecycleView.setLayoutManager(new LinearLayoutManager(this));
        verseRecycleView.setAdapter(new VerseAdapter(verses));
    }

    @Override
    public void gatherResources() {
        pageTitle = findViewById(R.id.pageTitle);
        verseRecycleView = findViewById(R.id.rvReadVerses);
    }

    @Override
    public void setOnClickListeners() {
        // Wire back button or header navigation here if present in cm_page_title / top bar
//        android.view.View btnBack = findViewById(R.id.btnBack);
//        if (btnBack != null) {
//            btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
//        }
    }
}