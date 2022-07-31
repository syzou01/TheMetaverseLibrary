package cs.hku.metalib.admin.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs.hku.metalib.admin.Feign.BookFeignService;
import cs.hku.metalib.admin.Feign.FriendFeignService;
import cs.hku.metalib.admin.Feign.MessageFeignService;
import cs.hku.metalib.admin.Mapper.AdminMapper;
import cs.hku.metalib.admin.Entity.AdminEntity;
import cs.hku.metalib.admin.Entity.ReturnStatus;
import cs.hku.metalib.admin.Feign.UserFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/*
    created by zousiyi
    2022/05/25
 */

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private MessageFeignService messageFeignService;
    @Autowired
    private FriendFeignService friendFeignService;
    @Autowired
    private BookFeignService bookFeignService;


    public ReturnStatus login(String id, String password){
        AdminEntity admin = adminMapper.selectById(id);
        if(admin==null){
            ReturnStatus rs = new ReturnStatus(0,"failed, admin does not exist");
            return rs;
        }
        if(admin.getPassword().equals(password)){
            ReturnStatus rs = new ReturnStatus(1,"success");
            return rs;
        }
        ReturnStatus rs = new ReturnStatus(0,"failed, password is not correct");
        return rs;
    }

    //User
    public ReturnStatus addUser(String id, String password){
        return userFeignService.add(id, password);
    }

    public ReturnStatus deleteUser(String id){
        return userFeignService.delete(id);
    }

    public ReturnStatus updateUser(String id, String password, String status){
        return userFeignService.update(id, password, status);
    }

    public String selectAllUser(){
        return userFeignService.selectAll();
    }

    //Message
    public ReturnStatus addMessage(String from, String to, String msg, Date time, int status){
        return messageFeignService.add(from, to, msg, time, status);
    };

    public ReturnStatus deleteMessage(int id){
        return messageFeignService.delete(id);
    }

    public ReturnStatus updateMessage(String from,String to,String msg,Date time,int status){
        return messageFeignService.update(from, to, msg, time, status);
    }

    public String selectAllMessage(){
        return messageFeignService.selectAll();
    }

    //Friend
    public String deleteFriend(String usr1, String usr2){
        return friendFeignService.deleteFriend(usr1, usr2);
    }

    public ReturnStatus addFriend(String usr1,String usr2){
        return friendFeignService.addFriend(usr1, usr2);
    }

    public String getAllFriendList(){
        return friendFeignService.getAllFriendList();
    }

    //Book
    public String addBook(int bookId, String title, String author, String address){
        return bookFeignService.add(bookId, title, author, address);
    }

    public String deleteBook(int bookId){
        return bookFeignService.delete(bookId);
    }

    public String listAllBook(){
        return bookFeignService.listAllBook();
    }
}
