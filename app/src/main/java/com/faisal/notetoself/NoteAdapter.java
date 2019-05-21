package com.faisal.notetoself;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import org.w3c.dom.Text;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ListItemHolder> {

    private List<Note> mShoppingItemList;
    private MainActivity mMainActivity;

    public NoteAdapter(MainActivity mainActivity, List<Note> shoppingItemList) {
        mMainActivity = mainActivity;
        mShoppingItemList = shoppingItemList;
    }

    @NonNull
    @Override
    public NoteAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ListItemHolder holder, int position) {
        Note shoppingItem = mShoppingItemList.get(position);
        holder.mTitle.setText(shoppingItem.getTitle());

        // Show the first 15 characters of the actual shoppingItem
        // Unless a short shoppingItem then show half
        if (shoppingItem.getDescription().length() > 15) {
            holder.mDescription.setText(shoppingItem.getDescription().substring(0, 15));
        }
        else {
            holder.mDescription.setText(shoppingItem.getDescription().substring(0, shoppingItem.getDescription().length() / 2));
        }

        // What is the status of the shoppingItem?
        if (shoppingItem.isIdea()) {
            holder.mStatus.setText(R.string.idea_text);
        }
        else if (shoppingItem.isImportant()) {
            holder.mStatus.setText(R.string.important_text);
        }
        else if (shoppingItem.isTodo()) {
            holder.mStatus.setText(R.string.todo_text);
        }
    }

    @Override
    public int getItemCount() {
        return mShoppingItemList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        TextView mDescription;
        TextView mStatus;

        public ListItemHolder(View view) {
            super(view);

            mTitle = view.findViewById(R.id.textViewTitle);
            mDescription = view.findViewById(R.id.textViewDescription);
            mStatus = view.findViewById(R.id.textViewStatus);
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mMainActivity.showNote(getAdapterPosition());
        }
    }
}
