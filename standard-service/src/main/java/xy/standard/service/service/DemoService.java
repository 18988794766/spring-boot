package xy.standard.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xy.standard.service.annotation.AutoLog;

@Slf4j
@Service
public class DemoService {
    @AutoLog(logType = 1, logContent = "自动日志打印")
    public String getData(String str) {
        log.info("111111111111111111111");
        return "****************************";
    }

    public void printThrowException(){
        log.info("2222222222222222222222");
        throw new IllegalArgumentException();
    }
}
