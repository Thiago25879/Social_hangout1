package thiago.social_hangout.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import thiago.social_hangout.R;
import thiago.social_hangout.activity.GrupoActivity;
import thiago.social_hangout.activity.GruposActivity;
import thiago.social_hangout.adapter.GruposAdapter;
import thiago.social_hangout.model.Grupo;
import thiago.social_hangout.service.GrupoServiceBD;

/**
 * Created by thiago on 30/08/17.
 */

public class GruposFragment extends BaseFragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Grupo> grupos;
    private GrupoServiceBD grupoServiceBD;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        ((GruposActivity) getActivity()).getSupportActionBar().setTitle("Grupos");

        grupoServiceBD = GrupoServiceBD.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grupos, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_fragmentgrupos);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        new Task().execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_grupos, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menuitem_pesquisar).getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Nome do grupo");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Grupo> grupoList = new ArrayList<>();

        for(Grupo grupo : grupos){
            if(grupo.nome.toLowerCase().contains(newText.toLowerCase())){
                grupoList.add(grupo);
            }
        }
        recyclerView.setAdapter(new GruposAdapter(getContext(), grupoList, onClickGrupo()));

        return true;
    }


    protected GruposAdapter.GrupoOnClickListener onClickGrupo() {
        return new GruposAdapter.GrupoOnClickListener() {
            @Override
            public void onClickGrupo(View view, int idx) {
                Grupo grupo = grupos.get(idx);
                Intent intent = new Intent(getContext(), GrupoActivity.class);
                intent.putExtra("grupo", grupo);
                intent.putExtra("qualFragmentAbrir", "GrupoDetalheFragment");
                startActivity(intent);
            }
        };
    }


    private class Task extends AsyncTask<Void, Void, List<Grupo>> {

        @Override
        protected List<Grupo> doInBackground(Void... voids) {
            return grupoServiceBD.getAll();
        }

        @Override
        protected void onPostExecute(List<Grupo> grupos) {
            super.onPostExecute(grupos);

            GruposFragment.this.grupos = grupos;
            recyclerView.setAdapter(new GruposAdapter(getContext(), grupos, onClickGrupo()));
        }
    }
}

