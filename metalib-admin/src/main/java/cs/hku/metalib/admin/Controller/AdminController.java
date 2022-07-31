package cs.hku.metalib.admin.Controller;


import cs.hku.metalib.admin.Entity.ReturnStatus;
import cs.hku.metalib.admin.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/*
    created by zousiyi
    2022/05/25
 */

@RestController()
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String getBook(@RequestParam("usr") String usrId, @RequestParam("password") String password){
        ReturnStatus login = adminService.login(usrId,password);
        return login.toString();
    }

    //User
    @RequestMapping("/add_user")
    public String addUser(@RequestParam("usr") String usrId, @RequestParam("password") String password){
        ReturnStatus add = adminService.addUser(usrId,password);
        return add.toString();
    }

    @RequestMapping("/delete_user")
    public String deleteUser(@RequestParam("usr") String usrId){
        ReturnStatus delete = adminService.deleteUser(usrId);
        return delete.toString();
    }

    @RequestMapping("/update_user")
    public String updateUser(@RequestParam("usr") String usrId, @RequestParam("password") String password,  @RequestParam("status") String status){
        ReturnStatus update = adminService.updateUser(usrId, password, status);
        return update.toString();
    }

    @RequestMapping("/all_user")
    public String selectAllUser(){
        String result = adminService.selectAllUser();
        return result;
    }

    //Message
    @RequestMapping("/add_msg")
    public ReturnStatus addMessage(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("msg") String msg, @RequestParam("time") Date time, @RequestParam("status") int status) {
        ReturnStatus result = adminService.addMessage(from, to, msg, time, status);
        return result;
    }

    @RequestMapping("/delete_msg")
    public ReturnStatus deleteMessage(@RequestParam("id") int id) {
        ReturnStatus result = adminService.deleteMessage(id);
        return result;
    }

    @RequestMapping("/update_msg")
    public ReturnStatus updateMessage(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("msg") String msg,  @RequestParam("time") Date time,  @RequestParam("status") int status) {
        ReturnStatus result = adminService.updateMessage(from, to, msg, time, status);
        return result;
    }

    @RequestMapping("/select_all_msg")
    public String selectAllMessage() {
        String result = adminService.selectAllMessage();
        return result;
    }

    //Friend
    @RequestMapping("/delete_friend")
    public String deleteFriend(@RequestParam("usr1") String usr1, @RequestParam("usr2") String usr2) {
        String result = adminService.deleteFriend(usr1, usr2);
        return result;
    }


    @RequestMapping("/add_friend")
    public ReturnStatus addFriend(@RequestParam("usr1") String usr1, @RequestParam("usr2") String usr2) {
        ReturnStatus result = adminService.addFriend(usr1, usr2);
        return result;
    }

    @RequestMapping("/get_all_friend_list")
    public String getAllFriendList() {
        String list = adminService.getAllFriendList();
        return list;
    }

    //Book
    @RequestMapping("/add_book")
    public String Add(@RequestParam("bookId") int bookId,@RequestParam("title") String title,@RequestParam("author") String author,@RequestParam("address") String address ){
        String rs = adminService.addBook(bookId,title, author, address);
        return rs;
    }

    @RequestMapping("/delete_book")
    public String Delete(@RequestParam("bookId") int bookId){
        String rs = adminService.deleteBook(bookId);
        return rs;
    }

    @RequestMapping("/list_all_book")
    public String ListAllBook(){
         String rs = adminService.listAllBook();
        return rs;
    }
}

