package com.mymonkmindset.sanatanam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mymonkmindset.sanatanam.adapters.GeetaChapterAdapter;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;
import com.mymonkmindset.sanatanam.models.Chapter;
import com.mymonkmindset.sanatanam.models.Verse;
import com.mymonkmindset.sanatanam.utils.AssetUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeetaActivity extends AppCompatActivity implements SelectItemInterface {

    public static int readingChapter = 0;
    private RecyclerView rvGeetaChapters;
    public static List<Chapter> chapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_geeta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gatherResources();
        loadChapters();
        setOnClickListeners();
    }


    private void loadChapters() {
        RecyclerView rvGeetaChapters = findViewById(R.id.rvGeetaChapters);
        if (rvGeetaChapters == null) return;

        rvGeetaChapters.setLayoutManager(new LinearLayoutManager(this));
        chapters = new ArrayList<>();

        String scriptureName = "श्रीमद्भगवद्गीता";

        List<String> chapterTitles = List.of(
                "अर्जुनविषाद योग",
                "सांख्य योग",
                "कर्म योग",
                "ज्ञानकर्मसंन्यास योग",
                "कर्मसंन्यास योग",
                "आत्मसंयम योग",
                "ज्ञानविज्ञान योग",
                "अक्षरब्रह्म योग",
                "राजविद्याराजगुह्य योग",
                "विभूति योग",
                "विश्वरूपदर्शन योग",
                "भक्ति योग",
                "क्षेत्र-क्षेत्रज्ञविभाग योग",
                "गुणत्रयविभाग योग",
                "पुरुषोत्तम योग",
                "दैवासुरसम्पद्विभाग योग",
                "श्रद्धात्रयविभाग योग",
                "मोक्षसंन्यास योग"
        );

        // Build the 18 chapters cleanly
        for (int i = 0; i < chapterTitles.size(); i++) {
            chapters.add(Chapter.builder()
                    .chapterNumber(String.valueOf(i + 1))
                    .chapterTitle(chapterTitles.get(i))
                    .scriptureTitle(scriptureName)
                    .build());
        }

        GeetaChapterAdapter adapter = new GeetaChapterAdapter(chapters);
        rvGeetaChapters.setAdapter(adapter);

        adapter.setOnItemClickListener((chapter, position) -> {
            Intent intent = new Intent(GeetaActivity.this, ReadVersesActivity.class);
            // Pass 1-based chapter number (1 to 18)
            intent.putExtra("EXTRA_CHAPTER_NUMBER", position + 1);
            intent.putExtra("EXTRA_CHAPTER_TITLE", chapter.getChapterTitle());
            startActivity(intent);
        });
    }

    @Override
    public void gatherResources() {
        rvGeetaChapters = findViewById(R.id.rvGeetaChapters);
    }

    @Override
    public void setOnClickListeners() {

    }

    public List<Verse> loadChapterFromAssets(Context context, int chapterNum) {
        return AssetUtils.loadVersesFromAssets(context, "chapter_" + chapterNum + ".json");
    }
}