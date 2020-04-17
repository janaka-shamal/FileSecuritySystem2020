package com.example.filesecuritysystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.filesecuritysystem.Utils.Encryptor;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

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

public class AudioEncrypt extends AppCompatActivity {
    private static final String FILE_NAME_DEC ="jnk.mp3" ;
    Button btn_enc,btn_dec,btn_pick;
    InputStream inputStream;

    File myDir;
    private static final String FILE_NAME_ENC="jnk";
    String my_key="jdwztahttruvphdm";
    String my_spec_key="risxjdoxqfhatuph";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_encrypt);
        btn_enc=(Button)findViewById(R.id.btn_encrypt);
        btn_dec=(Button)findViewById(R.id.btn_decrypt);
        btn_pick=(Button)findViewById(R.id.btn_pick);
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
                        Toast.makeText(AudioEncrypt.this,"You must enable permission",Toast.LENGTH_SHORT).show();
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

                InputStream is= inputStream;

                //
                File outputFileEnc=new File(myDir,FILE_NAME_ENC);
                if(inputStream!=null) {
                    try {
                        Encryptor.encryptToFile(my_key, my_spec_key, is, new FileOutputStream(outputFileEnc));
                        Toast.makeText(AudioEncrypt.this, "Ecrypted!!", Toast.LENGTH_SHORT).show();
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
                    }
                }else{
                    Toast.makeText(AudioEncrypt.this, "Select an Audio to Encrypt!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("audio/*");

                    startActivityForResult(Intent.createChooser(intent,"Pick an Audio"),1);

                }

            }
        });
        //decryption
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputFileDec = new File(myDir,FILE_NAME_DEC);
                File encFile=new File(myDir,FILE_NAME_ENC);
                try{
                    Encryptor.decryptToFile(my_key,my_spec_key,new FileInputStream(encFile),new FileOutputStream(outputFileDec));
                    //deletion of the file
                    //outputFileDec.delete();
                    Toast.makeText(AudioEncrypt.this, "Decrypted", Toast.LENGTH_SHORT).show();
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
            }
        });



    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                inputStream = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
