package thiago.social_hangout.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import thiago.social_hangout.fragment.ConvitesFragment;
import thiago.social_hangout.fragment.EventosFragment;
import thiago.social_hangout.fragment.GruposFragment;

/**
 * Created by thiago on 30/08/17.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Bundle args = new Bundle();
        android.support.v4.app.Fragment f = null;

        switch (position){
            case 0:
                f = new ConvitesFragment();
                args.putString("tipo", "Convites");
                break;

            case 1:
                f = new GruposFragment();
                args.putString("tipo", "Grupos");
                break;

            case 2:
                f = new EventosFragment();
                args.putString("tipo", "Eventos");
                break;
        }

        f.setArguments(args);

        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Convites";
            case 1:
                return "Grupos";
            case 2:
                return "Eventos";
        }
        return null;
    }
}
