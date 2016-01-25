package com.example.fxb.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by FXB on 2015/7/18.
 */
public class WriteNoteActivity extends Activity{
    private  EditText textView;
    private ImageView backImage;
    private TextView noteTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_note);
        noteTitle=(TextView)findViewById(R.id.writeNote_title);
        textView=(EditText)findViewById(R.id.note_text);
        backImage=(ImageView)findViewById(R.id.left_tip);
        Bundle intent=getIntent().getExtras();
        if(intent.getString("key").equals("update")){
            noteTitle.setText("查看便签");
            textView.setText(intent.getString("text"));
            textView.setSelection(intent.getString("text").length());

        }

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=WriteNoteActivity.this.getIntent();

                WriteNoteActivity.this.setResult(125, intent);
                WriteNoteActivity.this.finish();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            System.out.println("-----------------------+onKeyDown");
            Intent intent=WriteNoteActivity.this.getIntent();
            Bundle bundle=intent.getExtras();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            bundle.putString("text", textView.getText().toString());
            bundle.putString("date", format.format(new Date()));
            intent.putExtras(bundle);
            if(bundle.getString("key").equals("insert")){
                textView.setText("");
                WriteNoteActivity.this.setResult(123, intent);
            }else {


                WriteNoteActivity.this.setResult(124, intent);
                noteTitle.setText("写便签");
            }

            WriteNoteActivity.this.finish();
        }
        return false;
    }

    //    protected void onDestroy() {
//
//        Intent intent=new Intent();
//        Bundle bundle=new Bundle();
//        bundle.putString("biji",textView.getText().toString());
//        System.out.println("---------------------------======"+RESULT_OK);
//        setResult(RESULT_OK, intent);
//        finish();
//    }
}
