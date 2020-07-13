package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    public static final String  KEY_NUM_DICE ="num_dice";
    public static final String KEY_TOTAL = "total" ;
    private int total;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        TextView resultView = findViewById(R.id.result);
        Button doneButton = findViewById(R.id.done_button);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(KEY_TOTAL, total);
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });

//        int result = rollOne();
//        String text = String.valueOf(result);
//        resultView.setText(text);

        int numDice = getIntent().getIntExtra(KEY_NUM_DICE,1);
        rollAll(resultView, numDice);
    }

    private void rollAll(TextView textView, int numDice){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numDice; ++i){
            int result = rollOne();
            total += result;

            if (i > 0){
                builder.append(" + ");
            }

            builder.append(result);
        }

        if (numDice > 1){
            builder.append(" = ");
            builder.append(total);
        }

        textView.setText(builder.toString());
    }

    private int rollOne(){
        return random.nextInt(6) + 1;
    }

}