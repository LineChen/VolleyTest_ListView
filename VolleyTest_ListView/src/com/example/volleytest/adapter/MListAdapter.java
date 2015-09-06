package com.example.volleytest.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.example.volleytest.R;
import com.example.volleytest.bean.Info;
import com.example.volleytest.cache.BitmapCache;

public class MListAdapter extends BaseAdapter {
	private Context ctx;
	private ArrayList<Info> infos;
	private RequestQueue mQueue;
	private ImageLoader mImageLoader;

	public MListAdapter(Context ctx, ArrayList<Info> infos) {
		this.ctx = ctx;
		this.infos = infos;
		mQueue = Volley.newRequestQueue(ctx);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());//
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Info getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null)
			view = convertView;
		else {
			ViewHolder holder = new ViewHolder();
			view = LayoutInflater.from(ctx).inflate(R.layout.item, null);
			holder.imageView = (ImageView) view.findViewById(R.id.item);
			view.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		ImageListener listener = mImageLoader.getImageListener(
				holder.imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
		mImageLoader.get(infos.get(position).getUrl(), listener);
		// convertView = LayoutInflater.from(ctx).inflate(R.layout.item, null);
		// ImageView imageView = (ImageView)
		// convertView.findViewById(R.id.item);
		// ImageListener listener = ImageLoader
		// .getImageListener(imageView, android.R.drawable.ic_menu_rotate,
		// android.R.drawable.ic_delete);
		// mImageLoader.get(getItem(position).getUrl(), listener);
		Log.i("--", "view : " + view);
		return view;
	}

	class ViewHolder {
		ImageView imageView;
	}

}
