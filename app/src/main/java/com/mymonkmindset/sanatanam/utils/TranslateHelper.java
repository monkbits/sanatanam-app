package com.mymonkmindset.sanatanam.utils;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class TranslateHelper {
    public interface TranslationCallback {
        void onSuccess(String translatedText);
        void onFailure(Exception e);
    }

    private Translator translator;

    /**
     * Initializes a translator between two languages.
     * Example: TranslateLanguage.ENGLISH to TranslateLanguage.HINDI
     */
    public void setupTranslator(String sourceLang, String targetLang, Runnable onReady, TranslationCallback callback) {
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(sourceLang)
                .setTargetLanguage(targetLang)
                .build();

        translator = Translation.getClient(options);

        // Require Wi-Fi to save mobile data (or remove .requireWifi() to allow mobile data)
        DownloadConditions conditions = new DownloadConditions.Builder()
                .build();

        // Downloads ~30MB language model only once if not present on device
        translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(unused -> {
                    if (onReady != null) {
                        onReady.run();
                    }
                })
                .addOnFailureListener(callback::onFailure);
    }

    /**
     * Translates the given text asynchronously.
     */
    public void translateText(String text, TranslationCallback callback) {
        if (translator == null) {
            callback.onFailure(new IllegalStateException("Translator is not initialized."));
            return;
        }

        translator.translate(text)
                .addOnSuccessListener(callback::onSuccess)
                .addOnFailureListener(callback::onFailure);
    }

    /**
     * Clean up the translator instance to release resources.
     */
    public void close() {
        if (translator != null) {
            translator.close();
        }
    }
}
