package cs.hku.metalib.friend.Mapper;

/*
    created by zousiyi
    2022/06/27
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs.hku.metalib.friend.Entity.FriendRequestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FriendRequestMapper extends BaseMapper<FriendRequestEntity> {

    @Insert(value ="INSERT INTO user_friend_request ( `from`, `to`)  VALUES  ( #{from}, #{to})")
    int insertRequest(String from, String to);

    @Select(value ="select * from user_friend_request req where req.to = #{to} and req.status = 0")
    List<FriendRequestEntity> selectNewByTo(String to);

    @Update(value ="update user_friend_request set status = 1 where request_id = #{id}")
    int acceptReq(int id);

    @Update(value ="update user_friend_request set status = -1 where request_id = #{id}")
    int rejectReq(int id);

    @Update(value ="update user_friend_request set status = 0 where request_id = #{id}")
    int resetStatus(int id);

    @Select(value ="select * from user_friend_request req where req.request_id = #{id}")
    FriendRequestEntity selectId(int id);

    @Select(value ="select * from user_friend_request")
    List<FriendRequestEntity> selectAll();
}
