package cs.hku.metalib.book.Mapper;

/*
    created by zousiyi
    2022/05/25
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs.hku.metalib.book.Entity.BookEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<BookEntity> {

    @Select(value ="select * from book_list book where book.book_name LIKE  '%' #{query} '%'")
    List<BookEntity> searchByStr(String query);

}
