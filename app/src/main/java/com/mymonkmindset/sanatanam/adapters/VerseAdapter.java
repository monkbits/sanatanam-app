package com.mymonkmindset.sanatanam.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mymonkmindset.sanatanam.R;
import com.mymonkmindset.sanatanam.models.Verse;

import java.util.List;

public class VerseAdapter extends RecyclerView.Adapter<VerseAdapter.VerseViewHolder> {
    private final List<Verse> verseList;

    public VerseAdapter(List<Verse> verses) {
        this.verseList = verses;
    }


    @NonNull
    @Override
    public VerseAdapter.VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_verse, parent, false);
        return new VerseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerseAdapter.VerseViewHolder holder, int position) {
        Verse verse = verseList.get(position);

        if (verse.getTitle() != null && !verse.getTitle().isEmpty()) {
            holder.tvVerseTitle.setText(verse.getTitle());
            holder.tvVerseTitle.setVisibility(View.VISIBLE);
        } else {
            holder.tvVerseTitle.setVisibility(View.GONE);
        }

        if (verse.getReference() != null && !verse.getReference().isEmpty()) {
            holder.tvReference.setText(verse.getReference());
            holder.tvReference.setVisibility(View.VISIBLE);
        } else {
            holder.tvReference.setVisibility(View.GONE);
        }

        if (verse.getLines() != null && !verse.getLines().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < verse.getLines().size(); i++) {
                String line = verse.getLines().get(i);
                if (line != null) {
                    if (sb.length() > 0) sb.append("\n");
                    sb.append(line);
                }
            }
            holder.tvVerseLines.setText(sb.toString());
            holder.tvVerseLines.setVisibility(View.VISIBLE);
        } else {
            holder.tvVerseLines.setVisibility(View.GONE);
        }

        if (verse.getTranslation() != null && !verse.getTranslation().isEmpty()) {
            holder.tvTranslation.setText(verse.getTranslation());
            holder.tvTranslation.setVisibility(View.VISIBLE);
        } else {
            holder.tvTranslation.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return verseList != null ? verseList.size() : 0;
    }

    public static class VerseViewHolder extends RecyclerView.ViewHolder {
        TextView tvVerseTitle;
        TextView tvReference;
        TextView tvVerseLines;
        TextView tvTranslation;

        public VerseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVerseTitle = itemView.findViewById(R.id.tvVerseTitle);
            tvReference = itemView.findViewById(R.id.tvReference);
            tvVerseLines = itemView.findViewById(R.id.tvVerseLines);
            tvTranslation = itemView.findViewById(R.id.tvTranslation);
        }
    }
}
