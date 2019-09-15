package xy.standard.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xy.standard.api.vo.QueryCardReqVo;
import xy.standard.dao.dao.admin.AdminUserDao;
import xy.standard.service.exception.BusinessException;
import xy.standard.service.helper.RedisService;
import xy.standard.service.service.DemoService;
import xy.standard.service.vo.BaseRequestVo;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mock")
public class MockController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private AdminUserDao adminUserDao;

    @Autowired
    private DemoService demoService;

    @RequestMapping("/log")
    public List log() {
        log.debug("这是debug打印");
        log.info("这是info打印");
        log.warn("这是warn打印");
        log.error("这是error打印");

        redisService.set("huanglibin", "yes");

        ArrayList<String> data = new ArrayList<>();
        data.add((String) redisService.get("str1"));
        //return data;

        return adminUserDao.selectList(new QueryWrapper<>());
    }

    @RequestMapping("/aspect1")
    public String aspect1() {
        String data = demoService.getData("请求参数");
        log.info("1111111");
        return data;
    }

    @RequestMapping("/aspect2")
    public String aspect2() {
        demoService.printThrowException();
        return "****************************";
    }

    @RequestMapping("/req")
    public String request(@RequestBody @Validated BaseRequestVo<QueryCardReqVo> req) {
        return "****************************";
    }
}
