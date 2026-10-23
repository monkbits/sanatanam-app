package com.mymonkmindset.sanatanam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.mymonkmindset.sanatanam.interfaces.SelectItemInterface;

public class SettingActivity extends BottomMenuActivity implements SelectItemInterface {

    private MaterialCardView cardPrivacyPolicy;
    private CheckBox cbEnglish, cbHindi, cbHinglish;
    private TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initPrefs(this);
        initBottomNav();
        gatherResources();
        pageTitle.setText("Settings");
        setOnClickListeners();
        keepCheckedTansLang(getPref("translation_language"));
    }

    @Override
    public void gatherResources() {
        pageTitle = findViewById(R.id.pageTitle);
        cbEnglish = findViewById(R.id.cbEngish);
        cbHindi = findViewById(R.id.cbHindi);
        cbHinglish = findViewById(R.id.Hinglish);
        cardPrivacyPolicy = findViewById(R.id.cardPrivacyPolicy);
    }

    private boolean isUpdatingCheck = false;

    @Override
    public void setOnClickListeners() {

        if (cbEnglish != null) {
            cbEnglish.setOnClickListener(v -> {
                if (cbEnglish.isChecked()) {
                    keepCheckedTansLang("en");
                    setPref("translation_language", "en");
                } else {
                    setPref("translation_language", "en");
                    cbEnglish.setChecked(true);
                }
            });
        }
        if (cbHindi != null) {
            cbHindi.setOnClickListener(v -> {
                if (cbHindi.isChecked()) {
                    keepCheckedTansLang("hi");
                    setPref("translation_language", "hi");
                } else {
                    setPref("translation_language", "hi");
                    cbHindi.setChecked(true);
                }
            });
        }
        if (cbHinglish != null) {
            cbHinglish.setOnClickListener(v -> {
                if (cbHinglish.isChecked()) {
                    keepCheckedTansLang("hi-en");
                    setPref("translation_language", "hi-en");
                } else {
                    setPref("translation_language", "hi-en");
                    cbHinglish.setChecked(true);
                }
            });
        }

        if (cardPrivacyPolicy != null) {
            cardPrivacyPolicy.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mymonkmindset.com/sanatanam/privacy-policy"));
                startActivity(intent);
            });
        }
    }

    private void keepCheckedTansLang(String lang){
        if (lang == null) lang = "";
        if (cbHindi != null) cbHindi.setChecked("hi".equals(lang));
        if (cbEnglish != null) cbEnglish.setChecked("en".equals(lang));
        if (cbHinglish != null) cbHinglish.setChecked("hi-en".equals(lang));
    }
}
