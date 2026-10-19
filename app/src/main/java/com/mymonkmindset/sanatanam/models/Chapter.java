package com.mymonkmindset.sanatanam.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Chapter {
    private String chapterTitle;
    private String scriptureTitle;
    private String chapterNumber;
    private List<Verse> verses;
}
