package kr.ac.pusan.walkover.autotrackingcctv.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.ac.pusan.walkover.autotrackingcctv.R;

public class CameraListRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mIpAddress;
    private TextView mName;

    public CameraListRecyclerViewHolder(View itemView) {
        super(itemView);
        mIpAddress = itemView.findViewById(R.id.camera_card_ip_address);
        mName = itemView.findViewById(R.id.camera_card_name);
    }
}
