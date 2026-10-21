package com.mymonkmindset.sanatanam;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.MultiBrowseCarouselStrategy;
import com.mymonkmindset.sanatanam.adapters.CarouselAdapter;
import com.mymonkmindset.sanatanam.adapters.VerseAdapter;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;
import com.mymonkmindset.sanatanam.models.FirstCarouselCard;
import com.mymonkmindset.sanatanam.models.Verse;
import com.mymonkmindset.sanatanam.utils.AssetUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectItemInterface {
    private RecyclerView carousel;
    private List<FirstCarouselCard> list;
    private ShimmerFrameLayout shimmerFrameLayout;
    private Button geetaBtn, settingIconBtn;

    private List<Verse> verseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createCarousel();
        createQuotes();
        gatherResources();
        setOnClickListeners();
//        ShimmerFrameLayout container =
//                (ShimmerFrameLayout) findViewById(R.id.shimmer);
//        container.startShimmer();
    }

    public void gatherResources(){
        geetaBtn = findViewById(R.id.btnGeeta);
        settingIconBtn = findViewById(R.id.settingIconBtn);
    }

    @Override
    public void setOnClickListeners() {
        settingIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        geetaBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GeetaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createQuotes(){
        RecyclerView rvQuotes = findViewById(R.id.rvQuotes);
        if (rvQuotes == null) return;
        rvQuotes.setLayoutManager(new LinearLayoutManager(this));
        rvQuotes.setNestedScrollingEnabled(false);

        verseList = AssetUtils.loadVersesFromAssets(this, "chapter_1.json");
        if (verseList == null || verseList.isEmpty()) {
            verseList = new ArrayList<>();
            verseList.add(Verse.builder()
                    .reference("अध्याय 6, श्लोक 5")
                    .lines(List.of("उद्धरेदात्मनात्मानं नात्मानमवसादयेत्।", "आत्मैव ह्यात्मनो बन्धुरात्मैव रिपुरात्मनः॥"))
                    .translation("अपने द्वारा अपना उद्धार करे, अपना पतन न करे। क्योंकि आप ही अपने मित्र हैं और आप ही अपने शत्रु हैं।")
                    .title("आत्म उद्धार")
                    .build());
            verseList.add(Verse.builder()
                    .reference("अध्याय 2, श्लोक 47")
                    .lines(List.of("कर्मण्येवाधिकारस्ते मा फलेषु कदाचन।", "मा कर्मफलहेतुर्भूर्मा ते सङ्गोऽस्त्वकर्मणि॥"))
                    .translation("तुम्हारा अधिकार केवल कर्म करने पर है, उसके फलों पर कभी नहीं।")
                    .title("कर्म योग")
                    .build());
            verseList.add(Verse.builder()
                    .reference("मुण्डक उपनिषद् 3.1.6")
                    .lines(List.of("सत्यमेव जयते नानृतम्॥"))
                    .translation("सत्य की ही विजय होती है, असत्य की नहीं।")
                    .title("सत्यमेव जयते")
                    .build());
        }
        rvQuotes.setAdapter(new VerseAdapter(verseList));
    }

    private void createCarousel(){
        RecyclerView rvCarousel = findViewById(R.id.rvCarousel);

        // 1. Setup Material Carousel LayoutManager
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(new MultiBrowseCarouselStrategy());
        rvCarousel.setLayoutManager(layoutManager);

        // 2. Attach snap helper for smooth centering/snapping
        CarouselSnapHelper snapHelper = new CarouselSnapHelper();
        snapHelper.attachToRecyclerView(rvCarousel);

        // 3. Prepare data using your Lombok Builder
        List<FirstCarouselCard> cardList = new ArrayList<>();
        cardList.add(FirstCarouselCard.builder()
                .text("Morning Mindfulness & Stillness")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Universal Truth & Dharma")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Evening Reflection & Intention")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Evening Reflection & Intention")
                .imageUrl("https://picsum.photos/600/400?3")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Evening Reflection & Intention")
                .imageUrl("https://picsum.photos/600/400?3")
                .build());

        // 4. Attach adapter
        CarouselAdapter adapter = new CarouselAdapter(cardList);
        rvCarousel.setAdapter(adapter);
    }
}