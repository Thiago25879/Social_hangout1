package thiago.social_hangout.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import thiago.social_hangout.R;
import thiago.social_hangout.activity.GrupoActivity;
import thiago.social_hangout.activity.GruposActivity;
import thiago.social_hangout.model.Grupo;
import thiago.social_hangout.service.GrupoServiceBD;

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

        editText_novo = (EditText) view.findViewById(R.id.editNome_card1_frnovogrupo);
        editText_novo.setText(grupo.nome);

        imageView_novo = (ImageView) view.findViewById(R.id.imv_card0_frdetalhegrupo);
        imageView_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 0);
            }
        });



        button_novo = (Button) view.findViewById(R.id.button_novogrupo);
        button_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.button_novogrupo){
                    if(editText_novo.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Digite um nome válido", Toast.LENGTH_SHORT).show();
                    }else {
                        grupo.nome = editText_novo.getText().toString();
                        new Task().execute();
                    }
                }
            }
        });
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // if(requestCode == getActivity().RESULT_OK){
            Log.d(TAG, data.toString());
            Uri arquivoUri = data.getData();
            Log.d(TAG, "URI do arquivo: " + arquivoUri);
            imageView_novo.setImageURI(arquivoUri);
            grupo.foto = arquivoUri.toString();
        //}
    }

    private class Task extends AsyncTask<Void, Void, Long>{

        @Override
        protected Long doInBackground(Void... voids) {
            return GrupoServiceBD.getInstance(getContext()).save(grupo);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if(aLong > 0){
                alertOk("Resultado da operação", "Realizado com sucesso.");
            }else{
                alertOk("Resultado da operação", "Erro ao realizar operação.");
            }
        }
    }
}
