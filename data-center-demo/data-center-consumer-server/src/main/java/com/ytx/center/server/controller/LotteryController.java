package com.ytx.center.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glodphoenix.starter.mysql.utils.PageUtils;
import com.glodphoenix.starter.web.utils.R;
import com.glodphoenix.starter.web.validator.ValidatorUtils;
import com.google.common.base.Optional;
import com.ytx.center.server.controller.model.LotteryMode;
import com.ytx.center.server.entity.Lottery;
import com.ytx.center.server.service.LotteryService;
import com.ytx.center.server.utils.PositionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;

@RestController
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PositionBuilder positionBuilder;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/save")
    @SentinelResource("save")
    public String saveLottery(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Lottery entity  = new Lottery();
        Random r = new Random();
        int x = r.nextInt();
        entity.setName("test"+x);
        entity.setIssue(r.nextInt()+"");
        entity.setType("bjssc");
        entity.setVersion(entity.getVersion()+1);
        lotteryService.save(entity);
        return entity.getName();
    }

    @GetMapping("/findLotteryById")
    @SentinelResource("findLotteryById")
    public String findLotteryById(@RequestParam(name="id") int id) {

        Lottery entity = lotteryService.getById(id);
        System.out.println(entity);
        return entity.getName();
    }

    @RequestMapping(value = "/page/{currentPage}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @SentinelResource("page")
    public R page(@PathVariable(name="currentPage") int currentPage) {
        Page<Lottery> page = new Page<>(currentPage, 10);
        IPage<Lottery> lotteryPage = lotteryService.page(page);
        /*List<Lottery> records = lotteryPage.getRecords();
        records.stream().map(Lottery::getIssue)
                .forEach(System.out::println);*/
        return  R.ok().put("page", lotteryPage);
    }

    @PostMapping(value="/page2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SentinelResource("page2")
    public R page2(@RequestBody Map params) {
        PageUtils page = lotteryService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping(value="/update/{id}/{newIssue}")
    @SentinelResource("update")
    public String update(@PathVariable(name="newIssue") String newIssue,
                         @PathVariable(name="id")int id){
        lotteryService.updateIssueById(id, newIssue);
        return "sakdfja";
    }




    @GetMapping(value="/lottery/{issue}/{lotteryType}")
    public Lottery findLottery(@PathVariable(name="lotteryType")String lotteryType,
                                @PathVariable(name="issue")String issue){
        Optional<Lottery> lotteryOptional = lotteryService.queryByPrams(lotteryType, issue);
        return lotteryOptional.or(new Lottery());
    }

    @PostMapping(value="/lotteries")
    public R findLotteries(@RequestBody LotteryMode lottery){
        ValidatorUtils.validateEntity(lottery);
        PageUtils pageUtils = lotteryService.queryPage1(lottery);
        System.out.println("CurrPage:"+pageUtils.getCurrPage());
        System.out.println("PageSize:"+pageUtils.getPageSize());
        System.out.println("TotalCount:"+pageUtils.getTotalCount());
        System.out.println("TotalPage:"+pageUtils.getTotalPage());
        return R.ok().put("data",pageUtils.getList());
    }

    @GetMapping(value="/fl")
    public void findLotteryById(){
        Lottery lottery = lotteryService.getById(1260);
        System.out.println(lottery);
    }

}
