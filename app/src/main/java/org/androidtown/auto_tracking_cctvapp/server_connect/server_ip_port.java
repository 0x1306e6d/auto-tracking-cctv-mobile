package org.androidtown.auto_tracking_cctvapp.server_connect;

import java.io.Serializable;

/**
 * Created by soeun on 2017-07-21.
 */
public class server_ip_port implements Serializable {
    private String ip_address, port_num;

    public server_ip_port() {}

    public String get_ip_address() {
        return ip_address;
    }

    public String get_port_num() {
        return port_num;
    }

    public void set_ip_address(String IpAddress) {
        this.ip_address = IpAddress;
    }

    public void set_port_num(String PortNum) {
        this.port_num = PortNum;
    }

}
