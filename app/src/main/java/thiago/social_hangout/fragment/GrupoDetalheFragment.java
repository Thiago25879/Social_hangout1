package thiago.social_hangout.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import thiago.social_hangout.R;
import thiago.social_hangout.activity.BaseActivity;
import thiago.social_hangout.activity.GrupoActivity;
import thiago.social_hangout.activity.GruposActivity;
import thiago.social_hangout.adapter.TabsAdapter;
import thiago.social_hangout.model.Grupo;

/**
 * Created by thiago on 16/09/17.
 */

public class GrupoDetalheFragment extends BaseFragment  {

    private Grupo grupo;
    private ProgressBar progressBar;
    private TextView textViewNome;
    private ImageView imageView;
    private Button button;



    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        ((GrupoActivity) getActivity()).getSupportActionBar().setTitle("Detalhes do Grupo");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhegrupo, container, false);
        textViewNome = (TextView) view.findViewById(R.id.tvNome_card0_frdetalhegrupo);
        textViewNome.setText(grupo.nome);
        if(grupo.foto != null){
            imageView = (ImageView) view.findViewById(R.id.imv_card0_frdetalhegrupo);
            imageView.setImageURI(Uri.parse(grupo.foto));
            Log.d(TAG, "URL foto = " + grupo.foto);
        }

        button = (Button) view.findViewById(R.id.btedicaogrupo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btedicaogrupo){
                    Log.d(TAG,"Funciona");
                    GrupoEdicaoFragment grupoEdicaoFragment = new GrupoEdicaoFragment();
                    grupoEdicaoFragment.setGrupo(grupo);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, grupoEdicaoFragment).commit();
                }
            }
        });
        return view;
    }



    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_detalhegrupo, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuitem_editar:
                GrupoEdicaoFragment grupoEdicaoFragment = new GrupoEdicaoFragment();
                grupoEdicaoFragment.setGrupo(this.grupo);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, grupoEdicaoFragment).commit();
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return true;
    }
    */
}
