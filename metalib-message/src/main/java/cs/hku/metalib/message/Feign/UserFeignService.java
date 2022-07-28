package cs.hku.metalib.message.Feign;

import cs.hku.metalib.message.Entity.ReturnStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("metalib-user")
public interface UserFeignService {

    @RequestMapping("/user/exist")
    public ReturnStatus exist(String usrId);
}
