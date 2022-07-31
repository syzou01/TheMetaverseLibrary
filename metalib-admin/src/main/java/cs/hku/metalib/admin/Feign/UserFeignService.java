package cs.hku.metalib.admin.Feign;

import cs.hku.metalib.admin.Entity.ReturnStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("metalib-user")
public interface UserFeignService {

    @RequestMapping("/user/exist")
    public ReturnStatus exist(String usrId);

    @RequestMapping("/user/add")
    public ReturnStatus add(String usrId, String password);

    @RequestMapping("/user/delete")
    public ReturnStatus delete(String usrId);

    @RequestMapping("/user/update")
    public ReturnStatus update(String usrId, String password, String status);

    @RequestMapping("/user/select_all")
    public String selectAll();
}
