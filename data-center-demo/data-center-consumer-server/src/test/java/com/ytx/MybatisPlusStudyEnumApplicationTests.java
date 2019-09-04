package com.ytx;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytx.center.server.DataCenterConsumerApp;
import com.ytx.center.server.dao.UserDao;
import com.ytx.center.server.entity.User;
import com.ytx.center.server.extension.enums.AgeEnum;
import com.ytx.center.server.extension.enums.GenderEnum;
import com.ytx.center.server.extension.enums.GradeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataCenterConsumerApp.class)
public class MybatisPlusStudyEnumApplicationTests {

    @Resource
    private UserDao userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("Ray");
        user.setAge(AgeEnum.ONE);
        user.setGrade(GradeEnum.HIGH);
        user.setGender(GenderEnum.MALE);
        user.setEmail("abc@mp.com");
        Assert.assertTrue(userMapper.insert(user) > 0);
        // 成功直接拿 ID
        System.err.println("\n插入成功 ID 为: " + user.getId());

        List<User> list = userMapper.selectList(null);
        for (User u : list) {
            System.out.println(u);
            Assert.assertNotNull("age should not be null", u.getAge());
            if (u.getId().equals(user.getId())) {
                Assert.assertNotNull("gender should not be null", u.getGender());
                Assert.assertNotNull("grade should not be null", u.getGrade());
            }
        }
    }

    @Test
    public void delete() {
        Assert.assertTrue(userMapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getAge, AgeEnum.TWO)) > 0);
    }

    @Test
    public void update() {
        Assert.assertTrue(userMapper.update(new User().setAge(AgeEnum.TWO),
                new QueryWrapper<User>().eq("age", AgeEnum.THREE)) > 0);
    }

    @Test
    public void select() throws IOException {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
        System.out.println(JSONUtils.serializeObject(user));
        Assert.assertEquals("Jack", user.getName());
        Assert.assertTrue(AgeEnum.THREE == user.getAge());
    }

}
