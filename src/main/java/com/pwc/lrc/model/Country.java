package com.pwc.lrc.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Country {
    private String name;
    private List<String> borders = new ArrayList<>();
}
