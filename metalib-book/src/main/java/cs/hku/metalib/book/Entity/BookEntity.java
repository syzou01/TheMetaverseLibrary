package cs.hku.metalib.book.Entity;


/*
    created by zousiyi
    2022/05/25
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("book_list")
public class BookEntity {
    @TableId
    private int bookId;
    private String bookName;
    private String author;
    private String address;

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getBookName(){ return bookName; }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getAuthor(){ return author; }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAddress(){ return address; }

    public void setAddress(String address){
        this.address = address;
    }


}
