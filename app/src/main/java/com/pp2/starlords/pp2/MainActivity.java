package com.pp2.starlords.pp2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainViewFragment())
                    .commit();
        }


        List<Position> posOld = FileLogger.parseFile("readingsOldAPI.log", getApplicationContext());
        List<Position> posNew = FileLogger.parseFile("readingsNewAPI.log", getApplicationContext());

        System.out.println("---------------------------------------------- OLD ");
        for (Position p : posOld) {
            System.out.println(p.getLattitude() + ", " + p.getLongitude());
        }

        System.out.println("---------------------------------------------- NEW ");
        for (Position p : posNew) {
            System.out.println(p.getLattitude() + ", " + p.getLongitude());
        }
    }




    public void runOldAPI(View view) {
        Intent intent = new Intent(this, OldAPIActivity.class);
        startActivity(intent);
    }

    public void runNewAPI(View view) {
        Intent intent = new Intent(this, NewAPIActivity.class);
        startActivity(intent);
    }

    public void runCDFActivity(View view) {
        Intent intent = new Intent(this, CdfActivity.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MainViewFragment extends Fragment {

        public MainViewFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
