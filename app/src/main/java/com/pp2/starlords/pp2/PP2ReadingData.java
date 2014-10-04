package com.pp2.starlords.pp2;

import com.jjoe64.graphview.GraphViewDataInterface;

public class PP2ReadingData implements GraphViewDataInterface {

  private double x;
  private double y;

  public PP2ReadingData(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

}