package kr.ac.pusan.walkover.autotrackingcctv.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.ac.pusan.walkover.autotrackingcctv.R;
import kr.ac.pusan.walkover.autotrackingcctv.model.CameraModel;

public class CameraListRecyclerAdapter extends RecyclerView.Adapter<CameraListRecyclerViewHolder> {

    private List<CameraModel> mDataSet;
    private final OnCameraCardClickedListener mOnClickedListener;
    private final OnCameraCardLongClickedListener mOnLongClickedListener;

    public CameraListRecyclerAdapter(OnCameraCardClickedListener onClicked, OnCameraCardLongClickedListener onLongClicked) {
        mOnClickedListener = onClicked;
        mOnLongClickedListener = onLongClicked;
    }

    public void changeDataSet(List<CameraModel> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public CameraListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.camera_card, parent, false);
        return new CameraListRecyclerViewHolder(view, mOnClickedListener, mOnLongClickedListener);
    }

    @Override
    public void onBindViewHolder(CameraListRecyclerViewHolder holder, int position) {
        CameraModel cameraResponse = mDataSet.get(position);
        holder.setCameraId(cameraResponse.getId());
        holder.setName(cameraResponse.getName());
        holder.setIpAddress(cameraResponse.getAddress().getIp());
    }

    @Override
    public int getItemCount() {
        return mDataSet != null ? mDataSet.size() : 0;
    }
}
