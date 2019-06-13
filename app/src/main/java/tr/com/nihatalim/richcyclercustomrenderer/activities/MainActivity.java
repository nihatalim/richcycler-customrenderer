package tr.com.nihatalim.richcyclercustomrenderer.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import tr.com.nihatalim.richcycler.adapter.OnAdapter;
import tr.com.nihatalim.richcycler.views.Richcycler;
import tr.com.nihatalim.richcyclercustomrenderer.R;
import tr.com.nihatalim.richcyclercustomrenderer.holders.UserHolder;
import tr.com.nihatalim.richcyclercustomrenderer.models.User;

public class MainActivity extends AppCompatActivity {

    private Richcycler<UserHolder, User> richcycler;
    private Button btnShowFilter;

    private static final String FILTER_ID = "CustomSwitchRendererFilter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnShowFilter = findViewById(R.id.btnShowFilter);
        this.btnShowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflatedView = LayoutInflater.from(getContext()).inflate(R.layout.filter_main, null);
                LinearLayout linearLayout = inflatedView.findViewById(R.id.llContent);

                List<View> viewss = richcycler.getViews(FILTER_ID);
                if(viewss != null) {
                    for (View v : viewss) {
                        linearLayout.addView(v);
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        richcycler.saveFiltersStates(FILTER_ID);
                        //TODO handleResults();
                    }
                });

                builder.setNegativeButton("Clear Filters", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reloadFilters();
                    }
                });
                builder.setView(inflatedView);
                builder.show();
            }
        });

        this.richcycler = findViewById(R.id.rcUsers);

        this.richcycler.adapter.setOnAdapter(new OnAdapter<UserHolder>() {
            @Override
            public UserHolder onCreate(ViewGroup parent, int viewType, View view) {
                return new UserHolder(view);
            }

            @Override
            public void OnBind(UserHolder userHolder, int position) {
                userHolder.tvUserName.setText(richcycler.adapter.items.get(position).name);
            }

            @Override
            public RecyclerView.LayoutManager setLayoutManager(RecyclerView.LayoutManager layoutManager) {
                ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
                return layoutManager;
            }
        });

        this.richcycler.build();

        this.richcycler.adapter.addAll(fetchUsers(1, 20), true);

        this.richcycler.loadFilters(FILTER_ID);
    }

    public List<User> fetchUsers(int pageNumber, int paginationSize){
        List<User> users = new ArrayList<>();

        for(int i = 0;i<paginationSize;i++){
            users.add(new User("Page_" + pageNumber + " Item_" + i));
        }
        return users;
    }

    private Context getContext(){return this;}

    private void reloadFilters(){
        this.richcycler.clearFiltersStates(FILTER_ID);
    }
}
