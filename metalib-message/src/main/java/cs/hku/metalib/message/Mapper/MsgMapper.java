package cs.hku.metalib.message.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs.hku.metalib.message.Entity.MsgEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/*
    created by zousiyi
    2022/06/25
 */

public interface MsgMapper extends BaseMapper<MsgEntity> {

    @Insert(value ="INSERT INTO user_message ( `from`, `time`, `to`, `message` )  VALUES  ( #{from}, #{time}, #{to}, #{msg} )")
    int insertMsg(String from, String to, String msg, Date time);

    @Select(value ="select * from user_message msg where msg.to = #{usr} and msg.status = 0")
    List<MsgEntity> searchByUsr(String usr);

    @Update(value ="update user_message set status = 1 and message_id = #{id}")
    int markMsg(int id);

    @Select(value ="select * from user_message msg where msg.message_id = #{id}")
    MsgEntity selectByMsgId(int id);

    @Select(value ="select * from user_message")
    List<MsgEntity> selectAll();
}
