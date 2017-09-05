package kr.ac.pusan.walkover.autotrackingcctv.model;

public class NetworkModel {

    private String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NetworkModel{");
        sb.append("ip='").append(ip).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
