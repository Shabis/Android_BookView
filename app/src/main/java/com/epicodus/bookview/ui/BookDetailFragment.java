package com.epicodus.bookview.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailFragment extends Fragment {
    @Bind(R.id.bookImageView) ImageView mImageLabel;
    @Bind(R.id.bookTitleTextView) TextView mTitleLabel;
    @Bind(R.id.bookAuthorTextView) TextView mAuthorLabel;
    @Bind(R.id.bookDescriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.bookRatingTextView) TextView mRatingLabel;
    @Bind(R.id.bookRatingCountTextView) TextView mRatingCountLabel;

    private Book mBook;

    public BookDetailFragment newInstance(Book book) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("book", Parcels.wrap(book));
        bookDetailFragment.setArguments(args);
        return bookDetailFragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mBook = Parcels.unwrap(getArguments().getParcelable("book"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail,container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mBook.getImageUrl()).into(mImageLabel);

        mTitleLabel.setText(mBook.getTitle());
        mAuthorLabel.setText(android.text.TextUtils.join(", ", mBook.getAuthors()));
        mDescriptionLabel.setText(mBook.getDescription());
        mRatingLabel.setText(Double.toString(mBook.getAverageRating()));
        mRatingCountLabel.setText(mBook.getRatingCount());
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);

        return view;
    }

}
