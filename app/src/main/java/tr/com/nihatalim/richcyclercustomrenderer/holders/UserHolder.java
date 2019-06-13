package tr.com.nihatalim.richcyclercustomrenderer.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tr.com.nihatalim.richcyclercustomrenderer.R;

public class UserHolder extends RecyclerView.ViewHolder {
    public TextView tvUserName;

    public UserHolder(@NonNull View itemView) {
        super(itemView);
        this.tvUserName = itemView.findViewById(R.id.tvUserName);
    }
}
