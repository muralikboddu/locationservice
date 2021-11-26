package com.loc.project.impl;

import com.loc.project.LoadMockData;
import com.loc.project.model.Point;
import com.loc.project.model.PointDistance;
import com.loc.project.service.LocationService;
import com.loc.project.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LocationServiceImpl implements LocationService {

    @Autowired
    DataUtil dataUtil;


    @Override
    public Set<Point> getPointsInCircleBruteForce(Point p, Long radius) {


        Iterator<Point> it = DataUtil.allPoints.iterator();
        Set<Point> pointsInCircle = new HashSet<Point>();
        try {
        while(it.hasNext()) {
            Point currentPoint = it.next();
            if(DataUtil.getDistance(p,currentPoint) <= radius) {
                pointsInCircle.add(currentPoint);
                currentPoint.displayPoint();
            }
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            return pointsInCircle;

        }
    }

    @Override
    public Set<Point> getPointsInCircleByPointDistance(Point p, Long radius) {
        List<PointDistance> pointDistanceList = dataUtil.getPointDistancesForOneReferencePoint(p);
        Set<Point> points = getPointsWithInRadius(pointDistanceList,radius);
        return points;
    }

    @Override
    public Set<Point> getPointsInCircleByCachedPointDistance(Point p, Long radius) {
         /*
         this uses equals and hashcode implementation of Point class.
         A better implementation of hashcode ensures the time complexity to get point
         from the HashMap is O(1)
         */
        List<PointDistance> pointDistances = DataUtil.pointListMap.get(p);
        Set<Point> points = getPointsWithInRadius(pointDistances,radius);
        return points;
    }

    public Set<Point> getPointsWithInRadius(List<PointDistance> pointDistanceList, Long radius) {
        Iterator<PointDistance> pointDistanceIterator = pointDistanceList.iterator();
        List<Point> pointsInCircle= new ArrayList<Point>();
        try {

        while(pointDistanceIterator.hasNext()){
            PointDistance pointDistance = pointDistanceIterator.next();
            if(pointDistance.getDistance() <= radius) {
                pointsInCircle.add(pointDistance.getPoint());
            } else {
                break;
            }

        }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new HashSet<Point>(pointsInCircle);

        }

    }


}
