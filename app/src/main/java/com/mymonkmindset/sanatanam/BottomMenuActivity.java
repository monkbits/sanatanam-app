package com.mymonkmindset.sanatanam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import com.mymonkmindset.sanatanam.activities.BookmarkActivity;

public class BottomMenuActivity extends AppCompatActivity {
    private ImageView home_btn, bookmark_btn, account_btn;

    private static final String pref_name = "sanatanam_prefs";
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initPrefs(Context context) {
        this.pref = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
    }

    protected void setPref(String key, String value) {
        if (pref != null) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    protected String getPref(String key) {
        if (pref == null) return "";
        return pref.getString(key, "");
    }

    protected void removePref(String key) {
        if (pref != null) {
            SharedPreferences.Editor editor = pref.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    protected void initBottomNav() {
        String curr_activity = getClass().getSimpleName();

        home_btn = findViewById(R.id.home_btn);
        bookmark_btn = findViewById(R.id.bookmark_btn);
        account_btn = findViewById(R.id.account_btn);

        setActiveNavItem(curr_activity);

        if (home_btn != null && !curr_activity.equals("MainActivity")) {
            home_btn.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            });
        }

        if (bookmark_btn != null && !curr_activity.equals("BookmarkActivity")) {
            bookmark_btn.setOnClickListener(view -> {
                Intent intent = new Intent(this, BookmarkActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            });
        }

        if (account_btn != null && !curr_activity.equals("SettingActivity")) {
            account_btn.setOnClickListener(view -> {
                Intent intent = new Intent(this, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            });
        }
    }

    private void setActiveNavItem(String curr_activity) {
        if (home_btn != null) {
            if (curr_activity.equals("MainActivity")) {
                Context context = home_btn.getContext();
                int color = ContextCompat.getColor(context, R.color.color_blue_dark);
                ImageViewCompat.setImageTintList(home_btn, ColorStateList.valueOf(color));
                home_btn.setImageResource(R.drawable.ic_home_filled);
            } else {
                ImageViewCompat.setImageTintList(home_btn, null);
                home_btn.setImageResource(R.drawable.ic_home);
            }
        }

        if (bookmark_btn != null) {
            if (curr_activity.equals("BookmarkActivity")) {
                Context context = bookmark_btn.getContext();
                int color = ContextCompat.getColor(context, R.color.color_blue_dark);
                ImageViewCompat.setImageTintList(bookmark_btn, ColorStateList.valueOf(color));
                bookmark_btn.setImageResource(R.drawable.ic_bookmark_filled);
            } else {
                ImageViewCompat.setImageTintList(bookmark_btn, null);
                bookmark_btn.setImageResource(R.drawable.ic_bookmark);
            }
        }

        if (account_btn != null) {
            if (curr_activity.equals("SettingActivity")) {
                Context context = account_btn.getContext();
                int color = ContextCompat.getColor(context, R.color.color_blue_dark);
                ImageViewCompat.setImageTintList(account_btn, ColorStateList.valueOf(color));
                account_btn.setImageResource(R.drawable.ic_account_filled);
            } else {
                ImageViewCompat.setImageTintList(account_btn, null);
                account_btn.setImageResource(R.drawable.ic_account);
            }
        }
    }
}
