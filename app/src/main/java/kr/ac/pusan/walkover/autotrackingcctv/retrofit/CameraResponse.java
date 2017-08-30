package kr.ac.pusan.walkover.autotrackingcctv.retrofit;

public class CameraResponse {

    private int id;
    private String ipAddress;
    private String name;

    public CameraResponse(int id, String ipAddress, String name) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CameraResponse{");
        sb.append("id=").append(id);
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
