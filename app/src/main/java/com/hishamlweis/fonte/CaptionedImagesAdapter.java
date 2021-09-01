package com.hishamlweis.fonte;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hishamlweis.fonte.ProductsActivities.ArabicBrownBreadActivity;
import com.hishamlweis.fonte.ProductsActivities.ArabicWhiteBreadActivity;
import com.hishamlweis.fonte.ProductsActivities.BrownWrapsActivity;
import com.hishamlweis.fonte.ProductsActivities.FrozenWrapsActivity;
import com.hishamlweis.fonte.ProductsActivities.HotdogBreadActivity;
import com.hishamlweis.fonte.ProductsActivities.InfoActivity;
import com.hishamlweis.fonte.ProductsActivities.MiniMilkBreadActivity;
import com.hishamlweis.fonte.ProductsActivities.SesameBurgerBunActivity;
import com.hishamlweis.fonte.ProductsActivities.SlicedBrownBreadActivity;
import com.hishamlweis.fonte.ProductsActivities.SlicedMilkBreadActivity;
import com.hishamlweis.fonte.ProductsActivities.SpicyWrapsActivity;
import com.hishamlweis.fonte.ProductsActivities.SpinachWrapsActivity;
import com.hishamlweis.fonte.ProductsActivities.SubSandwichSesameActivity;
import com.hishamlweis.fonte.ProductsActivities.TomatoWrapsActivity;
import com.hishamlweis.fonte.ProductsActivities.WhiteWrapsActivity;

import java.util.List;


public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    List<Items> itemsList;
    Context context;


    public CaptionedImagesAdapter(Context context, List<Items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Here we will find the position and start setting the output on our views

        String nameOfProduct = itemsList.get(position).getName();
        String descriptionOfProduct = itemsList.get(position).getDetail();
        int images = itemsList.get(position).getImageResourceId();

        holder.pName.setText(nameOfProduct);
        holder.pDescription.setText(descriptionOfProduct);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    // in order to make our views responsive we can implement onclick listener on our recycler view

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Here we will find the views on which we will inflate our data

        TextView pName, pDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            pName = itemView.findViewById(R.id.productName);
            pDescription = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.productImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent0 = new Intent(context, InfoActivity.class);
                context.startActivity(intent0);
            }

            if (position == 1) {
                Intent intent1 = new Intent(context, SlicedBrownBreadActivity.class);
                context.startActivity(intent1);
            }

            if (position == 2) {
                Intent intent2 = new Intent(context, SlicedMilkBreadActivity.class);
                context.startActivity(intent2);
            }

            if (position == 3) {
                Intent intent3 = new Intent(context, SesameBurgerBunActivity.class);
                context.startActivity(intent3);
            }

            if (position == 4) {
                Intent intent4 = new Intent(context, SubSandwichSesameActivity.class);
                context.startActivity(intent4);
            }

            if (position == 5) {
                Intent intent5 = new Intent(context, MiniMilkBreadActivity.class);
                context.startActivity(intent5);
            }

            if (position == 6) {
                Intent intent6 = new Intent(context, HotdogBreadActivity.class);
                context.startActivity(intent6);
            }

            if (position == 7) {
                Intent intent7 = new Intent(context, ArabicWhiteBreadActivity.class);
                context.startActivity(intent7);
            }

            if (position == 8) {
                Intent intent8 = new Intent(context, ArabicBrownBreadActivity.class);
                context.startActivity(intent8);
            }

            if (position == 9) {
                Intent intent9 = new Intent(context, WhiteWrapsActivity.class);
                context.startActivity(intent9);
            }

            if (position == 10) {
                Intent intent10 = new Intent(context, BrownWrapsActivity.class);
                context.startActivity(intent10);
            }

            if (position == 11) {
                Intent intent11 = new Intent(context, SpinachWrapsActivity.class);
                context.startActivity(intent11);
            }

            if (position == 12) {
                Intent intent12 = new Intent(context, SpicyWrapsActivity.class);
                context.startActivity(intent12);
            }

            if (position == 13) {
                Intent intent13 = new Intent(context, TomatoWrapsActivity.class);
                context.startActivity(intent13);
            }

            if (position == 14) {
                Intent intent14 = new Intent(context, FrozenWrapsActivity.class);
                context.startActivity(intent14);
            }

        }
    }
}

//    private String[] captions;
//    private int[] imageIds;
//
//
//    //old code
//    //Inner class to define a ViewHolder that holds a CardView object
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private CardView cardView;
//
//        public ViewHolder(CardView cv) {
//            super(cv);
//            cardView = cv;
//        }
//    }
//
//    //Constructor of this class needs two arrays for images and captions
//
//    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
//        this.captions = captions;
//        this.imageIds = imageIds;
//    }
//
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_captioned_image, viewGroup, false);
//        return new ViewHolder(cv);
//    }
//
//    //onBindViewHolder will be called repeatedly for each ViewHolder object
//
//    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        CardView cardView = viewHolder.cardView;
//        ImageView imageView = cardView.findViewById(R.id.info_image);
//        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
//        imageView.setImageDrawable(drawable);
//        imageView.setContentDescription(captions[position]);
//
//        TextView textView = cardView.findViewById(R.id.info_text);
//        textView.setText(captions[position]);
//    }
//
//    //how many items are we displaying?
//
//    public int getItemCount() {
//        return captions.length;
//    }