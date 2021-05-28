package com.fetchrewards.points.services;

import com.fetchrewards.points.entities.Payer;
import com.fetchrewards.points.entities.Transaction;
import com.fetchrewards.points.exceptions.InsufficientPointsException;
import com.fetchrewards.points.exceptions.NegativePointsException;
import com.fetchrewards.points.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointsServiceImpl implements PointsService{
    @Autowired
    private TransactionRepository transactionRepository;

    // Stores the amount of points spent by each payer
    private final Map<String, Integer> pointsSpentMap = new HashMap<>();

    //Stores the total points per payer
    private final Map<String, Integer> payers = new HashMap<>();

    //Stores the total available points we have
    private Integer totalAvailablePoints = 0;

    /**
     * Increment a value by delta and return the new value.
     *
     * @param  transaction  transaction for a specific payer and date
     * @return              returns the transaction after persisting it in the h2 database.
     */
    @Override
    public Transaction addPoints(Transaction transaction) {
        String payer = transaction.getPayer();
        int points = payers.getOrDefault(payer, 0) + transaction.getPoints();

        //Make sure no payer's points to go negative
        if (points < 0){
            throw new NegativePointsException(payer, points);
        }
        totalAvailablePoints += transaction.getPoints();
        payers.put(payer, points);

        return transactionRepository.save(transaction);
    }

    /**
     * This method spends points by the payers and returns the points spent by each payer.
     *
     * @param  points  the number of points to be spent.
     * @return         returns the points spent by each payer.
     */
    @Override
    public List<Payer> spendPoints(Integer points) {
        //Make sure you have enough points to spend
        if (totalAvailablePoints < points){
            throw new InsufficientPointsException(totalAvailablePoints, points);
        }

        Iterable<Transaction> transactions = transactionRepository.findAllByOrderByTimestampAsc();

        for (Transaction transaction : transactions){
            if (points == 0)break;
            else {
                int payerPoints = transaction.getPoints();
                String payer = transaction.getPayer();
                if (points >= payerPoints){
                    points -= payerPoints;
                    totalAvailablePoints -= payerPoints;
                    payers.put(payer, payers.get(payer) - payerPoints);
                    pointsSpentMap.put(payer, pointsSpentMap.getOrDefault(payer, 0) + (-1 * payerPoints));
                    transactionRepository.deleteById(transaction.getId());
                }else{
                    int diff = payerPoints - points;
                    totalAvailablePoints -= points;
                    payers.put(payer, payers.get(payer) - points);
                    pointsSpentMap.put(payer, pointsSpentMap.getOrDefault(payer, 0) + (-1 * points));
                    points = 0;
                    transaction.setPoints(diff);
                    transactionRepository.save(transaction);
                }
            }
        }

        //Return the amount spend per payer involved
        List<Payer> pointsSpent = new ArrayList<>();
        for (var entry : pointsSpentMap.entrySet()){
            pointsSpent.add(new Payer(entry.getKey(), entry.getValue()));
        }
        //Reset the points spent map
        pointsSpentMap.clear();

        return pointsSpent;
    }

    /**
     * This method returns the balance points of each payer.
     *
     * @return  returns the balance of each payer.
     */
    @Override
    public Map<String, Integer> getPayersBalance() {
        return payers;
    }
}
