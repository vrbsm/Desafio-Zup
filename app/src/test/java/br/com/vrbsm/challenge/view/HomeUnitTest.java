package br.com.vrbsm.challenge.view;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import br.com.vrbsm.challenge.BuildConfig;
import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.description.DescriptionActivity;
import br.com.vrbsm.challenge.ui.view.home.HomeActivity;
import br.com.vrbsm.challenge.ui.view.home.HomeContract;
import br.com.vrbsm.challenge.ui.view.home.HomePresenterImpl;

import static junit.framework.Assert.assertEquals;

/**
 * Created by vrbsm on 07/12/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class HomeUnitTest {

    private HomeContract.Presenter mPresenter;
    @Before
    public void setUp() throws Exception {
        mPresenter = new HomePresenterImpl();
    }

//    @Test
//    public void whenClickList_shouldStartDescriptionActivity() throws Exception {
//        HomeActivity activity = Robolectric.setupActivity(HomeActivity.class);
//
//        Intent expectedIntent = new Intent(activity, DescriptionActivity.class);
//        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());
//
//    }
}
