package br.com.vrbsm.challenge.ui.view.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionFragment extends AbstractFragment {
    @BindView(R.id.img_description)
    ImageView imageDescription;
    @BindView(R.id.tx_description)
    TextView txDescription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container,false);
        ButterKnife.bind(this, view);
        txDescription.setText("");
        return view;
    }
}
