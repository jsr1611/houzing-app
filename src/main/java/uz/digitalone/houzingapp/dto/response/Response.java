package uz.digitalone.houzingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.*;

@Data
@AllArgsConstructor
public class Response {

    private boolean success;
    private String message;
    private Object data;
    private List<Object> dataList = new ArrayList<>();
    private Map<Object, Object> map = new HashMap<>();
    private HttpStatus status;


    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
        if(success)
            this.status = HttpStatus.OK;
    }

    public Response(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        if(data instanceof List){
            this.dataList = Collections.singletonList(data);
        }else {
            this.data = data;
        }
        if(success)
            this.status = HttpStatus.OK;
    }

    public Response(boolean success, String message, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }
}
