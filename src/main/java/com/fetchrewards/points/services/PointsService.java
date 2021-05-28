package com.fetchrewards.points.services;

import com.fetchrewards.points.entities.Payer;
import com.fetchrewards.points.entities.Transaction;

import java.util.Map;

public interface PointsService {

    Transaction addPoints(Transaction transaction);

    Iterable<Payer> spendPoints(Integer points);

    Map<String, Integer> getPayersBalance();
}
