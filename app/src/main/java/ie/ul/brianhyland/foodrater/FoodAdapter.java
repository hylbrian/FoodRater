package ie.ul.brianhyland.foodrater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_view, parent, false );
        return new FoodViewHolder(itemView);
    }

    private List<Food> mFoods = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    public void addFood(){
        mFoods.add(0, new Food());
        notifyItemInserted(0);
        notifyItemChanged(0,mFoods.size());
        mRecyclerView.getLayoutManager().scrollToPosition(0);


    }

    private void deleteFood(int position){
        mFoods.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0,mFoods.size());
    }


    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int position) {
        final Food food = mFoods.get(position);
        foodViewHolder.mName.setText(food.getName());
        foodViewHolder.mImageView.setImageResource(food.getImageResourceId());
        foodViewHolder.mRatingBar.setRating(food.getRating());
        //TODO

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{


        private ImageView mImageView;
        private TextView mName;
        private RatingBar mRatingBar;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.food_pic);
            mName = itemView.findViewById(R.id.name);
            mRatingBar = itemView.findViewById(R.id.rating_bar);



            //Done together, update rating for
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (fromUser){
                        //Update this food's rating
                        Food currentFood = mFoods.get(getAdapterPosition());
                        currentFood.setRating(rating);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    deleteFood(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
