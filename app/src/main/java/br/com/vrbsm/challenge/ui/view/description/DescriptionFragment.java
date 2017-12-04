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

/**
 * Created by vmascare on 01/12/17.
 */

public class DescriptionFragment extends AbstractFragment {
    @BindView(R.id.img_description)
    ImageView imageDescription;
    @BindView(R.id.tx_description)
    TextView txDescription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_description, container,false);
        ButterKnife.bind(this, view);
        txDescription.setText("O final do ano está chegando e seria bem bacana fazermos uma confraternização, com direito a amigo oculto, comida e muita bebida! *-*\n" +
                "Obs: Não é uma festa organizada pelo INDT, e sim pelos próprios funcionários.\n" +
                " \n" +
                "Temos as seguintes opções:\n" +
                " \n" +
                "CONFRATERNIZAÇÃO COM AMIGO OCULTO ( VALOR DA LEMBRANÇA R$ 20,00)\n" +
                "Opção 1: Imperial Pub (data: 21/12)\n" +
                " \n" +
                "VALOR R$ 30 POR PESSOA. MINIMO 50 PESSOAS. (KARAOKE)\n" +
                "Petiscos\n" +
                "300 salgadinhos (bolinha de bacalhau, queijo, frango,\n" +
                "caranguejo, carne, camarão)\n" +
                "10 porções de pasteis (60 unidades)\n" +
                "10 porções de linguicinha acebolada\n" +
                "10 porções de file com fritas\n" +
                "300 salgadinhos (bolinha de bacalhau, queijo, frango,\n" +
                "caranguejo, carne, camarão)\n" +
                "10 porções de pasteis (60 unidades)\n" +
                "10 porções de linguicinha acebolada\n" +
                "10 porções de file com fritas\n" +
                "300 salgadinhos (bolinha de bacalhau, queijo, frango,\n" +
                "caranguejo, carne, camarão)\n" +
                "10 porções de pasteis (60 unidades)\n" +
                "10 porções de linguicinha acebolada\n" +
                "10 porções de file com fritas\n" +
                "300 salgadinhos (bolinha de bacalhau, queijo, frango,\n" +
                "caranguejo, carne, camarão)\n" +
                "10 porções de pasteis (60 unidades)\n" +
                "10 porções de linguicinha acebolada\n" +
                "10 porções de file com fritas\n" +
                "Bebidas\n" +
                "2 refrigerantes lata p/ pessoa (120 refrigerantes)\n" +
                "20 litros de cerveja brahma ou 1 chopp de 300ml pra cada (60 chopps)\n" +
                " \n" +
                "Agua liberada\n" +
                "Taxa de serviço incluso");
        return view;
    }
}
