package thiago.social_hangout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import thiago.social_hangout.R;
import thiago.social_hangout.activity.GrupoActivity;
import thiago.social_hangout.activity.GruposActivity;
import thiago.social_hangout.model.Grupo;

/**
 * Created by Cliente on 31/08/2017.
 */

public class GrupoNovoFragment extends BaseFragment {

    Grupo grupo;
    String novo_grupo;
    private EditText editText_novo;
    private ImageView imageView_novo;
    private Button button_novo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        ((GrupoActivity) getActivity()).getSupportActionBar().setTitle("Novo Grupo");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novogrupo, container, false);
        grupo = new Grupo();

        imageView_novo = (ImageView) view.findViewById(R.id.imv_card0_frdetalhegrupo);
        imageView_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.)
            }
        };



        return view;
    }
}
