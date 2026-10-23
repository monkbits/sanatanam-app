package com.mymonkmindset.sanatanam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.MultiBrowseCarouselStrategy;
import com.google.android.material.navigation.NavigationBarView;
import com.mymonkmindset.sanatanam.adapters.CarouselAdapter;
import com.mymonkmindset.sanatanam.adapters.VerseAdapter;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;
import com.mymonkmindset.sanatanam.models.FirstCarouselCard;
import com.mymonkmindset.sanatanam.models.Scripture;
import com.mymonkmindset.sanatanam.models.ScriptureType;
import com.mymonkmindset.sanatanam.models.Verse;
import com.mymonkmindset.sanatanam.utils.AssetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends BottomMenuActivity implements SelectItemInterface {
    private RecyclerView carousel;
    private List<FirstCarouselCard> list;
    private ShimmerFrameLayout shimmerFrameLayout;
    private Button geetaBtn;
    private List<Verse> verseList;
    private MaterialCardView cardReadGeeta;

    public static List<Scripture> vedas, maha_puranas, upanishads, otherBooks;

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
        initBottomNav();
        setDefaultData();
        createQuotes();
        gatherResources();
        setOnClickListeners();
    }

    private void setDefaultData(){
        vedas = Arrays.asList(
                Scripture.builder().category(String.valueOf(ScriptureType.VEDA)).id(1L).title("Rigveda").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.VEDA)).id(2L).title("Samaveda").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.VEDA)).id(3L).title("Yajurveda").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.VEDA)).id(4L).title("Atharvaveda").build()
        );

        upanishads = Arrays.asList(
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(1L).title("Isha Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(2L).title("Kena Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(3L).title("Katha Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(4L).title("Prashna Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(5L).title("Mundaka Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(6L).title("Mandukya Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(7L).title("Taittiriya Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(8L).title("Aitareya Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(9L).title("Chandogya Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(10L).title("Brihadaranyaka Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(11L).title("Shvetashvatara Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(12L).title("Kaushitaki Upanishad").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.UPANISHAD)).id(13L).title("Maitri Upanishad").build()
        );


        maha_puranas = Arrays.asList(
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(1L).title("Brahma Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(2L).title("Padma Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(3L).title("Vishnu Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(4L).title("Shiva Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(5L).title("Srimad Bhagavatam").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(6L).title("Narada Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(7L).title("Markandeya Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(8L).title("Agni Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(9L).title("Bhavishya Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(10L).title("Brahma Vaivarta Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(11L).title("Linga Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(12L).title("Varaha Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(13L).title("Skanda Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(14L).title("Vamana Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(15L).title("Kurma Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(16L).title("Matsya Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(17L).title("Garuda Purana").build(),
                Scripture.builder().category(String.valueOf(ScriptureType.MAHA_PURANA)).id(18L).title("Brahmanda Purana").build()
        );


        otherBooks = Arrays.asList(
                Scripture.builder().category(String.valueOf(ScriptureType.VEDA)).id(5L).title("Geeta").build()
                );
    }

    public void gatherResources(){
        cardReadGeeta = findViewById(R.id.cardReadGeeta);
    }

    @Override
    public void setOnClickListeners() {

        if (cardReadGeeta != null) {
            cardReadGeeta.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, GeetaActivity.class);
                startActivity(intent);
            });
        }
    }

    private void createQuotes(){
        RecyclerView rvQuotes = findViewById(R.id.rvQuotes);
        if (rvQuotes == null) return;
        rvQuotes.setLayoutManager(new LinearLayoutManager(this));
        rvQuotes.setNestedScrollingEnabled(false);

        Executors.newSingleThreadExecutor().execute(() -> {
            List<Verse> loadedVerses = AssetUtils.loadVersesFromAssets(this, "chapter_1.json");
            if (loadedVerses == null || loadedVerses.isEmpty()) {
                loadedVerses = new ArrayList<>();
                loadedVerses.add(Verse.builder()
                        .reference("अध्याय 6, श्लोक 5")
                        .lines(Arrays.asList("उद्धरेदात्मनात्मानं नात्मानमवसादयेत्।", "आत्मैव ह्यात्मनो बन्धुरात्मैव रिपुरात्मनः॥"))
                        .translation("अपने द्वारा अपना उद्धार करे, अपना पतन न करे। क्योंकि आप ही अपने मित्र हैं और आप ही अपने शत्रु हैं।")
                        .title("आत्म उद्धार")
                        .build());
                loadedVerses.add(Verse.builder()
                        .reference("अध्याय 2, श्लोक 47")
                        .lines(Arrays.asList("कर्मण्येवाधिकारस्ते मा फलेषु कदाचन।", "मा कर्मफलहेतुर्भूर्मा ते सङ्गोऽस्त्वकर्मणि॥"))
                        .translation("तुम्हारा अधिकार केवल कर्म करने पर है, उसके फलों पर कभी नहीं।")
                        .title("कर्म योग")
                        .build());
                loadedVerses.add(Verse.builder()
                        .reference("मुण्डक उपनिषद् 3.1.6")
                        .lines(Arrays.asList("सत्यमेव जयते नानृतम्॥"))
                        .translation("सत्य की ही विजय होती है, असत्य की नहीं।")
                        .title("सत्यमेव जयते")
                        .build());
            }
            verseList = loadedVerses;
            runOnUiThread(() -> rvQuotes.setAdapter(new VerseAdapter(verseList)));
        });
    }

    private void createCarousel(){
        RecyclerView rvCarousel = findViewById(R.id.rvCarousel);
        if (rvCarousel == null) return;

        // 1. Setup Material Carousel LayoutManager
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(new MultiBrowseCarouselStrategy());
        rvCarousel.setLayoutManager(layoutManager);

        // 2. Attach snap helper for smooth centering/snapping
        CarouselSnapHelper snapHelper = new CarouselSnapHelper();
        snapHelper.attachToRecyclerView(rvCarousel);

        // 3. Prepare data highlighting features and significance of Bhagavad Gita
        List<FirstCarouselCard> cardList = new ArrayList<>();
        cardList.add(FirstCarouselCard.builder()
                .text("Karma Yoga: Master Duty Without Attachment")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Jnana Yoga: Eternal Wisdom & Self-Realization")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Bhakti Yoga: Path of Devotion & Inner Peace")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("18 Chapters & 700 Sacred Sanskrit Verses")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Overcome Fear & Doubt: Practical Wisdom for Life")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        cardList.add(FirstCarouselCard.builder()
                .text("Read Offline in Hindi, English & Hinglish")
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS98KfWtAZwV0C4NjaMQ__eUDTUpNnLHci1rJHH-kGFMA&s=10")
                .build());

        // 4. Attach adapter
        CarouselAdapter adapter = new CarouselAdapter(cardList);
        rvCarousel.setAdapter(adapter);
    }
}
