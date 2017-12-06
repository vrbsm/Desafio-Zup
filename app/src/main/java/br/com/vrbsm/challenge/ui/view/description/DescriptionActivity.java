package br.com.vrbsm.challenge.ui.view.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.search.SearchResultsFragment;
import br.com.vrbsm.challenge.util.controlfrags.ControlFrags;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionActivity extends AbstractActivity{
    public static final int CONTAINER = R.id.fr_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        replaceFragment(ControlFrags.DESCRIPTION_FRAGMENT, CONTAINER,false, extras);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_description, menu);

        return true;
    }
}
