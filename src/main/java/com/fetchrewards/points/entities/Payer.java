package com.fetchrewards.points.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payer {
    private String payer;
    private Integer points;
}
