package com.mymonkmindset.sanatanam.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mymonkmindset.sanatanam.models.Verse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class AssetUtils {
    public static List<Verse> loadVersesFromAssets(Context context, String fileName) {
        String path = fileName.startsWith("chapters/") ? fileName : "chapters/" + fileName;
        try (InputStream is = context.getAssets().open(path);
             Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {

            Type listType = new TypeToken<List<Verse>>() {}.getType();
            List<Verse> verses = new Gson().fromJson(reader, listType);
            return verses != null ? verses : Collections.emptyList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
