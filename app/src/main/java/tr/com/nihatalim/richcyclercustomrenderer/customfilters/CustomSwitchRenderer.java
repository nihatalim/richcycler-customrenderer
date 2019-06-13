package tr.com.nihatalim.richcyclercustomrenderer.customfilters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import tr.com.nihatalim.richcycler.filters.Filter;
import tr.com.nihatalim.richcycler.filters.FilterType;
import tr.com.nihatalim.richcyclercustomrenderer.R;

public class CustomSwitchRenderer extends Filter<Boolean> {

    private boolean status;
    private Switch mSwitch;

    public CustomSwitchRenderer(Context context, String name, String display, String renderer, FilterType type) {
        super(context, name, display, renderer, type);
    }

    @Override
    public View render() {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_switch, null);
        ((TextView) view.findViewById(R.id.tvCustomFilterSwitch)).setText(display);
        this.mSwitch = view.findViewById(R.id.customFilterSwitch);
        mSwitch.setChecked(status);
        return view;
    }

    @Override
    public Boolean result() {
        return status;
    }

    @Override
    public void clear() {
        status = false;
    }

    @Override
    public void save() {
        status = mSwitch.isChecked();
    }
}
