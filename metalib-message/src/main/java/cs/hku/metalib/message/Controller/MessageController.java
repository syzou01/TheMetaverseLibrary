package cs.hku.metalib.message.Controller;


import cs.hku.metalib.message.Entity.ReturnStatus;
import cs.hku.metalib.message.Entity.MsgEntity;
import cs.hku.metalib.message.Service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/*
    created by zousiyi
    2022/06/23
 */

@RestController()
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MsgService msgService;

    @RequestMapping("/send_msg")
    public String send(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("msg") String msg) {
        ReturnStatus result = msgService.sendMsg(from, to, msg);
        return result.toString();
    }

    @RequestMapping("/receive_msg")
    public List<MsgEntity> receive(@RequestParam("usr") String to) {
        List<MsgEntity> result = msgService.receiveMsg(to);
        return result;
    }

    @RequestMapping("/mark_msg_read")
    public String receive(@RequestParam("id") int id) {
        ReturnStatus result = msgService.markMsg(id);
        return result.toString();
    }

    @RequestMapping("/add")
    public ReturnStatus add(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("msg") String msg,  @RequestParam("time") Date time,  @RequestParam("status") int status) {
        ReturnStatus result = msgService.add(from, to, msg, time, status);
        return result;
    }

    @RequestMapping("/delete")
    public ReturnStatus delete(@RequestParam("id") int id) {
        ReturnStatus result = msgService.delete(id);
        return result;
    }

    @RequestMapping("/update")
    public ReturnStatus update(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("msg") String msg,  @RequestParam("time") Date time,  @RequestParam("status") int status) {
        ReturnStatus result = msgService.update(from, to, msg, time, status);
        return result;
    }

    @RequestMapping("/select_all")
    public String selectAll() {
        List<MsgEntity> result = msgService.selectAll();
        return result.toString();
    }
}
