package com.epicodus.bookview.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.bookview.models.Book;
import com.epicodus.bookview.ui.BookDetailActivity;
import com.epicodus.bookview.util.ItemTouchHelperAdapter;
import com.epicodus.bookview.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Guest on 12/14/16.
 */
public class FirebaseBookListAdapter extends FirebaseRecyclerAdapter<Book, FirebaseBookViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Book> mBooks = new ArrayList<>();

    private void setIndexInFirebase() {
        for (Book book : mBooks) {
            int index = mBooks.indexOf(book);
            DatabaseReference ref = getRef(index);
            book.setIndex(Integer.toString(index));
            ref.setValue(book);
        }
    }

    public FirebaseBookListAdapter(Class<Book> modelClass, int modelLayout, Class<FirebaseBookViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBooks.add(dataSnapshot.getValue(Book.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseBookViewHolder viewHolder, Book model, int position) {
        viewHolder.bindBook(model);
        viewHolder.mBookImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("books", Parcels.wrap(mBooks));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBooks, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mBooks.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
