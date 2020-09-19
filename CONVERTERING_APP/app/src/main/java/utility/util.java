package utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class util {

    public static boolean saveImageToSDCard(Bitmap image, String folder, String name) {
        String fullPath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+folder+"/";

        try {
            File dir=new File(fullPath);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            OutputStream fout=null;
            File file=new File(fullPath, name);
            if(!file.exists()) {
                file.createNewFile();
            }
            fout=new FileOutputStream(file);

            image.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.flush();
            fout.close();

            return true;

        } catch (Exception e) {
            Log.e("Error!!", e.getMessage());
            return  false;
        }



    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-đd HH:mm:ss");
        String dateTime=dateFormat.format(new Date());
        return dateTime;
    }

    public static boolean isSDable() {
        boolean mExter=false;
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            mExter=true;
            Log.i("isSDReadablie", "External storage card is readable.");
        }
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Log.i("isSDReadablie", "External storage card is readable.");
            mExter=true;
        }
        else {
            mExter=false;
        }

        return  mExter;
    }

    public static Date convertStringToDate(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-đd HH:mm:ss");
        try {
            Date date=dateFormat.parse(dateTime);
            return  date;
        } catch (Exception e) {
            return null;
        }
    }

    public static void setBitmapToImage(final Context context, final String folder, final String name, final ImageView imageView) {
        final Handler handler=new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Bitmap bitmap= (Bitmap) msg.obj;
                if(bitmap!=null) {
                    imageView.setImageBitmap(bitmap);
                }
                else  {
                    imageView.setVisibility(View.GONE);
                }
            }
        };

        try {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = util.readImage(folder, name, context);
                    Message mag=new Message();
                    mag.obj=bitmap;
                    handler.sendMessage(mag);
                }
            });
            thread.start();
        } catch (Exception e) {

        }
    }

    public static Bitmap readImage(String folder, String filename, Context context) {
        Bitmap img=null;
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+folder+"/"+filename;
        try {
            img= BitmapFactory.decodeFile(fullPath);
        } catch (Exception e) {
            Log.i("DemoReadWriteImage", "Cant read image from SDCard");
        }

        try {
            File myFile=context.getFileStreamPath(filename);
            FileInputStream fln = new FileInputStream(myFile);
            img=BitmapFactory.decodeStream(fln);
        } catch (Exception e) {
            Log.i("DemoReadWriteImage", "Cant read image from internal memory");
        }
        return  img;
    }

    public static String convertStringDateTimeToFileName(String date) {
        return date.toString().replace(":", "").replace(" ", "").replace("-", "");
    }

}
