package com.loc.project;

import com.loc.project.model.Point;
import com.loc.project.service.PointRepository;
import com.loc.project.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class LoadMockData implements CommandLineRunner {

    @Autowired
    PointRepository pointService;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    DataUtil dataUtil;


    public static final int load_random_points_count=10000;

    public void run(String[] args) {
        int i=0;
        while(i < load_random_points_count ) {
            Point point = DataUtil.returnRandomPoint();
            point.setId(i);
            pointService.save(point);
            i++;
        }
        dataUtil.loadMockData();
    }


}
