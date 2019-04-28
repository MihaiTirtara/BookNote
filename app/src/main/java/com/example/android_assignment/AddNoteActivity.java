package com.example.android_assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class AddNoteActivity extends BaseActivity implements View.OnClickListener
{
    private Bitmap myBitmap;
    private ImageView myImageView;
    private TextView myTextView;
    private NoteHelper noteHelper;
    private Note note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.textView);
        myImageView = findViewById(R.id.imageView);
        findViewById(R.id.check_text).setOnClickListener(this);
        findViewById(R.id.add_text).setOnClickListener(this);

        note = new Note();
        noteHelper = new NoteHelper(AddNoteActivity.this);

    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.check_text:
                if (myBitmap != null) {
                    textRecog();
                }
                break;
            case R.id.add_text:
                postData();
                break;

        }
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case WRITE_STORAGE:
                    checkPermission(requestCode);
                    break;
                case SELECT_PHOTO:
                    Uri dataUri = data.getData();
                    String path = MyHelper.getPath(this,dataUri);
                    if(path==null)
                    {
                        myBitmap = MyHelper.resizePhoto(photo,this,dataUri,myImageView);
                    }
                    else
                    {
                        myBitmap = MyHelper.resizePhoto(photo,path,myImageView);
                    }
                    if(myBitmap!=null)
                    {
                        myTextView.setText(null);
                        myImageView.setImageBitmap(myBitmap);
                    }
                    break;

            }
        }
    }

    private void textRecog()
    {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(myBitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText text)
            {
                processExtractedText(text);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(AddNoteActivity.this,"Exception",Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void processExtractedText(FirebaseVisionText text)
    {
        myTextView.setText(null);
        if(text.getTextBlocks().size()==0)
        {
            myTextView.setText(R.string.no_text);
            return;
        }
        for(FirebaseVisionText.TextBlock block : text.getTextBlocks())
        {
            myTextView.append(block.getText());
        }
    }

    private void postData()
    {
        note.setText(myTextView.getText().toString().trim());
        noteHelper.addNote(note);
        System.out.println("HERE");

        Intent addNoteIntent = new Intent(AddNoteActivity.this,NotesActivity.class);
        System.out.println("HERE");
        Toast.makeText(this,"Note added succesfully",Toast.LENGTH_SHORT).show();
        System.out.println("HERE");
        addNoteIntent.putExtra("TEXT",myTextView.getText().toString().trim());
        startActivity(addNoteIntent);
        System.out.println("HERE");



    }

}
