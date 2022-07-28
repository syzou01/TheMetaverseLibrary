package cs.hku.metalib.book.Entity;

/*
    created by zousiyi
    2022/06/27
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("book_recommend")
public class RecommendEntity {

    @TableId
    private int recId;
    private int bookId;

    public int getRecId() {
        return recId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
