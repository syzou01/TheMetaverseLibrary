package cs.hku.metalib.message.Service;

/*
    created by zousiyi
    2022/06/27
 */


import cs.hku.metalib.message.Entity.ReturnStatus;
import cs.hku.metalib.message.Entity.MsgEntity;
import cs.hku.metalib.message.Feign.UserFeignService;
import cs.hku.metalib.message.Mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgService {

    @Autowired
    MsgMapper msgMapper;

    @Autowired
    UserFeignService userFeignService;

    public ReturnStatus sendMsg(String from, String to, String msg){
        if(msg == null || msg.length() == 0){
            ReturnStatus rs = new ReturnStatus(0,"failed, message can not be empty");
            return rs;
        }
        ReturnStatus userF = userFeignService.exist(from);
        ReturnStatus userT = userFeignService.exist(to);
        if(userF.getCode()==0|| userT.getCode()==0){
            ReturnStatus rs = new ReturnStatus(0,"failed, sender or receiver does not exist");
            return rs;
        }
        //MsgEntity m = new MsgEntity();
        //m.setFrom(from);
        //m.setTo(to);
        //m.setMessage(msg);
        //m.setTime(new Date(System.currentTimeMillis()));
        Date time = new Date(System.currentTimeMillis());
        int ins = msgMapper.insertMsg(from, to, msg,time);
        if(ins <= 0){
            ReturnStatus rs = new ReturnStatus(0,"message sent failed");
            return rs;
        }
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public List<MsgEntity> receiveMsg(String usr){
//        ReturnStatus user = userFeignService.exist(usr);
//        if(usr == null||user==null){
//            ReturnStatus rs = new ReturnStatus(0,"failed, user does not exist");
//            return rs;
//        }
        List<MsgEntity> msgs = msgMapper.searchByUsr(usr);
        return msgs;
    }

    public ReturnStatus markMsg(int id){
        MsgEntity msg = msgMapper.selectByMsgId(id);
        if(msg == null){
            ReturnStatus rs = new ReturnStatus(0,"message does not exist");
            return rs;
        }
        msgMapper.markMsg(id);
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }
}
