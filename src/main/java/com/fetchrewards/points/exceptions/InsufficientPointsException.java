package com.fetchrewards.points.exceptions;

public class InsufficientPointsException extends RuntimeException{
    public InsufficientPointsException(int available, int needed){
        super("InsufficientPointsException: Available "+ available+ " points, but need " + needed +" points");
    }
}
