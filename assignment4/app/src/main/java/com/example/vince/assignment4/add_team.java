package com.example.vince.assignment4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class add_team extends AppCompatActivity {
    private byte[] imageData = null;
    private String imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        Log.d("inspect", "activity started");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_not_found);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
        imageData = byteArrayOutputStream.toByteArray();
        ((ImageView) findViewById(R.id.teamImageView)).setImageBitmap(BitmapFactory.decodeByteArray(imageData, 0, imageData.length));

        findViewById(R.id.uploadImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 100);
            }
        });


        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView city = findViewById(R.id.cityField);
                TextView name = findViewById(R.id.nameField);
                Spinner sport = findViewById(R.id.sportSpinner);
                TextView mvp = findViewById(R.id.mvpField);


                if (city.getText().length() > 0 && name.getText().length() > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putParcelable("newTeam", new Team(
                            city.getText().toString(),
                            name.getText().toString(),
                            sport.getSelectedItem().toString(),
                            mvp.getText().toString(),
                            imageString
                    ));

                    intent.putExtra("newTeam", bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "City and Name are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && (grantResults.length <= 0 || grantResults[0] != 0))
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            String[] filePaths = new String[]{"_data"};
            Cursor cursor = getContentResolver().query(data.getData(), filePaths, null, null, null);
            cursor.moveToFirst();
            imageString = cursor.getString(cursor.getColumnIndex(filePaths[0]));
            Log.d("inspect", imageString);
            cursor.close();
            //todo: image not setting after selection
            ((ImageView) findViewById(R.id.teamImageView)).setImageBitmap(BitmapFactory.decodeFile(imageString));
        } catch (Exception e) {

        }
    }
}

