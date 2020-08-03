package com.example.build_jgy.ctos12;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.build_jgy.ctos12.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryFragment extends Fragment {
    List<Student> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
//        slideshowBeenList = bundle.getParcelableArrayList("SlideshowBean");
        View view=inflater.inflate(R.layout.result,container,false);
        list = (List<Student>)bundle.getSerializable("data1");
        if(list.size()!=0){
            List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
            for(int i = 0 ; i < list.size();i++){
                Map<String,Object> listItem = new HashMap<String,Object>();
                listItem.put("name",list.get(i).getStudent_name());
                listItem.put("exam",list.get(i).getFinal_grades());
                listItems.add(listItem);

            }
            SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(),listItems,R.layout.record_items,
                    new String[] {"name","exam"},
                    new int[]{R.id.student_name,R.id.record});
            ListView listView=view.findViewById(R.id.mylist);
            listView.setAdapter(simpleAdapter);
        }else{
            TextView textView=view.findViewById(R.id.title);
            textView.setText("没有这个信息！");
        }
        return  view;
    }
}
