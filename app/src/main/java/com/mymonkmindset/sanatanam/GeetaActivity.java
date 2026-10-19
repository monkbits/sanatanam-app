package com.mymonkmindset.sanatanam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mymonkmindset.sanatanam.adapters.GeetaChapterAdapter;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;
import com.mymonkmindset.sanatanam.models.Chapter;
import com.mymonkmindset.sanatanam.models.Verse;

import java.util.ArrayList;
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
        gatherResources();
        loadChapters();

        setOnClickListeners();


    }

    private void loadChapters() {
        RecyclerView rvGeetaChapters = findViewById(R.id.rvGeetaChapters);
        if (rvGeetaChapters == null) return;

        chapters = new ArrayList<>();
        rvGeetaChapters.setLayoutManager(new LinearLayoutManager(this));
        String scriptureName = "bhagwat geeta";
        int[] count = {1};
        chapters.addAll(List.of(
//           Chapter.builder().chapterTitle("अर्जुनविषाद योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("अर्जुनविषाद योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("सांख्य योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("कर्म योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("ज्ञानकर्मसंन्यास योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("कर्मसंन्यास योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("आत्मसंयम योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("ज्ञानविज्ञान योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("अक्षरब्रह्म योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("राजविद्याराजगुह्य योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("विभूति योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("विश्वरूपदर्शन योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("भक्ति योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("क्षेत्र-क्षेत्रज्ञविभाग योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("गुणत्रयविभाग योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("पुरुषोत्तम योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("दैवासुरसम्पद्विभाग योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("श्रद्धात्रयविभाग योग").build(),
           Chapter.builder()
                   .chapterNumber(String.valueOf(count[0]++))
                   .verses(List.of())
                   .scriptureTitle(scriptureName)
                   .chapterTitle("मोक्षसंन्यास योग").build()

        ));

        for (int i = 0; i < chapters.size(); i++) {
            List<Verse> versesAll = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                versesAll.add(Verse.builder()
                        .title("verse Title")
                        .lines(List.of(""))
                        .translation("translation in hindi")
                        .reference("some reference")
                        .scriptureTitle("sculpture Title")
                        .build());
            }
            Chapter c = chapters.get(i);
            c.setVerses(versesAll);
            chapters.set(i, c);
        }
        GeetaChapterAdapter adapter = new GeetaChapterAdapter(chapters);
        rvGeetaChapters.setAdapter(adapter);

        // ✅ Adapter इत्यस्य माध्यमेन Listener योज्यताम्, न तु rvGeetaChapters इत्यस्य उपरि:
        adapter.setOnItemClickListener((chapter, position) -> {
            GeetaActivity.readingChapter = position;
//            Toast.makeText(this, "चितः अध्यायः: " + chapter.getChapterTitle(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(GeetaActivity.this, ReadVersesActivity.class);
            startActivity(intent);
            // नूतन-Activity उद्घाटनाय:
            // Intent intent = new Intent(this, ChapterDetailActivity.class);
            // intent.putExtra("CHAPTER_NUM", geetaChapter.getChapterNumber());
            // startActivity(intent);
        });
    }

    @Override
    public void gatherResources() {
        rvGeetaChapters = findViewById(R.id.rvGeetaChapters);
    }

    @Override
    public void setOnClickListeners() {

    }
}