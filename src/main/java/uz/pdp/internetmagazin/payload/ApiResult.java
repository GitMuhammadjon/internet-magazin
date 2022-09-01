package uz.pdp.internetmagazin.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult {

    private boolean success;

    private String message;

    private Object data;

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
