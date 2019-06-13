package cat.dme.smart.marcopolo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;

public class TripRelativeAdapter extends RecyclerView.Adapter<TripRelativeAdapter.TripViewHolder> {

    private List<Trip> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private Long currentTripId;

    /**
     * Default constructor with required parameters.
     * @param context
     */
    public TripRelativeAdapter(Context context, Long currentTripId) {
        super();
        this.data = new ArrayList<>();
        this.context = context;
        this.currentTripId = currentTripId;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_trips, parent, false);
        return new TripViewHolder(itemView, (OnTripListener )this.context);
    }

    public void setData(List<Trip> newData) {
 /*       if (trip != null) {
            PostDiffCallback postDiffCallback = new PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {*/
            // first initialization
            this.data = newData;
            notifyDataSetChanged();
 /*       }*/
    }

    class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvFirstLine;
        private TextView tvSecondLine;

        private OnTripListener onTripListener;

        /**
         * Default constructor with required parameters.
         * @param itemView
         */
        TripViewHolder(View itemView, OnTripListener onTripListener) {
            super(itemView);
            this.tvFirstLine = itemView.findViewById(R.id.firstLine);
            this.tvSecondLine = itemView.findViewById(R.id.secondLine);
            this.onTripListener = onTripListener;
            itemView.setOnClickListener(this);
        }

        void bind(final Trip trip) {
            if (trip != null) {
                tvFirstLine.setText(trip.getDestination());
                tvSecondLine.setText(trip.getDescription());
            }
            if(currentTripId!=null && currentTripId.equals(trip.getId())) {
                itemView.setBackgroundColor(context.getColor(R.color.colorPrimaryMark));
            }

        }

        @Override
        public void onClick(View v) {
            this.onTripListener.onTripClick(this.getAdapterPosition());
        }
    }

    public interface OnTripListener {
        void onTripClick(int position);
    }
}



/*


    public void setData(List<Post> newData) {
        if (data != null) {
            PostDiffCallback postDiffCallback = new PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            // first initialization
            data = newData;
        }
    }



/*    class PostDiffCallback extends DiffUtil.Callback {

        private final List<Post> oldPosts, newPosts;

        public PostDiffCallback(List<Post> oldPosts, List<Post> newPosts) {
            this.oldPosts = oldPosts;
            this.newPosts = newPosts;
        }

        @Override
        public int getOldListSize() {
            return oldPosts.size();
        }

        @Override
        public int getNewListSize() {
            return newPosts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).getId() == newPosts.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }*/

