package com.mymonkmindset.sanatanam.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class FirstCarouselCard {
    private  String text;
    private  String imageUrl;
}
