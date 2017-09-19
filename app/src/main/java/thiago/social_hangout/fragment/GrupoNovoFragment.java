package thiago.social_hangout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thiago.social_hangout.R;
import thiago.social_hangout.activity.GrupoActivity;
import thiago.social_hangout.activity.GruposActivity;

/**
 * Created by Cliente on 31/08/2017.
 */

public class GrupoNovoFragment extends BaseFragment {

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


        return view;
    }
}
