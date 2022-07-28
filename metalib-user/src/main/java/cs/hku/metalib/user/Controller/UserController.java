package cs.hku.metalib.user.Controller;


import cs.hku.metalib.user.Entity.ReturnStatus;
import cs.hku.metalib.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    created by zousiyi
    2022/06/23
 */

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam("usr") String usr, @RequestParam("psw") String password){
        ReturnStatus result = userService.login(usr,password);
        return result.toString();
    }


    @RequestMapping("/logout")
    public String logout(@RequestParam("usr") String usr) {
        ReturnStatus result = userService.logout(usr);
        return result.toString();
    }

    @RequestMapping("/exist")
    public ReturnStatus exist(@RequestParam("usr") String usr) {
        ReturnStatus result = userService.exist(usr);
        return result;
    }

}
