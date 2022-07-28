package cs.hku.metalib.book.Entity;

import com.alibaba.fastjson.JSONObject;

/*
    created by zousiyi
    2022/06/25
 */

public class ReturnStatus {

    //0=fail;1=success
    int code = 0;
    String state = "null";

    public ReturnStatus(int code, String state){
        this.code = code;
        this.state = state;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("code",this.code);
        json.put("state",this.state);
        return json.toJSONString();
    }

    public int getCode() {
        return code;
    }
}
