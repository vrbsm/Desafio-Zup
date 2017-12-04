package br.com.vrbsm.challenge.ui.view.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.util.controlfrags.ControlFrags;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vmascare on 01/12/17.
 */

public class DescriptionActivity extends AbstractActivity{
    public static final int CONTAINER = R.id.fr_container;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.my_films);


        replaceFragment(ControlFrags.DESCRIPTION_FRAGMENT, CONTAINER,false, null);

    }
}
