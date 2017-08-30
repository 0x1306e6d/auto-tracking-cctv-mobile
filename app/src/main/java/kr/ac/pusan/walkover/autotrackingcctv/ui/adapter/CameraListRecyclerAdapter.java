package kr.ac.pusan.walkover.autotrackingcctv.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.pusan.walkover.autotrackingcctv.R;

public class CameraListRecyclerAdapter extends RecyclerView.Adapter<CameraListRecyclerViewHolder> {
    @Override
    public CameraListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.camera_card, parent, false);
        return new CameraListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CameraListRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
