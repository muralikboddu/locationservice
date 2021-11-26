package com.loc.project.service;

import com.loc.project.model.Point;
import com.loc.project.model.PointDistance;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface LocationService {

    public Set<Point> getPointsInCircleBruteForce(Point p , Long radius);
    public Set<Point> getPointsInCircleByPointDistance(Point p, Long radius);
    public Set<Point> getPointsInCircleByCachedPointDistance(Point p, Long radius);
    // public Map<Point, List<PointDistance>> getPointDistancesForAllPoints();
    //public List<PointDistance> getPointDistancesForOneReferencePoint(Point referencePoint);


}
