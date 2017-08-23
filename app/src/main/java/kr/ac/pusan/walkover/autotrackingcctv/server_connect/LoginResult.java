package kr.ac.pusan.walkover.autotrackingcctv.server_connect;

import java.io.Serializable;

/**
 * Created by soeun on 2017-07-21.
 */
public class LoginResult implements Serializable {
    private String ip_address, port_num;
    private Integer camera_num;

    public LoginResult() {
    }

    public String get_ip_address() {
        return ip_address;
    }

    public String get_port_num() {
        return port_num;
    }

    public Integer get_camera_num() {
        return camera_num;
    }

    public void set_ip_address(String IpAddress) {
        this.ip_address = IpAddress;
    }

    public void set_port_num(String PortNum) {
        this.port_num = PortNum;
    }

    public void set_camera_num(Integer CameraNum) {
        this.camera_num = CameraNum;
    }
}
