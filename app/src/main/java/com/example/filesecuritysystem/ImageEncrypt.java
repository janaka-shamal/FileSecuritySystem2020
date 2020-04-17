package com.example.filesecuritysystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.filesecuritysystem.Utils.Encryptor;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class ImageEncrypt extends AppCompatActivity {
    private static final String FILE_NAME_DEC ="jnk.png" ;
    Button btn_enc,btn_dec;
    ImageView imageView;
    Bitmap bitmap;
    InputStream encInputStream;
    File myDir;
    private static final String FILE_NAME_ENC="jnk";
    String my_key="jdwztahttruvphdm";
    String my_spec_key="risxjdoxqfhatuph";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_encrypt);

        btn_enc=(Button)findViewById(R.id.btn_encrypt);
        btn_dec=(Button)findViewById(R.id.btn_decrypt);
        imageView = (ImageView)findViewById(R.id.imageView);
        myDir=new File(Environment.getExternalStorageDirectory().toString());
        Dexter.withActivity(this)
                .withPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                })
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        btn_dec.setEnabled(true);
                        btn_enc.setEnabled(true);
                        btn_enc.setEnabled(true);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Toast.makeText(ImageEncrypt.this,"You must enable permission",Toast.LENGTH_SHORT).show();

                    }
                }).check();

        //encryption
        btn_enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bitmap convertion
                //Drawable drawable = ContextCompat.getDrawable(MainActivity.this,R.drawable.janaka);
                //BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
                //Bitmap bitmap1=bitmapDrawable.getBitmap();

                if(bitmap!=null){
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                InputStream is= new ByteArrayInputStream(stream.toByteArray());

                //
                File outputFileEnc=new File(myDir,FILE_NAME_ENC);

                try {
                    Encryptor.encryptToFile(my_key,my_spec_key,is,new FileOutputStream(outputFileEnc));
                    Toast.makeText(ImageEncrypt.this,"Ecrypted!!",Toast.LENGTH_SHORT).show();
                    btn_dec.setEnabled(true);
                    bitmap=null;
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
                else{
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"Pick an Image"),1);
                    Toast.makeText(ImageEncrypt.this,"Select an Image to Encrypt!",Toast.LENGTH_SHORT).show();

                }

            }
        });
        //decryption
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputFileDec = new File(myDir,FILE_NAME_DEC);
                //File encFile=new File(myDir,FILE_NAME_ENC);
                if(encInputStream!=null){
                try{
                    Encryptor.decryptToFile(my_key,my_spec_key,encInputStream,new FileOutputStream(outputFileDec));
                    imageView.setImageURI(Uri.fromFile(outputFileDec));
                    //deletion of the file
                    //outputFileDec.delete();
                    Toast.makeText(ImageEncrypt.this, "Decrypted", Toast.LENGTH_SHORT).show();
                    btn_enc.setEnabled(true);
                    encInputStream=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
            }else{
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/*");
                    startActivityForResult(Intent.createChooser(intent,"Pick an Encrypted Image"),2);
                    Toast.makeText(ImageEncrypt.this,"Select a Encrypted Image",Toast.LENGTH_SHORT).show();
            }
            }
        });


    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                btn_dec.setEnabled(false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            try {
                encInputStream = getContentResolver().openInputStream(data.getData());
                btn_enc.setEnabled(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
