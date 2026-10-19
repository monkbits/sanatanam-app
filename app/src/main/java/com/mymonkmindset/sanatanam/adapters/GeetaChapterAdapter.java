package com.mymonkmindset.sanatanam.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mymonkmindset.sanatanam.R;
import com.mymonkmindset.sanatanam.models.Chapter;

import java.util.List;

public class GeetaChapterAdapter extends RecyclerView.Adapter<GeetaChapterAdapter.GeetaChapterViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(Chapter chapter, int position);
    }
    private final List<Chapter> chapters;
    private  OnItemClickListener listener;

    public GeetaChapterAdapter(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public GeetaChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_geeta_chapter, parent, false);
        return new GeetaChapterViewHolder(view, listener, chapters);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GeetaChapterViewHolder holder, int position) {
        Chapter chapter = chapters.get(position);
        holder.chapterTitle.setText(". " +chapter.getChapterTitle());
        holder.chapterNumber.setText(chapter.getChapterNumber());
    }

    @Override
    public int getItemCount() {
        return chapters != null? chapters.size(): 0;
    }

    public class GeetaChapterViewHolder extends RecyclerView.ViewHolder {
        TextView chapterTitle, chapterNumber;
        public GeetaChapterViewHolder(View itemView, OnItemClickListener listener, List<Chapter> chapters) {
            super(itemView);
            chapterTitle = itemView.findViewById(R.id.tvChapterTitle);
            chapterNumber = itemView.findViewById(R.id.tvChapterNumber);

            itemView.setOnClickListener(v ->
                {
                    int position = getBindingAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(chapters.get(position), position);
                    }
                }
            );
        }
    }
}
