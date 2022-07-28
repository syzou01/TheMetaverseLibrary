package cs.hku.metalib.friend.Controller;


import cs.hku.metalib.friend.Entity.ReturnStatus;
import cs.hku.metalib.friend.Entity.FriendEntity;
import cs.hku.metalib.friend.Entity.FriendRequestEntity;
import cs.hku.metalib.friend.Service.FriendService;
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
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping("/get_friend_list")
    public List<FriendEntity> getFriendList(@RequestParam("usr") String usrId) {
        List<FriendEntity> list = friendService.getFriendList(usrId);
        return list;
    }

    @RequestMapping("/send_friend_request")
    public String sendFriendRequest(@RequestParam("from") String from, @RequestParam("to") String to) {
        ReturnStatus result = friendService.sendFriendRequest(from, to);
        return result.toString();
    }

    @RequestMapping("/receive_friend_request")
    public List<FriendRequestEntity> receiveFriendRequest(@RequestParam("usr") String usrId) {
        List<FriendRequestEntity> requestList = friendService.receiveFriendRequest(usrId);
        return requestList;
    }

    @RequestMapping("/agree_friend_request")
    public String agreeFriendRequest(@RequestParam("reqId") int reqId) {
        ReturnStatus result = friendService.agreeFriendRequest(reqId);
        return result.toString();
    }

    @RequestMapping("/reject_friend_request")
    public String rejectFriendRequest(@RequestParam("reqId") int reqId) {
        ReturnStatus result = friendService.rejectFriendRequest(reqId);
        return result.toString();
    }

    @RequestMapping("/delete_friend")
    public String deleteFriend(@RequestParam("usr1") String usr1,@RequestParam("usr2") String usr2) {
        ReturnStatus result = friendService.deleteFriend(usr1,usr2);
        return result.toString();
    }

    @RequestMapping("/get_friend_request_list")
    public List<FriendRequestEntity> getFriendRequestList() {
        List<FriendRequestEntity> list = friendService.getAllFriendRequestList();
        return list;
    }

}
