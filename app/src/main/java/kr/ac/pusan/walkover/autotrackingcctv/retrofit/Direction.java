package kr.ac.pusan.walkover.autotrackingcctv.retrofit;

/**
 * Created by soeun on 2017-07-24.
 */
public class Direction {
    private long camera_num;
    private int direction;

    public Direction(long camera_num, int direction) {
        this.camera_num = camera_num;
        this.direction = direction;
    }
}
