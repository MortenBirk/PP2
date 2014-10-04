package com.pp2.starlords.pp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* This class takes two lists of positions (readings and groundtruths) and computes a sorted list of the errors
*/
public class CDFHelper{

  /**
  * Assumes readings and truths are same length
  */
  public List<Double> sortedErrors(List<Position> readings, List<Position> truths) {
    List<Double> e = errors(readings, truths);
    Collections.sort(e);
    return e;
  } 

  private List<Double> errors(List<Position> readings, List<Position> truths)  {
      List<Double> res = new ArrayList<Double>();
    for(int i = 0; i < readings.size(); i++) {
      Position r = readings.get(i);
      Position t = truths.get(i);

      double d = distance(r,t);
      res.add(d);
    }
    return res;
  }

  /*
  * Euclidian distance of positons 
  */
  private double distance(Position x, Position y) {
    return Math.sqrt( (x.getLattitude()- y.getLattitude())*(x.getLattitude()- y.getLattitude()) +
                      (x.getLongitude()- y.getLongitude())*(x.getLongitude()- y.getLongitude()));
  }
}