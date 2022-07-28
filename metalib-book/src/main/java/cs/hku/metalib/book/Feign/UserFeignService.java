package cs.hku.metalib.book.Feign;

import cs.hku.metalib.book.Entity.ReturnStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("metalib-user")
public interface UserFeignService {

    @RequestMapping("/user/exist")
    public ReturnStatus exist(String usrId);
}
