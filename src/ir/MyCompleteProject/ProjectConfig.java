package ir.MyCompleteProject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;


public class ProjectConfig extends Application {

    public static Activity       CurrentActivity;
    public static Context        context;
    public static final String   LogTag                = "MyProject";

    public static final String   ImagesWebUrl          = "http://rahe-zendegi.ir/NewsImages/";

    public static final Handler  handler               = new Handler();

    public static LayoutInflater inflater;

    public static SQLiteDatabase database;
    public static final String   DIR_SDCARD            = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String   DIR_DATABASES         = DIR_SDCARD + "/MontaseriDatabases/";

    public static final String   DIR_AppFileDirectory  = DIR_SDCARD + "/MontaseriFiles/";
    public static final String   DIR_AppImageDirectory = DIR_SDCARD + "/MontaseriImages/";


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        context = getApplicationContext();
        //inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //new File(DIR_DATABASES).mkdirs();
        //new File(DIR_AppImageDirectory).mkdirs();

        //database = SQLiteDatabase.openOrCreateDatabase(DIR_DATABASES + "Montaseri.sqlite", null);

        /*        database.execSQL("CREATE  TABLE  IF NOT EXISTS TableNews (" +

                        "newsAouthorID  INTEGER NOT NULL," +
                        "newsAouthorLastName TEXT  NOT NULL ," +
                        "newsAouthorName TEXT  NOT NULL ," +
                        "newsArchieve  BOOL  NOT NULL ," +
                        "newsBranchName  TEXT NOT NULL ," +
                        "newsDate TEXT  NOT NULL ," +
                        "newsID  INTEGER NOT NULL ," +
                        "newsPassage  TEXT NOT NULL ," +
                        "newsPictureName TEXT  NOT NULL ," +
                        "newsRelatedLink TEXT  NOT NULL ," +
                        "newsShowingPermision  BOOL  NOT NULL ," +
                        "newsTitle TEXT NOT NULL ," +
                        "newsType  TEXT NOT NULL )");*/

        //"newsTitle TEXT PRIMARY KEY NOT NULL ," +

        //database.execSQL("insert into TableCourse(CourseID,CourseName) values(101,'مهندسی نرم افزار')");

        //database.execSQL("delete from ......
        //database.execSQL("update .....

        /*        Cursor cursor = database.rawQuery("select * from  TableNews", null);

                while (cursor.moveToNext())
                {
                    String newsPassage = cursor.getString(cursor.getColumnIndex("newsPassage"));
                    String newsTitle = cursor.getString(cursor.getColumnIndex("newsTitle"));

                    Log.i(LogTag, "title=" + newsTitle + " passage=" + newsPassage);
                }
                cursor.close();*/
    }
}
