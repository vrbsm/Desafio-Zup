package br.com.vrbsm.challenge.ui.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.ui.listener.OnErrorListener;
import br.com.vrbsm.challenge.util.controlfrags.ControlFrags;

/**
 * Created by vmascare on 29/11/17.
 */

public abstract class AbstractActivity extends AppCompatActivity implements OnDialogListener, OnErrorListener{

    private android.app.ProgressDialog mProgressDialog;
    protected FragmentManager fragmentManager;

    @Override
    protected void onResume() {
        super.onResume();
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Carregando ... ");

    }

    @Override
    public void showDialog() {
        try {
            if (mProgressDialog != null)
                mProgressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismissDialog() {
        if (mProgressDialog != null)
            if (!isFinishing()) {
                mProgressDialog.dismiss();
            }

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Method to add fragment with add to BackStack.
     *
     * @param control
     * @param layoutId
     * @param addBackStack
     */
    public void addFragment(final ControlFrags control,
                            final int layoutId,
                            final boolean addBackStack) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        try {
           AbstractFragment agentFragment = control.getClassFrag().newInstance();
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.add(layoutId, agentFragment, control.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (addBackStack) {
            fragmentTransaction.addToBackStack(control.getName());
        }

        fragmentTransaction.commitAllowingStateLoss();

    }

    /**
     * Method to add fragment with add to BackStack and Bundle option.
     *
     * @param control
     * @param container
     * @param addBackStack
     * @param bundle
     */
    public void addFragment(final ControlFrags control,
                            final int container,
                            final boolean addBackStack,
                            final Bundle bundle) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        try {
            AbstractFragment agentFragment = control.getClassFrag().newInstance();
            agentFragment.setArguments(bundle);
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.add(container, agentFragment, control.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }

        if (addBackStack) {
            fragmentTransaction.addToBackStack(control.getName());
        }

        fragmentTransaction.commitAllowingStateLoss();

    }
    /**
     * Method to replace a fragment with add to BackStack and Bundle option.
     *
     * @param control
     * @param container
     * @param addBackStack
     * @param bundle
     */
    public void replaceFragment(final ControlFrags control,
                                final int container,
                                final boolean addBackStack,
                                final Bundle bundle) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        try {
            AbstractFragment agentFragment = control.getClassFrag().newInstance();
            agentFragment.setArguments(bundle);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(container, agentFragment, control.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (addBackStack) {
            fragmentTransaction.addToBackStack(control.getName());
        }

        if (!isFinishing()) {
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Method to replace a fragment with add to BackStack and Bundle option.
     *
     * @param fragment
     * @param tag
     * @param container
     * @param addBackStack
     * @param bundle
     */
    public void replaceFragment(@NonNull final Fragment fragment, @NonNull final String tag,
                                @NonNull final int container,
                                final boolean addBackStack,
                                @Nullable Bundle bundle) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(container, fragment, tag);

        if (addBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }

        if (!isFinishing()) {
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Method to remove a fragment
     *
     * @param fragment
     */
    public void removeFragment(final Fragment fragment) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * Method to remove a fragment
     *
     * @param control
     */
    public void removeFragment(final ControlFrags control) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        try {
            fragmentTransaction.remove(control.getClassFrag().newInstance());
            fragmentTransaction.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * Realiza o tratamento dos erros atraves de seu codigo http
     *
     * @param code
     */
    @Override
    public void onErrorHandle(int code) {

        View view = getCurrentFocus();

        switch (code) {
            case 401:
                Log.d(getClass().getCanonicalName(), "code: 401");
                break;
            case 404:
                Log.d(getClass().getCanonicalName(), "code: 404");

                break;
            default:
                Log.d(getClass().getCanonicalName(), "code: default");

                break;
        }
    }

}
