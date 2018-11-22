package com.example.vince.assignment4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class add_team extends AppCompatActivity {
    private byte[] imageData = null;
    private String filePath;
    private DisplayMetrics dm = new DisplayMetrics();
    private ImageView imageview;
    private String savableImage;
    private boolean customImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        imageview = findViewById(R.id.teamImageView);

        Bitmap bitmap = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image_not_found), dm.widthPixels / 2);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
        imageData = byteArrayOutputStream.toByteArray();
        imageview.setImageBitmap(BitmapFactory.decodeByteArray(imageData, 0, imageData.length));
        savableImage = Base64.encodeToString(imageData, 0);

        ArrayAdapter aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.sports);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.sportSpinner)).setAdapter(aa);

        findViewById(R.id.uploadImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(add_team.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
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
                if (customImage) {
                    Bitmap imageMap = getResizedBitmap(BitmapFactory.decodeFile(filePath), dm.widthPixels / 2);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    imageMap.compress(Bitmap.CompressFormat.PNG, 20, bos);
                    savableImage = Base64.encodeToString(bos.toByteArray(), 0);
                } else
                    savableImage = savableImage;

                if (city.getText().length() > 0 && name.getText().length() > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putParcelable("newTeam", new Team(
                            city.getText().toString(),
                            name.getText().toString(),
                            sport.getSelectedItem().toString(),
                            mvp.getText().toString(),
                            savableImage
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
            filePath = cursor.getString(cursor.getColumnIndex(filePaths[0]));
            cursor.close();
            imageview.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(filePath), dm.widthPixels / 2));
            customImage = true;
        } catch (Exception e) {

        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width;
        int height;
        float bitmapRatio = ((float) image.getWidth()) / ((float) image.getHeight());
        if (bitmapRatio > 1.0f) {
            width = maxSize;
            height = (int) (((float) width) / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (((float) height) * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}

