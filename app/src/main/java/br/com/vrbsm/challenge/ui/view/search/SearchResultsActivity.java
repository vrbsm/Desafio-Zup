package br.com.vrbsm.challenge.ui.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.util.controlfrags.ControlFrags;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AbstractActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    public static final int CONTAINER = R.id.fr_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pesquisar");


        replaceFragment(ControlFrags.SEARCH_FRAGMENT, CONTAINER, false, null);

    }


}
