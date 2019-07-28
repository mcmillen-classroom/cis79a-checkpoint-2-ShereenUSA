package shereenskalsky.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mPreviousButton;
    private ImageButton mNextButton;

    private Question[] mQuestions;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);

        // Initialize an array of questions.
        mQuestions = new Question[7];
        mIndex = 0;

        mQuestions[0] = new Question(R.string.question_1, true);
        mQuestions[1] = new Question(R.string.question_2, true);
        mQuestions[2] = new Question(R.string.question_3, true);
        mQuestions[3] = new Question(R.string.question_4, false);
        mQuestions[4] = new Question(R.string.question_5, false);
        mQuestions[5] = new Question(R.string.question_6, true);
        mQuestions[6] = new Question(R.string.question_7, true);

        mTextView.setText(mQuestions[mIndex].getTextResId());
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.true_button)
        {
            checkAnswer(true);
        }
        else if (view.getId() == R.id.false_button)
        {
            checkAnswer(false);
        }
        else if (view.getId() == R.id.previous_button)
        {
        // Change to the previous question ...

            // decrement the index by one.
            mIndex--;

            // If the index is less than the number of questions, loop to the last question.
            if (mIndex < 0)
            {
                mIndex = 6;
            }

            //Change text in view.
            mTextView.setText(mQuestions[mIndex].getTextResId());

        }
        else if (view.getId() == R.id.next_button)
        {
        // Change to the next question ...

            // Increment the index by one.
            mIndex++;

            // If the index is greater than the number of questions, loop back to the first question.
            if (mIndex >= mQuestions.length)
            {
                mIndex = 0;
            }

            //Change text in view.
            mTextView.setText(mQuestions[mIndex].getTextResId());

        }
        else
        {
            // Do nothing (Not used).
        }
    }

    public boolean checkAnswer(boolean userInput)
    {
        if (mQuestions[mIndex].getAnswer() == userInput)
        {
            Toast myToast = Toast.makeText(this, "You are correct!!!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 10, 200);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect!!!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.BOTTOM, 10, 200);
            myToast.show();
            return false;
        }
    }
}
