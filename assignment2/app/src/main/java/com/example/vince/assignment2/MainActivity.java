package com.example.vince.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int i = 0;
    private int totalMarks = 0;
    static Map<String, Integer> codes = new HashMap() {
        {
            put("hint", 0);
            put("cheat", 1);
        }
    };
    TextView questionField;

    private Question[] questions = new Question[]{
            new Question(true, false, false, false, R.string.question_one, R.string.hint_one),
            new Question(true, false, false, false, R.string.question_two, R.string.hint_two),
            new Question(false, false, false, false, R.string.question_three, R.string.hint_three),
            new Question(false, false, false, false, R.string.question_four, R.string.hint_four),
            new Question(true, false, false, false, R.string.question_five, R.string.hint_five),
            new Question(true, false, false, false, R.string.question_six, R.string.hint_six)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionField = findViewById(R.id.questionField);
        updateQuestion();

        final Button trueButton = findViewById(R.id.trueButton);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(true);
            }
        });
        final Button falseButton = findViewById(R.id.falseButton);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(false);
            }
        });

        final Button hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), hint.class);
                intent.putExtra("HINT", questions[i].getHintId());
                startActivityForResult(intent, codes.get("hint"));
            }
        });

        final Button cheatButton = findViewById(R.id.cheatButton);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
                intent.putExtra("ANSWER", questions[i].getAnswer());
                startActivityForResult(intent, codes.get("cheat"));
            }
        });

        final Button prevButton = findViewById(R.id.previousButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safeIncrement("BACKWARD");
                updateQuestion();
            }
        });

        final Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safeIncrement("FORWARD");
                updateQuestion();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == codes.get("hint") && resultCode == RESULT_OK && data != null) {
            questions[i].setUsedHint(data.getBooleanExtra("hint", false));
        } else if (requestCode == codes.get("cheat") && resultCode == RESULT_OK && data != null) {
            questions[i].setUsedCheat(data.getBooleanExtra("cheat", false));
        }
    }

    private void updateQuestion() {
        questionField.setText(questions[i].getQuestionId());
    }

    private void safeIncrement(String direction) {
        if (direction == "FORWARD") i++;
        else if (direction == "BACKWARD") i--;
        if (i > questions.length - 1) i = 0;
        else if (i < 0) i = questions.length - 1;
    }

    private void submitAnswer(boolean answer) {
        Question question = questions[i];
        if (question.isCompleted())
            Toast.makeText(getApplicationContext(), "Question Completed!", Toast.LENGTH_SHORT).show();
        else if (question.usedCheat())
            Toast.makeText(getApplicationContext(), "Cheating is Wrong!", Toast.LENGTH_SHORT).show();
        else {
            question.setCompleted(true);
            String toastText = "";

            if (question.getAnswer() == answer && !question.usedHint()) {
                totalMarks += 2;
                toastText = "+2 Marks";
            } else if (question.getAnswer() == answer && question.usedHint()) {
                totalMarks++;
                toastText = "+1 Mark";
            } else if (question.getAnswer() != answer && !question.usedHint()) {
                totalMarks--;
                toastText = "-1 Mark";
            } else if (question.getAnswer() != answer && question.usedHint()) {
                totalMarks -= 2;
                toastText = "-2 Marks";
            }
            TextView totalMark = findViewById(R.id.totalMarks);
            totalMark.setText(new StringBuffer().append(totalMarks).toString());
            TextView questionsComplete = findViewById(R.id.questionsComplete);
            questionsComplete.setText(new StringBuffer().append(Integer.parseInt(questionsComplete.getText().toString()) + 1).toString());


            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
        }
    }
}