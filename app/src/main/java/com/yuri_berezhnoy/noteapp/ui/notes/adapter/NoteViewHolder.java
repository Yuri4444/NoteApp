package com.yuri_berezhnoy.noteapp.ui.notes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuri_berezhnoy.noteapp.databinding.ItemNoteBinding;
import com.yuri_berezhnoy.noteapp.databinding.MenuMyQuotePopupBinding;
import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final ItemNoteBinding binding;

    public NoteViewHolder(@NonNull ItemNoteBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Note item,
              int position,
              NoteAdapter.OnEditClickListener onEditClickListener,
              NoteAdapter.OnDeleteClickListener onDeleteClickListener
    ) {
        binding.tvContent.setText(item.content);
        binding.ivMenu.setOnClickListener(v -> showPopup(
                position,
                binding.ivMenu,
                binding.getRoot().getWidth(),
                item.id,
                onEditClickListener,
                onDeleteClickListener
        ));
    }

    public void showPopup(int position, ImageView ivMenu, int parentWidth, int id,
                          NoteAdapter.OnEditClickListener onEditClick,
                          NoteAdapter.OnDeleteClickListener onDeleteClick) {
        int[] loc = new int[2];
        ivMenu.getLocationOnScreen(loc);
        Point point = new Point(loc[0], loc[1]);

        LayoutInflater layoutInflater = (LayoutInflater) ivMenu.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MenuMyQuotePopupBinding menuBinding = MenuMyQuotePopupBinding.inflate(layoutInflater);
        PopupWindow popup = new PopupWindow(ivMenu.getContext());
        int width = dpToPx(ivMenu.getContext());
        popup.setContentView(menuBinding.getRoot());
        popup.setFocusable(true);
        popup.setWidth(width);
        int xPosition = parentWidth - width;
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        menuBinding.tvEdit.setOnClickListener(view -> {
            onEditClick.onEditClick(id, position);
            popup.dismiss();
        });
        menuBinding.tvDelete.setOnClickListener(view -> {
            onDeleteClick.onDeleteClick(id, position);
            popup.dismiss();
        });

        popup.showAtLocation(menuBinding.getRoot(), Gravity.NO_GRAVITY,
                xPosition, point.y + ivMenu.getHeight());
    }

    private int dpToPx(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(234 * density);
    }
}
