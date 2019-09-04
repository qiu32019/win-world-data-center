package com.ytx;

import com.ytx.center.server.DataCenterConsumerApp;
import com.ytx.center.server.dao.LotteryDao;
import com.ytx.center.server.entity.Lottery;
import com.ytx.center.server.entity.Position;
import com.ytx.center.server.extension.enums.SourceSiteEnum;
import com.ytx.center.server.service.LotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataCenterConsumerApp.class)
public class MybatisPlusStudyJSonApplicaitonTest {
    @Resource
    private LotteryDao lotteryDao;

    @Autowired
    private LotteryService lotteryService;

    @Test
    public void insert()  {
        Lottery l = new Lottery<Position>();
        l.setIssue("201908172011");
        l.setType("bjssc");
        l.setVersion(1);
        l.setName("北京时时彩");
        l.setSite(SourceSiteEnum.KJW168);
        /*MyBall ball = new MyBall();
        ball.setBall1(1);
        ball.setBall2(2);
        ball.setBall3(3);
        ball.setBall4(4);
        ball.setBall5(5);
        l.setData(ball);*/
        Position p = new Position();
        p.setP1("1");
        p.setP2("2");
        p.setP3("3");
        p.setP4("4");
        p.setP5("5");
        l.setData(p);
        /*JSONObject json = new JSONObject();
        json.put("ball1", 1);
        json.put("ball2", 2);
        json.put("ball3", 3);
        json.put("ball4", 4);
        json.put("ball5", 5);
        l.setData(json);*/

        lotteryDao.insert(l);
    }

    @Test
    public void testGetById(){
//        Page<Lottery> page = new Page<>(1, 10);
//        IPage<Lottery> lotteryPage = lotteryService.page(page);
//        lotteryPage.getRecords().forEach(System.out::println);

        Lottery<Position> lottery = lotteryDao.selectById(22);
        System.out.println(lottery);
    }
}
