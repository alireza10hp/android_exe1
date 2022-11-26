package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Course> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Course> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView course_id;
        private TextView info;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            course_id = (TextView) itemView.findViewById(R.id.course_id);
            info = (TextView) itemView.findViewById(R.id.info);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_item, viewGroup, false);

        return new ItemViewHolder((layoutView));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        Course course = (Course) listRecyclerItem.get(i);

        itemViewHolder.name.setText(course.getName());
        itemViewHolder.course_id.setText(course.getCourse_id());
        itemViewHolder.info.setText(course.getInfo());

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView info;
        public TextView course_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            course_id = itemView.findViewById(R.id.course_id);

            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Log.d("ClickFromViewHolder", "Clicked");
            int position = this.getAdapterPosition();
            Course course = listRecyclerItem.get(position);
            String name = course.getName();
            String course_id = course.getCourse_id();
            String info = course.getInfo();

            //todo check overlap
            PopupMenu popupMenu = new PopupMenu(context, this.name);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    // Toast message on menu item clicked
                    Toast.makeText(context, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            // Showing the popup menu
            popupMenu.show();
            Toast.makeText(context, "The position is " + String.valueOf(position) +
                    " Name: " + name + ", info:" + info + ", course_id:" + course_id, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("info", info);
            intent.putExtra("course_id", course_id);

            context.startActivity(intent);

        }
    }
}