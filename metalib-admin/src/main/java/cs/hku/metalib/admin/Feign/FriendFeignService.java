package cs.hku.metalib.admin.Feign;

import cs.hku.metalib.admin.Entity.ReturnStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("metalib-friend")
public interface FriendFeignService {
    @RequestMapping("friend/delete_friend")
    public String deleteFriend(String usr1, String usr2);

    @RequestMapping("friend/add_friend")
    public ReturnStatus addFriend(String usr1,String usr2);

    @RequestMapping("friend/get_all_friend_list")
    public String getAllFriendList();
}
