package com.materialApp.nativeandroid.app;

import android .support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder>{

    private ArrayList<InboxEntry> mDataSet;

    public InboxAdapter(ArrayList<InboxEntry> mDataSet){
        this.mDataSet = mDataSet;

    }

    public void add(int i, InboxEntry newInboxEntry){
        mDataSet.add(i,newInboxEntry);
        notifyItemChanged(i);
    }

    public int getItemCount(){
        return mDataSet.size();
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mUser.setText(mDataSet.get(position).userName);
        holder.mUserStatus.setText(mDataSet.get(position).userState);
        holder.mUserLastMessage.setText(mDataSet.get(position).lastMessage);
    }

    public InboxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mUser;
        public TextView mUserStatus;
        public TextView mUserLastMessage;
        public TextView mUserDate;
        public TextView mUserAvatar;

        public ViewHolder(View v){
            super(v);
            mUser = (TextView) v.findViewById(R.id.recy_user);
            mUserStatus = (TextView) v.findViewById(R.id.recy_userStatus);
            mUserLastMessage = (TextView) v.findViewById(R.id.recy_userLast);
            //mUserDate = (TextView) v.findViewById();
            //mUserAvatar = (TextView) v.findViewById();

        }



    }

}
