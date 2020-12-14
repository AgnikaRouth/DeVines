package devines.com.devines20;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber; //to keep a track of number of tabs in the layout
    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) { //get position of Tabs starting from 0
        switch (position)
        {
            case 0 : return new DashboardFragment();

            case 1: return new ResultFragment();

            default:return  null;

        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}