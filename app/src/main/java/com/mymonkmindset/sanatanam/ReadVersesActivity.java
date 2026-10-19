package com.mymonkmindset.sanatanam;

import android.os.Bundle;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

public class ReadVersesActivity extends AppCompatActivity implements SelectItemInterface {
    private TextView pageTitle;
    private List<Verse> verseList;
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
        gatherResources();
        setOnClickListeners();

        pageTitle.setText(GeetaActivity.chapters.get(GeetaActivity.readingChapter).getChapterTitle());
        loadVerses();
    }

    @Override
    public void gatherResources() {
        pageTitle = findViewById(R.id.pageTitle);
        verseRecycleView = findViewById(R.id.rvReadVerses);
    }

    @Override
    public void setOnClickListeners() {

    }

    private void loadVerses(){
        verseList = new ArrayList<>();
        verseRecycleView.setLayoutManager(new LinearLayoutManager(this));
        List<Verse> vlist = GeetaActivity.chapters.get(GeetaActivity.readingChapter).getVerses();
        verseList.addAll(vlist);
//        pageTitle.setText(verseList.toString());

        VerseAdapter verseAdapter = new VerseAdapter(verseList);
        verseRecycleView.setAdapter(verseAdapter);
    }
}