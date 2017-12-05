package br.com.vrbsm.challenge.util.controlfrags;

import br.com.vrbsm.challenge.presenter.observable.presenter.SearchResultsPresenter;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import br.com.vrbsm.challenge.ui.view.description.DescriptionFragment;
import br.com.vrbsm.challenge.ui.view.home.HomeFragment;
import br.com.vrbsm.challenge.ui.view.search.SearchResultsFragment;

/**
 * Created by vmascare on 29/11/17.
 */

public enum ControlFrags {

    HOME_FRAGMENT("home_fragment", HomeFragment.class),
    SEARCH_FRAGMENT("search_fragment", SearchResultsFragment.class),
    DESCRIPTION_FRAGMENT("description_fragment", DescriptionFragment.class);
    private String name;
    private Class<? extends AbstractFragment> classFrag;

    ControlFrags(final String name, Class<? extends AbstractFragment> classFrag) {
        this.name = name;
        this.classFrag = classFrag;
    }

    public String getName() {
        return name;
    }

    public Class<? extends AbstractFragment> getClassFrag() {
        return classFrag;
    }
}
