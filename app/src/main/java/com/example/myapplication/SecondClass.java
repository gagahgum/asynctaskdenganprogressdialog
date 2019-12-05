package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SecondClass extends AsyncTask<Void, Integer, String> {
    Context ctx;
    ProgressDialog pd;

    public SecondClass (Context ct) {
        ctx = ct;
    }


    @Override
    protected String doInBackground(Void... voids) {
        try {
            for (int i = 1; i <= 10;i++) {
                Thread.sleep(3000);
                Log.i("Thread","Execute" +i);
                publishProgress(i);
            }
            return ("Well Done");
        }
        catch (Exception e) {
            Log.i("Exception", e.getMessage());
            return ("Failed");
        }
    }


    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(ctx,s, Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int myValue=values[0];
        pd.setProgress(myValue);
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(ctx);
        pd.setMessage("Downloading");
        pd.setTitle("Download");
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        pd.show();
    }
}


