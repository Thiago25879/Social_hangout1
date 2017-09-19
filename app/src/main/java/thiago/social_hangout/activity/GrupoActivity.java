package thiago.social_hangout.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import thiago.social_hangout.R;
import thiago.social_hangout.fragment.GrupoDetalheFragment;
import thiago.social_hangout.fragment.GrupoNovoFragment;
import thiago.social_hangout.model.Grupo;

/**
 * Created by thiago on 30/08/17.
 */

public class GrupoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grupo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_grupo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String msg = (String) getIntent().getCharSequenceExtra("qualFragmentAbrir");

        if(msg.equals("GrupoNovoFragment")){
            replaceFragment(R.id.fragment_container, new GrupoNovoFragment());
        }else if(msg.equals("GrupoDetalheFragment")){
            GrupoDetalheFragment grupoDetalheFragment = new GrupoDetalheFragment();
            replaceFragment(R.id.fragment_container, grupoDetalheFragment);
            Grupo grupo = (Grupo) getIntent().getSerializableExtra("grupo");
            Log.d(TAG, "Objeto grupo recebido> " + grupo.toString());
            grupoDetalheFragment.setGrupo(grupo);
        }
    }
}
