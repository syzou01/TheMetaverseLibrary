package cs.hku.metalib.user.Service;

import cs.hku.metalib.user.Entity.ReturnStatus;
import cs.hku.metalib.user.Entity.UserEntity;
import cs.hku.metalib.user.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    created by zousiyi
    2022/06/23
 */

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public ReturnStatus login(String usrId, String password) {
        UserEntity eUser = userMapper.selectById(usrId);
        if(eUser==null){
            ReturnStatus rs = new ReturnStatus(0,"failed, user does not exist");
            return rs;
        }
        if(eUser.getPassword().equals(password)){
            userMapper.login(usrId);
            ReturnStatus rs = new ReturnStatus(1,"success");
            return rs;
        }
        ReturnStatus rs = new ReturnStatus(0,"failed, password is not correct");
        return rs;
    }

    public ReturnStatus logout(String usrId){
        UserEntity eUser = userMapper.selectById(usrId);
        if(eUser==null){
            ReturnStatus rs = new ReturnStatus(0,"failed, user does not exist");
            return rs;
        }
        if(eUser.getUserState().equals("OFF")){
            ReturnStatus rs = new ReturnStatus(0,"failed, user is not online");
            return rs;
        }
        userMapper.logout(usrId);
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public ReturnStatus exist(String usrId){
        UserEntity eUser = userMapper.selectById(usrId);
        if(eUser==null){
            ReturnStatus rs = new ReturnStatus(0,"failed, user does not exist");
            return rs;
        }

        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }


}
