package com.mymonkmindset.sanatanam.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Verse {
    private String reference;      // e.g. "अध्याय 2, श्लोक 47"
    private String title;          // e.g. "अध्याय 2, श्लोक 47"
    private String chapterTitle;
    private String scriptureTitle;
    private List<String> lines;    // List of lines in the verse
    private String translation;    // Meaning / commentary
}
