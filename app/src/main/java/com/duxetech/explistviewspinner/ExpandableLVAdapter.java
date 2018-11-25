package com.duxetech.explistviewspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Karthik Swamy on 22/11/18.
 */

class ExpandableLVAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> list;
    HashMap<String, ArrayList<String>> hm;

    public ExpandableLVAdapter(Context context, List<String> list, HashMap<String, ArrayList<String>> hm) {
        this.context = context;
        this.list = list;
        this.hm = hm;
    }

    @Override
    public int getGroupCount() {
        return list.size()-1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hm.get(list.get(groupPosition+1)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inf.inflate(R.layout.titles,parent,false);
        TextView tv = view.findViewById(R.id.listTitle);
        tv.setText(list.get(groupPosition+1));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view  = inf.inflate(R.layout.items,parent,false);
        TextView tv = view.findViewById(R.id.list_content);
        tv.setText(hm.get(list.get(groupPosition+1)).get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
