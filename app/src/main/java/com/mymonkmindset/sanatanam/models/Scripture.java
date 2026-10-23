package com.mymonkmindset.sanatanam.models;

import java.util.List;

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
public class Scripture {
    private Long id;
    private String title;
    private String category;
    private List<Chapter> chapters;
}
