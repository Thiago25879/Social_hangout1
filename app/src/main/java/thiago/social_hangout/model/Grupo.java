package thiago.social_hangout.model;

import java.io.Serializable;

/**
 * Created by thiago on 05/09/17.
 */

public class Grupo implements Serializable{
    private static final long serialVersionUID = 1L;

    public Long id;
    public String nome;
    public String foto;


    @Override
    public String toString() {
        return "Carro{"
                + "id='" + id + '\''
                + ", nome='" + nome + '\''
                + ", foto='" + foto + '\'';
    }
}
