package ir.MyCompleteProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;


public abstract class ProjectActivityEnhanced extends Activity {

    public static final int menueHome            = 3;
    public static final int menuePayam           = 2;
    public static final int menueDownloadFromWeb = 4;
    public static final int menueArchive         = 1;
    public static final int menueExit            = 5;

    private Cursor          cursor;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // if ( !(this instanceof ActionBarMainActivity))
        menu.add(0, menuePayam, menuePayam, "ActionBar").setIcon(R.drawable.ic_action_help);

        //if ( !(this instanceof SlidingMenuMainActivity))
        menu.add(0, menueArchive, menuePayam, "Sliding").setIcon(R.drawable.ic_communities);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case menuePayam: {
                //Toast.makeText(ProjectConfig.context, "Menue " + item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProjectConfig.CurrentActivity, ActionBarMainActivity.class);
                ProjectConfig.CurrentActivity.startActivity(intent);
                break;
            }

            case menueArchive: {
                //Toast.makeText(ProjectConfig.context, "Menue " + item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProjectConfig.CurrentActivity, SlidingMenuMainActivity.class);
                ProjectConfig.CurrentActivity.startActivity(intent);
                break;
            }

            default: {
                //Toast.makeText(ProjectConfig.context, "Menue " + item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////

    public void showAlertDialog(Context context, String title, String message, String icon) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        if (icon == "success")
            alertDialog.setIcon(R.drawable.ic_action_help);

        if (icon == "fail")
            alertDialog.setIcon(R.drawable.ic_checkin);

        if (icon == "internet")
            alertDialog.setIcon(R.drawable.ic_latitude);

        // Setting OK Button
        alertDialog.setButton("تایید", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Showing Alert Message
        alertDialog.show();
    }


    /// activity cycle ///
    @Override
    protected void onResume() {

        ProjectConfig.context = this;
        ProjectConfig.CurrentActivity = this;

        super.onResume();
    }


    @Override
    protected void onPause() {

        super.onPause();
    }


    @Override
    protected void onStart() {

        super.onStart();
    }


    @Override
    protected void onStop() {

        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onRestart() {

        super.onRestart();
    }


    @Override
    protected void onPostResume() {

        super.onPostResume();
    }

}
