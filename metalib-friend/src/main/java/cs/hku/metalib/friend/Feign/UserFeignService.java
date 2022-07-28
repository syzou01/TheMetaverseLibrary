package cs.hku.metalib.friend.Feign;

import cs.hku.metalib.friend.Entity.ReturnStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("metalib-user")
public interface UserFeignService {

    @RequestMapping("/user/exist")
    public ReturnStatus exist(String usrId);
}
