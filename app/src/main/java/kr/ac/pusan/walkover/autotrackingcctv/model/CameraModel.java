package kr.ac.pusan.walkover.autotrackingcctv.model;

public class CameraModel {

    private long id;
    private String name;
    private NetworkModel address;
    private ResolutionModel resolution;
    private int framerate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NetworkModel getAddress() {
        return address;
    }

    public ResolutionModel getResolution() {
        return resolution;
    }

    public int getFramerate() {
        return framerate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CameraModel{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address=").append(address);
        sb.append(", resolution=").append(resolution);
        sb.append(", framerate=").append(framerate);
        sb.append('}');
        return sb.toString();
    }
}
