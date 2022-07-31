package cs.hku.metalib.friend.Service;

/*
    created by zousiyi
    2022/06/27
 */


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs.hku.metalib.friend.Entity.ReturnStatus;
import cs.hku.metalib.friend.Entity.FriendEntity;
import cs.hku.metalib.friend.Entity.FriendRequestEntity;
import cs.hku.metalib.friend.Feign.UserFeignService;
import cs.hku.metalib.friend.Mapper.FriendMapper;
import cs.hku.metalib.friend.Mapper.FriendRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Autowired
    FriendRequestMapper friendRequestMapper;

    @Autowired
    UserFeignService userFeignService;

    public List<FriendEntity> getFriendList(String usrId){
        QueryWrapper<FriendEntity> equalWrapper = new QueryWrapper<FriendEntity>();
        equalWrapper.eq("user_id",usrId);
        List<FriendEntity> list = friendMapper.selectList(equalWrapper);
        return list;
    }

    public ReturnStatus sendFriendRequest(String from, String to){
        ReturnStatus userF = userFeignService.exist(from);
        ReturnStatus userT = userFeignService.exist(to);
        if(userF.getCode()==0 || userT.getCode() == 0){
            ReturnStatus rs = new ReturnStatus(0,"failed, sender or receiver does not exist");
            return rs;
        }
        FriendEntity f1 = friendMapper.selectByName(from,to);
        FriendEntity f2 = friendMapper.selectByName(to,from);
        if(f1!=null&&f2!=null){
            ReturnStatus rs = new ReturnStatus(0,"they are already friends");
            return rs;
        }
//        FriendRequestEntity req = new FriendRequestEntity();
//        req.setFrom(from);
//        req.setTo(to);
        int result = friendRequestMapper.insertRequest(from,to);
        if(result>0){
            ReturnStatus rs = new ReturnStatus(1,"success");
            return rs;
        }
        ReturnStatus rs = new ReturnStatus(0,"failed to send request");
        return rs;
    }

    public List<FriendRequestEntity> receiveFriendRequest(String usrId){
//        QueryWrapper<FriendRequestEntity> equalWrapper = new QueryWrapper<FriendRequestEntity>();
//        equalWrapper.eq("to",usrId);
//        List<FriendRequestEntity> list = friendRequestMapper.selectList(equalWrapper);
        List<FriendRequestEntity> list = friendRequestMapper.selectNewByTo(usrId);
        return list;
    }

    public ReturnStatus agreeFriendRequest(int reqId){
        FriendRequestEntity req = friendRequestMapper.selectId(reqId);
        if(req == null){
            ReturnStatus rs = new ReturnStatus(0,"failed, request does not exist");
            return rs;
        }
        if(req.getStatus()>0){
            ReturnStatus rs = new ReturnStatus(0,"request has been accepted");
            return rs;
        }
        if(req.getStatus()<0){
            ReturnStatus rs = new ReturnStatus(0," failed, request has been rejected");
            return rs;
        }
        int result1 = friendRequestMapper.acceptReq(reqId);
        if(result1 <= 0){
            ReturnStatus rs = new ReturnStatus(0,"failed to accept, please try again");
            return rs;
        }
        FriendEntity f1 = friendMapper.selectByName(req.getFrom(), req.getTo());
        FriendEntity f2 = friendMapper.selectByName(req.getTo(), req.getFrom());
        if(f1!=null&&f2!=null){
            ReturnStatus rs = new ReturnStatus(0,"they are already friends");
            return rs;
        }
        if(f1==null){
            FriendEntity f1add = new FriendEntity();
            f1add.setUserId(req.getFrom());
            f1add.setContactId(req.getTo());
            int result2 = friendMapper.insert(f1add);
            if(result2<=0){
                friendRequestMapper.resetStatus(reqId);
                ReturnStatus rs = new ReturnStatus(0,"failed to accept, please try again");
                return rs;
            }
        }
        if(f2==null){
            FriendEntity f2add = new FriendEntity();
            f2add.setUserId(req.getTo());
            f2add.setContactId(req.getFrom());
            int result3 = friendMapper.insert(f2add);
            if(result3<=0){
                friendRequestMapper.resetStatus(reqId);
                ReturnStatus rs = new ReturnStatus(0,"failed to accept, please try again");
                return rs;
            }
        }
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public ReturnStatus rejectFriendRequest(int reqId){
        FriendRequestEntity req = friendRequestMapper.selectId(reqId);
        if(req == null){
            ReturnStatus rs = new ReturnStatus(0,"failed, request does not exist");
            return rs;
        }
        if(req.getStatus()>0){
            ReturnStatus rs = new ReturnStatus(0,"failed, request has been accepted");
            return rs;
        }
        if(req.getStatus()<0){
            ReturnStatus rs = new ReturnStatus(0," request has been rejected");
            return rs;
        }
        int result1 = friendRequestMapper.rejectReq(reqId);
        if(result1 <= 0){
            ReturnStatus rs = new ReturnStatus(0,"failed to accept, please try again");
            return rs;
        }
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public ReturnStatus deleteFriend(String usr1, String usr2){
        FriendEntity rela1 = friendMapper.selectByName(usr1, usr2);
        FriendEntity rela2 = friendMapper.selectByName(usr2, usr1);
        if(rela1 == null || rela2 == null){
            ReturnStatus rs = new ReturnStatus(0,"failed, friend relation does not exist");
            return rs;
        }
        int result1 = friendMapper.deleteById(rela1.getRelationId());
        int result2 = friendMapper.deleteById(rela2.getRelationId());
        if(result1 <= 0 || result2 <= 0){
            ReturnStatus rs = new ReturnStatus(0,"failed to delete, please try again");
            return rs;
        }

        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public List<FriendRequestEntity> getAllFriendRequestList(){
        List<FriendRequestEntity> list = friendRequestMapper.selectAll();
        return list;
    }

    public ReturnStatus addFriend(String usr1, String usr2){

        FriendEntity f1 = friendMapper.selectByName(usr1, usr2);
        FriendEntity f2 = friendMapper.selectByName(usr2, usr1);
        if(f1==null){
            FriendEntity f1add = new FriendEntity();
            f1add.setUserId(usr1);
            f1add.setContactId(usr2);
            int result2 = friendMapper.insert(f1add);
            if(result2<=0){
                ReturnStatus rs = new ReturnStatus(0,"failed please try again");
                return rs;
            }
        }
        if(f2==null){
            FriendEntity f2add = new FriendEntity();
            f2add.setUserId(usr2);
            f2add.setContactId(usr1);
            int result3 = friendMapper.insert(f2add);
            if(result3<=0){
                ReturnStatus rs = new ReturnStatus(0,"failed, please try again");
                return rs;
            }
        }
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public List<FriendEntity> getAllFriendList(){
        List<FriendEntity> list = friendMapper.selectAll();
        return list;
    }

}
