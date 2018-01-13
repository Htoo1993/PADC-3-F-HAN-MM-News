package xyz.htooaungnaing.news.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.viewitems.ImageInNewsDetailsViewItem;

/**
 * Created by htoo on 12/10/2017.
 */

public class ImagesInNewsDetailsAdapter extends PagerAdapter {

    private List<String> mImages;

    public ImagesInNewsDetailsAdapter() {
        mImages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
//        if(object instanceof View)
//            return true;
//        else
//            return false;

//        return (object instanceof View);

        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ImageInNewsDetailsViewItem view = (ImageInNewsDetailsViewItem) layoutInflater.inflate(R.layout.item_news_details_images, container, false);
        container.addView(view);
        view.setData(mImages.get(position));

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
