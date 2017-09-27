package kr.ac.pusan.walkover.autotrackingcctv.model;

/**
 * Created by kwonsoeun on 2017-09-28.
 */

public class ResponseModel {

    private boolean success;
    private String reason;

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "success=" + success +
                ", reason='" + reason + '\'' +
                '}';
    }
}
