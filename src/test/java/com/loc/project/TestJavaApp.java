package com.loc.project;

import com.loc.project.impl.LocationServiceImpl;
import com.loc.project.model.Point;
import com.loc.project.service.LocationService;
import com.loc.project.util.DataUtil;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith( SpringJUnit4ClassRunner.class )
@ActiveProfiles("test")
@SpringBootTest
public class TestJavaApp {

    @Autowired
    DataUtil dataUtil;

    @Autowired
    LocationServiceImpl locationService;


    @Test
    public void checkLongValue() {
        Long x = DataUtil.returnRandomLongValue();
        Assert.assertTrue(x <= 10000L);
    }


    @Test
    public void checkDoubleValue() {
        Double y = DataUtil.returnRandomDoubleValue();
        String[] splitter = y.toString().split("\\.");
        if(splitter.length !=2) {
            Assert.assertTrue(false);
       } else {

        int decimalLength = splitter[1].length();
        System.out.println(y);
        Assert.assertTrue(y < 50.00 && decimalLength == 2);

        }
    }

    @Before
    public void  getLocPoints() {
        dataUtil.loadMockData();
        Assert.assertTrue(DataUtil.allPoints.size() == 100);

    }

    @Test
    public void getPointsInCircleBruteForce() {
        Set<Point> inCirclePoints = locationService.getPointsInCircleBruteForce(new Point(4.9, 2.29, "121 Park Ave"), 20L);
        Assert.assertTrue(inCirclePoints.size() == 21);
    }

    @Test
    public void getPointsInCircleByPointDistance() {
        Set<Point> inCirclePoints = locationService.getPointsInCircleByPointDistance(new Point(4.9, 2.29, "121 Park Ave"), 20L);
        Assert.assertTrue(inCirclePoints.size() == 21);
    }

    @Test
    public void getPointsByUsingCache() {
        Set<Point> inCirclePoints = locationService.getPointsInCircleByCachedPointDistance(new Point(4.9, 2.29, "121 Park Ave"), 20L);
        Assert.assertTrue(inCirclePoints.size() == 21);
    }

}
