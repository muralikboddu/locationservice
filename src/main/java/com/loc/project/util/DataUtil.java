package com.loc.project.util;

import com.loc.project.model.Point;
import com.loc.project.model.PointDistance;
import com.loc.project.service.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataUtil {


    @Autowired
    PointRepository pointRepository;

    public static Set<Point> allPoints = null;
    public static Map<Point,List<PointDistance>> pointListMap = null;

    public static Long returnRandomLongValue() {
        return ThreadLocalRandom.current().nextLong(0,10000);
    }


    public static Double returnRandomDoubleValue() {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.valueOf(df.format(ThreadLocalRandom.current().nextDouble(0,50)));

    }

    public static Point returnRandomPoint() {
        return new Point(returnRandomDoubleValue(),returnRandomDoubleValue(),"121 Park Ave");
    }

    public static double getDistance(Point p1 , Point p2) {
        //sqrt is static method in java.lang.Math which returns double
        return Math.sqrt(((p2.getxCor() - p1.getxCor())*(p2.getxCor() - p1.getxCor()) )+ ((p2.getyCor() - p1.getyCor())*(p2.getyCor() - p1.getyCor())) );
    }

    public void loadMockData() {
        allPoints = new HashSet<Point>();
        List<Point> allPointsList = pointRepository.findAll();
        allPoints = new HashSet<Point>(allPointsList);
        if(!allPoints.isEmpty()) {
            pointListMap = getPointDistancesForAllPoints();
        }

    }

    public List<PointDistance> getPointDistancesForOneReferencePoint(Point sourcePoint) {
        List<PointDistance> pointDistances = new ArrayList<PointDistance>();
        for( Point point : DataUtil.allPoints) {
            pointDistances.add(new PointDistance(point,sourcePoint.getDistance(point)));
        }
        Collections.sort(pointDistances);
        return pointDistances;
    }

    public Map<Point, List<PointDistance>> getPointDistancesForAllPoints() {
        Map<Point,List<PointDistance>> pointListMap = new HashMap<Point,List<PointDistance>>();
        List<PointDistance> pointDistances = null;
        for(Point point : DataUtil.allPoints) {
            pointDistances = getPointDistancesForOneReferencePoint(point);
            if(pointDistances != null && !pointDistances.isEmpty()) {
                pointListMap.put(point,pointDistances);
            }
        }
        return pointListMap;
    }
}
