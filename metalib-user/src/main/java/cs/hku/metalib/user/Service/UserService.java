package cs.hku.metalib.user.Service;

import cs.hku.metalib.user.Entity.ReturnStatus;
import cs.hku.metalib.user.Entity.UserEntity;
import cs.hku.metalib.user.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ReturnStatus add(String usrId, String password){
        UserEntity eUser = userMapper.selectById(usrId);
        if(eUser!=null){
            ReturnStatus rs = new ReturnStatus(0,"failed, user already exist");
            return rs;
        }

        UserEntity nUser = new UserEntity();
        nUser.setUserId(usrId);
        nUser.setPassword(password);
        userMapper.insert(nUser);
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public ReturnStatus delete(String usrId){
        UserEntity eUser = userMapper.selectById(usrId);
        if(eUser==null){
            ReturnStatus rs = new ReturnStatus(0,"failed, user does not exist");
            return rs;
        }
        userMapper.deleteById(usrId);
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public ReturnStatus update(String usrId, String password, String status){
        UserEntity eUser = userMapper.selectById(usrId);
        if(eUser==null){
            ReturnStatus rs = new ReturnStatus(0,"failed, user does not exist");
            return rs;
        }

        UserEntity nUser = new UserEntity();
        nUser.setUserId(usrId);
        nUser.setPassword(password);
        nUser.setUserState(status);
        userMapper.updateById(nUser);
        ReturnStatus rs = new ReturnStatus(1,"success");
        return rs;
    }

    public List<UserEntity> selectAll(){
        List<UserEntity> list = userMapper.selectAll();
        return list;
    }

}
