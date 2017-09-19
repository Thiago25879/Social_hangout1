package thiago.social_hangout.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import thiago.social_hangout.R;
import thiago.social_hangout.model.Grupo;

/**
 * Created by thiago on 12/09/17.
 */

public class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.GruposViewHolder> {
    protected static final String TAG = "GruposAdapter";
    private final List<Grupo> grupos;
    private final Context context;

    private GrupoOnClickListener grupoOnClickListener;

    public GruposAdapter(Context context, List<Grupo> grupos, GrupoOnClickListener grupoOnClickListener) {
        this.context = context;
        this.grupos = grupos;
        this.grupoOnClickListener = grupoOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.grupos != null ? this.grupos.size() : 0;
    }

    @Override
    public GruposViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_grupos, viewGroup, false);

        // Cria o ViewHolder
        GruposViewHolder holder = new GruposViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GruposViewHolder holder, final int position) {
        // Atualiza a view
        Grupo c = grupos.get(position);
        Log.d(TAG, "Grupo no Adapter da RecyclerView: " + c.toString());

        Log.d(TAG, c.toString());

        holder.tNome.setText(c.nome);
        holder.progress.setVisibility(View.VISIBLE);
        if(c.foto != null){
            holder.img.setImageURI(Uri.parse(c.foto));
        }else{
            holder.img.setImageResource(R.drawable.grupo_background);
        }

        // Click
        if (grupoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grupoOnClickListener.onClickGrupo(holder.itemView, position); // A variável position é final
                }
            });
        }

        holder.progress.setVisibility(View.INVISIBLE);
    }

    public interface GrupoOnClickListener {
        public void onClickGrupo(View view, int idx);
    }

    // ViewHolder com as views
    public static class GruposViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ProgressBar progress;

        public GruposViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.textView_card_adaptergrupo);
            img = (ImageView) view.findViewById(R.id.imageView_card_adaptergrupo);
            progress = (ProgressBar) view.findViewById(R.id.progressBar_card_adaptergrupo);
        }
    }
}
