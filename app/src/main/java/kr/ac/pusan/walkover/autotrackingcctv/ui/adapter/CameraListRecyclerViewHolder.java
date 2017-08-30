package kr.ac.pusan.walkover.autotrackingcctv.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.ac.pusan.walkover.autotrackingcctv.R;

public class CameraListRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mIpAddressView;
    private TextView mNameView;

    private int mCameraId;
    private String mIpAddress;
    private String mName;

    private final OnCameraCardClickedListener mOnClickedListener;
    private final OnCameraCardLongClickedListener mOnLongClickedListener;

    public CameraListRecyclerViewHolder(View itemView, OnCameraCardClickedListener onClicked, OnCameraCardLongClickedListener onLongClicked) {
        super(itemView);
        mOnClickedListener = onClicked;
        mOnLongClickedListener = onLongClicked;

        mIpAddressView = itemView.findViewById(R.id.camera_card_ip_address);
        mNameView = itemView.findViewById(R.id.camera_card_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickedListener != null) {
                    mOnClickedListener.onClicked(view, mCameraId);
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnLongClickedListener != null) {
                    return mOnLongClickedListener.onLongClicked(view, mCameraId);
                } else {
                    return false;
                }
            }
        });
    }

    public void setCameraId(int cameraId) {
        mCameraId = cameraId;
    }

    public void setIpAddress(String ipAddress) {
        mIpAddressView.setText(ipAddress);
        mIpAddress = ipAddress;
    }

    public void setName(String name) {
        mNameView.setText(name);
        mName = name;
    }
}
