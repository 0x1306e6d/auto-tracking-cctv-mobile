package kr.ac.pusan.walkover.autotrackingcctv.retrofit;

/**
 * Created by soeun on 2017-08-01.
 */
public class Mode {
    private long camera_num;
    private String mode;

    public Mode(long camera_num, String mode) {
        this.camera_num = camera_num;
        this.mode = mode;
    }
}
