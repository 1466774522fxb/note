package com.example.fxb.note;

import java.util.ArrayList;




import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter{
	Activity context;
	ArrayList<Note> noteLists;
    public NoteAdapter(Activity context,ArrayList<Note> noteLists ){
    	this.context=context;
    	this.noteLists=noteLists;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return noteLists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return noteLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder view;
		if(convertView==null){
			view=new ViewHolder();
			convertView=context.getLayoutInflater().inflate(R.layout.line_item, null);
			view.timeView=(TextView)convertView.findViewById(R.id.time_view);
			view.textView=(TextView)convertView.findViewById(R.id.text_View);
           convertView.setTag(view);
			
		}else{
			view = (ViewHolder) convertView.getTag();
		}
		Note note=noteLists.get(position);
		view.timeView.setText(note.getTime());
		view.textView.setText(note.getText());
		return convertView;
	}
	final private class ViewHolder{
		TextView timeView;
		TextView textView;
		
	}
}
