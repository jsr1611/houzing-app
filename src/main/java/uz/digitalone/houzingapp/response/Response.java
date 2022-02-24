package uz.digitalone.houzingapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private boolean success;
    private String message;
    private Object data;
    private List<Object> dataList;
    private Map<Object, Object> map = new HashMap<>();

    public Response(boolean success, String message, List<Object> dataList) {
        this.success = success;
        this.message = message;
        this.dataList = dataList;
    }

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Response(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
