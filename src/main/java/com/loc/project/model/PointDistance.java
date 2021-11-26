package com.loc.project.model;

public class PointDistance implements Comparable<PointDistance>{
    private Point point;
    private double distance;

    public PointDistance(Point point, double distance) {
        this.point = point;
        this.distance = distance;
    }

    public Point getPoint() {
        return point;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(PointDistance o) {
        return distance < o.distance ? -1 : 1;
    }
}
