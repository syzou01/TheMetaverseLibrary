package cs.hku.metalib.book.Controller;


import cs.hku.metalib.book.Entity.BookEntity;
import cs.hku.metalib.book.Entity.CommentEntity;
import cs.hku.metalib.book.Service.BookService;
import cs.hku.metalib.book.Entity.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    created by zousiyi
    2022/05/25
 */

@RestController()
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getbook")
    public String getBook(@RequestParam("usr") String usrId, @RequestParam("bookId") int bookId){
        String book = bookService.getBook(usrId,bookId);
        return book;
    }

    @RequestMapping("/search")
    public String search(@RequestParam("query") String query){
        String book = bookService.search(query);
        return book;
    }

    @RequestMapping("/comment")
    public String Comment(@RequestParam("usr") String usrId, @RequestParam("bookId") int bookId, @RequestParam("comment") String comment){
        ReturnStatus rs = bookService.comment(usrId,bookId,comment);
        return rs.toString();
    }

    @RequestMapping("/comment_view")
    public List<CommentEntity> Comment(@RequestParam("bookId") int bookId){
        List<CommentEntity> cmts = bookService.commentView(bookId);
        return cmts;
    }

    @RequestMapping("/recommend")
    public List<BookEntity> recommend(){
        List<BookEntity> recs = bookService.getRecommendList();
        return recs;
    }


    @RequestMapping("/add_book")
    public String Add(@RequestParam("bookId") int bookId,@RequestParam("title") String title,@RequestParam("author") String author,@RequestParam("address") String address ){
        ReturnStatus rs = bookService.addBook(bookId,title, author, address);
        return rs.toString();
    }

    @RequestMapping("/delete_book")
    public String Delete(@RequestParam("bookId") int bookId){
        ReturnStatus rs = bookService.deleteBook(bookId);
        return rs.toString();
    }

    @RequestMapping("/list_all_book")
    public String ListAllBook(){
        List<BookEntity> rs = bookService.listAllBook();
        return rs.toString();
    }

}

