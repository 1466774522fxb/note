package com.example.fxb.note;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.fxb.note.http.AsyncHttpResponseHandler;
import com.example.fxb.note.http.RequestParams;
import com.example.fxb.note.http.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ImageView addImage;
    private ListView listView;
    private Database database;
    static int flag=0;
    boolean banduan=true;
    ArrayList<Note> notes=new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addImage=(ImageView)findViewById(R.id.note_addimage);
        listView=(ListView)findViewById(R.id.main_list );

        database=new Database(this,"myNote.db3", 1);
        DisplayMetrics dm = new DisplayMetrics();
        //��ȡ��Ļ��Ϣ
        View root=this.getLayoutInflater().inflate(R.layout.popup, null);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;
        final PopupWindow popup=new PopupWindow(root,screenWidth,screenHeigh/2-180);
        popup.setFocusable(true);

        popup.setBackgroundDrawable(new BitmapDrawable());

        (root.findViewById(R.id.delete_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Note> notes=selectData(database.getReadableDatabase());
                deleteData(database.getReadableDatabase(), notes.get(flag).getTime());
                notes.remove(flag);
                listView.setAdapter(new NoteAdapter(MainActivity.this,notes));
                popup.dismiss();
            }
        });
        (root.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        NoteAdapter noteAdpter=new NoteAdapter(this, selectData(database.getReadableDatabase()));
        listView.setAdapter(noteAdpter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent =new Intent(MainActivity.this,WriteNoteActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("key", "update");
                ArrayList<Note> notes=selectData(database.getReadableDatabase());
                bundle.putString("time", notes.get(position).getTime());
                bundle.putString("text",notes.get(position).getText());
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                popup.showAsDropDown(findViewById(R.id.bottom_line));
                popup.showAtLocation(findViewById(R.id.bottom_line),  Gravity.BOTTOM, 0,0);
                flag=position;
                return false;
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WriteNoteActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("key","insert");
                intent.putExtras(bundle);

               // onTest();
                startActivityForResult(intent,0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle=data.getExtras();
        if(resultCode==123){
            insertData(database.getReadableDatabase(),bundle.getString("date"), bundle.getString("text"));
        }else if(resultCode==124){
            updateData(database.getReadableDatabase(),bundle.getString("time"), bundle.getString("text"));
        }

       listView.setAdapter(new NoteAdapter(this,selectData(database.getReadableDatabase())) );
    }

    private void deleteData(SQLiteDatabase db,String time){
        db.execSQL("delete from note where time='" + time + "'");
    }
    private void insertData(SQLiteDatabase db,String time,String text){
        db.execSQL("insert into note values(null,?,?)",new String[]{time,text});

    }
    private  void updateData(SQLiteDatabase db,String time,String text){
        db.execSQL("update note set text='" + text + "'where time='"+time+"'");

    }
    private ArrayList<Note> selectData(SQLiteDatabase db){
        Cursor cursor=db.rawQuery("select * from note order by time desc;", null);
        notes.removeAll(notes);
        Note note;
        while(cursor.moveToNext()){
            note=new Note();
            note.setTime(cursor.getString(1));
            note.setText(cursor.getString(2));
            notes.add(note);
        }

        return notes;
    }
    public void onTest(){
        System.out.println("-----------------1--code----------------");
        RequestParams params=new RequestParams();

        params.put("client_id",1100166);
        params.put("redirect_uri","www.baiud.com");
        System.out.println("-------------------code----------------");
        params.put("response_type", "code");

        RestClient.get("http://api.csdn.net/oauth2/authorize",params,
                new AsyncHttpResponseHandler(getBaseContext(),new JsonHttpResponseHandler(){
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        System.out.println("-------------------start----------------");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            System.out.println("-------------------json----"+response.getString("code"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }


                }));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
