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

import thiago.social_hangout.R;
import thiago.social_hangout.activity.GrupoActivity;
import thiago.social_hangout.model.Grupo;
import thiago.social_hangout.service.GrupoServiceBD;

/**
 * Created by thiago on 17/09/17.
 */

public class GrupoEdicaoFragment extends BaseFragment {
    private Grupo grupo;
    private final String SAVE = "save";
    private final String DELETE = "delete";
    private GrupoServiceBD grupoServiceBD;

    private ProgressBar progressBar;
    private EditText editText;
    private ImageView imageView;
    private Button button1;


    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        ((GrupoActivity) getActivity()).getSupportActionBar().setTitle("Editar do Grupo");

        grupoServiceBD = GrupoServiceBD.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novogrupo, container, false);
        editText = (EditText) view.findViewById(R.id.editNome_card1_frnovogrupo);
        editText.setText(grupo.nome);
        imageView = (ImageView) view.findViewById(R.id.imv_card0_frdetalhegrupo);
        if(grupo.foto != null){
            imageView.setImageURI(Uri.parse(grupo.foto));
        }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 0);
                }
            });

        button1 = (Button) view.findViewById(R.id.button_novogrupo);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.button_novogrupo){
                    grupo.nome = editText.getText().toString();
                    new Task().execute(SAVE);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK){
            Uri arquivoUri = data.getData();
            if(arquivoUri.toString().contains("images")){
                imageView.setImageURI(arquivoUri);
                grupo.foto = arquivoUri.toString();
            }
        }
    }

    private class Task extends AsyncTask<String, Void, Long>{

        /*@Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertWait("Aguarde", "Processando.....");
        }*/

        @Override
        protected Long doInBackground(String... strings) {
            if (strings[0].equals(SAVE)) {
                return grupoServiceBD.save(grupo);
            }else if(strings[0].equals(DELETE)){
                return grupoServiceBD.delete(grupo);
            }
            return 0L;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if(aLong > 0){
                alertOk("Resultado da operação", "Realizado com sucesso.");
            }else{
                //faz aparecer uma caixa de diálogo confirmando problemas na operação
                alertOk("Resultado da operação", "Erro ao realizar operação.");
            }
        }
    }
}
