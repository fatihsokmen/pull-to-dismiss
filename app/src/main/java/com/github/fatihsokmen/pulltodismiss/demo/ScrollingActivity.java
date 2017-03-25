package com.github.fatihsokmen.pulltodismiss.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.fatihsokmen.pulltodismiss.PullDismissLayout;

public class ScrollingActivity extends AppCompatActivity implements PullDismissLayout.Listener {

    private PullDismissLayout pullDismissLayout;
    private AppBarLayout appBarLayout;
    private boolean blockEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pullDismissLayout = (PullDismissLayout) findViewById(R.id.pull_dismiss_layout);
        pullDismissLayout.setListener(this);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                blockEvents =  (verticalOffset != 0);
            }
        });
    }

    @Override
    public void onDismissed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    public boolean onShouldInterceptTouchEvent() {
        return blockEvents;
    }
}
