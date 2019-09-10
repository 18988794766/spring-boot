package xy.standard.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoService {
    public String getData() {
        log.info("111111111111111111111");
        return "****************************";
    }

    public void printThrowException(){
        log.info("2222222222222222222222");
        throw new IllegalArgumentException();
    }
}
