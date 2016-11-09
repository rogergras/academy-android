package com.sea.academy.list.people;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sea.academy.list.R;
import com.sea.academy.list.base.OnPeopleClickListener;
import com.sea.academy.list.entity.People;
import com.sea.academy.list.util.Validator;

import java.util.ArrayList;
import java.util.List;


public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private OnPeopleClickListener onPeopleClickListener;
    private final List<People> data = new ArrayList<>();

    public PeopleAdapter() {
    }

    public PeopleAdapter(List<People> data) {

        Validator.checkNull(data);

        this.data.addAll(data);
    }

    public void update(List<People> data) {

        Validator.checkNull(data);

        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        return new PeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        final People people = data.get(position);
        holder.textView.setText(people.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPeopleClickListener.onPeopleClick(people);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public OnPeopleClickListener getOnPeopleClickListener() {
        return onPeopleClickListener;
    }

    public void setOnPeopleClickListener(OnPeopleClickListener onPeopleClickListener) {
        this.onPeopleClickListener = onPeopleClickListener;
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        PeopleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.info);
        }

    }

}
