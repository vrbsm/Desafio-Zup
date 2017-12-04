package br.com.vrbsm.challenge.ui.view.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.util.controlfrags.ControlFrags;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AbstractActivity {

    @BindView(R.id.toolbar_main) Toolbar toolbar;
    public static final int CONTAINER_HOME = R.id.fr_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Titulo");


        replaceFragment(ControlFrags.HOME_FRAGMENT, CONTAINER_HOME, false, null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }
}
