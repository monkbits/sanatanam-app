package com.mymonkmindset.sanatanam.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mymonkmindset.sanatanam.R;
import com.mymonkmindset.sanatanam.models.FirstCarouselCard;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.FirstCardHolder> {
    private final List<FirstCarouselCard> items;

    public CarouselAdapter(List<FirstCarouselCard> items) {
        this.items = items;
    }

    public static class FirstCardHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvText;
        public FirstCardHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivCarouselImage);
            tvText = itemView.findViewById(R.id.tvCarouselText);
        }
    }

    @NonNull
    @Override
    public CarouselAdapter.FirstCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_carousel_first_card, parent, false);
        return new FirstCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselAdapter.FirstCardHolder holder, int position) {
        FirstCarouselCard card = items.get(position);

        holder.tvText.setText(card.getText());

        Glide.with(holder.itemView.getContext())
                .load(card.getImageUrl())
                .placeholder(android.R.drawable.picture_frame)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
