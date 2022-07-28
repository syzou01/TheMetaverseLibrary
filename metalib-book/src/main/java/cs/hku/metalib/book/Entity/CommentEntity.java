package cs.hku.metalib.book.Entity;

/*
    created by zousiyi
    2022/06/25
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("book_comment")
public class CommentEntity {
    @TableId(value = "comment_id", type = IdType.AUTO)
    private int commentId;
    private String comment;
    private String userId;
    private int bookId;
    private Date time;

    public int getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getTime() {
        return time;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
