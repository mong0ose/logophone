package com.logophone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;
import java.util.Arrays;


/**
 * Created by mongOose on 03.03.14.
 */
public class GridSavedContactsViewActivity extends Activity {
    private Context mContext;
    private Point p;
    DisplayImageOptions opts;
    String[] imageUrls;
    String[] files;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_saved_contacts);

        mContext = this;
        p = new Point();

        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);

        p.x = p.x/3;
        p.y = p.x*1528/1080;
//        p.x *= 0.35;
//        p.y *= 0.35;

        File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/LogophoneContacts/");
        files = folder.list();
        System.out.println(Uri.fromFile(folder).toString());
        System.out.println(Arrays.toString(files));

        imageUrls = new String[folder.list().length];

        for(int i = 0; i < folder.list().length; i++)
            imageUrls[i] = "file://" + Environment.getExternalStorageDirectory().getPath() + "/LogophoneContacts/" + files[i];


        opts = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnFail(android.R.drawable.ic_delete)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);

        GridView gridView = (GridView) findViewById(R.id.gridViewContacts);
        gridView.setAdapter(new ImageAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startImagePagerActivity(position);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
    }

    private void startImagePagerActivity(int position) {
        Intent intent = new Intent(this, PagerSavedContactsViewActivity.class);
        intent.putExtra("com.logophone.pagerimages", imageUrls);
        intent.putExtra("com.logophone.pagerpos", position);
        startActivity(intent);
    }

    public class ImageAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.grid_item_contact, parent, false);
                holder = new ViewHolder();
                assert view != null;
                holder.imageView = (ImageView) view.findViewById(R.id.imgItemPhoneLogo);
                holder.imageView.setMinimumHeight(p.y);
                holder.imageView.setMaxHeight(p.y);
                holder.imageView.setMinimumWidth(p.x);
                holder.imageView.setMaxWidth(p.x);
                holder.textView = (TextView) view.findViewById(R.id.txtItemPhoneNumber);
                holder.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            String splitedStr[] = files[position].split("_");
//            holder.textView.setText(files[position].replaceAll("[^0-9]+", ""));
            holder.textView.setText(splitedStr[1] + "\n" + splitedStr[2].replaceAll(".jpg", "") + "\n" + splitedStr[0]);

            ImageLoader.getInstance().displayImage(imageUrls[position], holder.imageView, opts, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            holder.progressBar.setProgress(0);
                            holder.progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view,
                                                    FailReason failReason) {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current,
                                                     int total) {
                            holder.progressBar.setProgress(Math.round(100.0f * current / total));
                        }
                    }
            );

            return view;
        }

        class ViewHolder {
            ImageView imageView;
            ProgressBar progressBar;
            TextView textView;
        }
    }
}
