package cs.hku.metalib.admin.Feign;

import cs.hku.metalib.admin.Entity.ReturnStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@FeignClient("metalib-message")
public interface MessageFeignService {

    @RequestMapping("/message/add")
    public ReturnStatus add(String from, String to, String msg, Date time, int status);

    @RequestMapping("/message/delete")
    public ReturnStatus delete(int id);

    @RequestMapping("/message/update")
    public ReturnStatus update(String from,String to,String msg,Date time,int status);

    @RequestMapping("/message/select_all")
    public String selectAll();
}
