package com.fetchrewards.points.exceptions;

 public class NegativePointsException extends RuntimeException{
     public NegativePointsException(String payer, int points) {
        super("NegativePointsException: Payer " + payer + " points should not go negative: " + points);
    }
}
