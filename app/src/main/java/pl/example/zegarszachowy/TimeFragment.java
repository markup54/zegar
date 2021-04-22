package pl.example.zegarszachowy;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class TimeFragment extends Fragment {
    public int seconds;
    public boolean dzialaten;
    public TimeViewModel tenVM;
    public TimeViewModel wspVM;
    String time;
    Boolean dziala=false;

    public TimeFragment(){
        super(R.layout.fragment_timer);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wspVM = new ViewModelProvider(requireActivity()).get(TimeViewModel.class);
        tenVM = new ViewModelProvider(this).get(TimeViewModel.class);
        seconds=tenVM.licznikA;
        final Observer<Boolean> dzialaObserver =new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean aBoolean) {
                dziala=aBoolean;
            }
        };
        tenVM.getCzyDziala().observe(this,dzialaObserver);
    }

    private void runTimer(TextView timeView){

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int h=seconds/3600;
                int m=(seconds%3600)/60;
                int s=seconds%60;
                time=String.format("%d:%02d:%02d",h,m,s);
                timeView.setText(time);
                if(dziala) {
                    seconds++;
                    tenVM.licznikA = seconds;
                }
                handler.postDelayed(this,1000);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        TextView timeView = (TextView) view.findViewById(R.id.textView2);
        timeView.setText(Integer.toString(tenVM.licznikA));
        runTimer(timeView);
        Button start = (Button) view.findViewById(R.id.startButton) ;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dziala= !dziala;
                //tenVM.dzialaA=dziala;

                tenVM.setCzy_dziala(dziala);
            }
        });

        return view;
    }
}