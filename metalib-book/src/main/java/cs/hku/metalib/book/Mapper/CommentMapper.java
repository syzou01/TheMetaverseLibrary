package cs.hku.metalib.book.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs.hku.metalib.book.Entity.CommentEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
    created by zousiyi
    2022/06/25
 */

public interface CommentMapper extends BaseMapper<CommentEntity> {

    @Select(value ="select * from book_comment cmt where cmt.book_id = #{bookId}")
    List<CommentEntity> selectByBookId(int bookId);
}
