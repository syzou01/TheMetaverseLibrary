package cs.hku.metalib.book.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs.hku.metalib.book.Entity.BookEntity;
import cs.hku.metalib.book.Entity.CommentEntity;
import cs.hku.metalib.book.Entity.RecommendEntity;
import cs.hku.metalib.book.Feign.UserFeignService;
import cs.hku.metalib.book.Mapper.BookMapper;
import cs.hku.metalib.book.Mapper.CommentMapper;
import cs.hku.metalib.book.Mapper.RecommendMapper;
import cs.hku.metalib.book.Entity.ReturnStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/*
    created by zousiyi
    2022/05/25
 */

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private UserFeignService userFeignService;

    public String getBook(String usrId, int bookId){

        BookEntity book = bookMapper.selectById(bookId);
        log.info("book add = "+book.getAddress());

        String reString =  read(book.getAddress(),"");
        String[] strArr = stringToStringArray(reString, 1000);
        JSONObject object = toJson(strArr, strArr.length);

        return object.toJSONString();
    }

    public String search(String query){
        String result = "{";
        List<BookEntity> books = bookMapper.searchByStr(query);
        log.info("search result books = "+books.toString());
        int resultNum = books.size();
        result += "\"ResultNum\" : \"" + String.valueOf(resultNum) +"\", \"ResultContent\" : \"" ;
        for(BookEntity book : books){
            result += String.valueOf(book.getBookId()) + " = "+book.getBookName()+",";
        }
        result.substring(0,result.length()-2);
        result += "\"}";
        return result;
    }


    public static String read(String name,String path){
        //		path-->/home/
        File file = new File(path+name);
        if(!file.exists()) {
            return "successfully in the service while the path does not exist...";
        }
        String readData=null;
        StringBuffer reString=new StringBuffer();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(file), "UTF-8") );
            //    BufferedReader br = new BufferedReader(fr);
            try{
                while((readData = br.readLine())!=null){
                    reString.append(readData);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                br.close();
                fr.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reString.toString();
    }

    //将书籍字符串分割为数组
    public static String[] stringToStringArray(String src, int length) {
        //检查参数是否合法
        if (null == src || src.equals("")) {
            return null;
        }
        if (length <= 0) {
            return null;
        }
        int n = (src.length() + length - 1) / length; //获取整个字符串可以被切割成字符子串的个数
        if(n>5){n = 5;} //限制最多5页
        String[] split = new String[n];
        for (int i = 0; i < n; i++) {
            if((i + 1) * length<src.length()){
                split[i] = src.substring(i * length, (i + 1) * length);
            }else{
                split[i] = src.substring(i * length);
            }
        }
        return split;
    }

    //将书籍数组转化为json
    public JSONObject toJson(String[] arr, int n) {
        JSONObject object = new JSONObject();
        object.put("PageNum",String.valueOf(n));
        for(int i=1;i<=n;i++){
            object.put("Page"+String.valueOf(i),arr[i-1]);
        }
        return object;
    }

    public ReturnStatus comment(String usrId,int bookId, String comment){
        BookEntity book = bookMapper.selectById(bookId);
        ReturnStatus exist = userFeignService.exist(usrId);
        if(book == null || exist.getCode()==0){
            ReturnStatus rs = new ReturnStatus(0,"failed, book or user does not exist");
            return rs;
        }
        //int result = commentMapper.insertCmt(comment,new Date(System.currentTimeMillis()),usrId,bookId);
        CommentEntity cmt = new CommentEntity();
        cmt.setComment(comment);
        cmt.setBookId(bookId);
        cmt.setUserId(usrId);
        cmt.setTime(new Date(System.currentTimeMillis()));
        int result = commentMapper.insert(cmt);
        if(result>0){
            ReturnStatus rs = new ReturnStatus(1,"success");
            return rs;
        }
        ReturnStatus rs = new ReturnStatus(0,"failed to add comment");
        return rs;
    }

    public List<CommentEntity> commentView(int bookId){
        List<CommentEntity> cmts = commentMapper.selectByBookId(bookId);
        return cmts;
    }

    public List<BookEntity> getRecommendList(){
        QueryWrapper<RecommendEntity> isNotNullWrapper = new QueryWrapper<RecommendEntity>();
        isNotNullWrapper.isNotNull("book_id");
        List<RecommendEntity> recList = recommendMapper.selectList(isNotNullWrapper);
        Collection<Integer> idList = new ArrayList<Integer>();
        for(RecommendEntity rec: recList){
            idList.add(rec.getBookId());
        }
        return bookMapper.selectBatchIds(idList);
    }
}
