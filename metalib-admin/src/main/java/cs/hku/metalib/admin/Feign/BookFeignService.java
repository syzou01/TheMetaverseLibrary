package cs.hku.metalib.admin.Feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("metalib-book")
public interface BookFeignService {

    @RequestMapping("book/add_book")
    public String add(int bookId, String title, String author, String address);

    @RequestMapping("book/delete_book")
    public String delete(int bookId);

    @RequestMapping("book/list_all_book")
    public String listAllBook();
}
