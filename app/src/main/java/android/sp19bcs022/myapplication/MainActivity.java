package android.sp19bcs022.myapplication;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean[] power = {true};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersEventListener("_",10);
        numbersEventListener("operation",5);
        on_offEventListener(power);
        ((Button) findViewById(R.id.equal)).setOnClickListener(view -> {
           TextView calView = findViewById(R.id.calView);
            TextView ansView = findViewById(R.id.ansView);



            String expression = calView.getText().toString();
                if(expression.contains("+")) calculate(ansView, expression,"\\+");
                else if(expression.contains("-")) calculate(ansView, expression,"\\-");
                else if(expression.contains("*")) calculate(ansView, expression,"\\*");
                else if(expression.contains("/")) calculate(ansView, expression,"\\/");

           calView.setText("0");

        });


    }

    private void calculate(TextView ansView, String expression,String regex) {
        String[] parts = expression.split(regex);
        double part1 = Double.parseDouble(parts[0]);
        double part2 = Double.parseDouble(parts[1]);
        Log.i("part 1", Double.toString(part1) );
        switch (regex) {
            case "\\+":
                ansView.setText(Double.toString(part1 + part2));
                break;
            case "\\-":
                ansView.setText(Double.toString(part1 - part2));
                break;
            case "\\/":
                ansView.setText(Double.toString(part1 / part2));
                break;
            case "\\*":
                ansView.setText(Double.toString(part1 * part2));
                break;
            default:
               ansView.setText("0");
        }
    }

    private void on_offEventListener(boolean[] power) {
        findViewById(R.id.on_off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(power[0]) {
                    ((TextView) findViewById(R.id.calView)).setText("");
                    ((TextView) findViewById(R.id.ansView)).setText("");
                    power[0] =false;
                }else{
                    ((TextView) findViewById(R.id.calView)).setText("0");
                    ((TextView) findViewById(R.id.ansView)).setText("");
                    power[0] =true;
                }
            }
        });
    }


    private void numbersEventListener(String startText, int totalBtn) {
        ArrayList<Button> allButtons = new ArrayList<Button>();
        for (int i = 0; i < totalBtn; i++) {
            int id = getResources().getIdentifier(startText+i, "id", getPackageName());
            int finalI = i;
            Button btn=findViewById(id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   TextView  calView = findViewById(R.id.calView);
                   String _i;
                    _i = Integer.toString(finalI);
                    if(calView.getText().toString().equals("0"))
                       calView.setText(btn.getText().toString());
                   else calView.setText(calView.getText().toString() + btn.getText().toString());
                }
            });

        }
    }

}