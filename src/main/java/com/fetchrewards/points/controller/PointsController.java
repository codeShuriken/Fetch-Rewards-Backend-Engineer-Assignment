package com.fetchrewards.points.controller;

import com.fetchrewards.points.entities.Payer;
import com.fetchrewards.points.entities.Points;
import com.fetchrewards.points.entities.Transaction;
import com.fetchrewards.points.services.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/points")
public class PointsController {
    @Autowired
    private PointsService pointsService;

    @PostMapping("/addPoints")
    public Transaction addPoints(@RequestBody Transaction transaction){
        return pointsService.addPoints(transaction);
    }

    @PostMapping("/spendPoints")
    public Iterable<Payer> spendPoints(@RequestBody Points points){
        return pointsService.spendPoints(points.getPoints());
    }

    @GetMapping("/payersBalance")
    public Map<String, Integer> getPayersBalance(){
        return pointsService.getPayersBalance();
    }
}
