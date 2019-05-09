package hw.appdev.example.android.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;
    private EditText mEditDefinitionView;
    boolean mChecked = false;
    private String mPartOfSpeech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        mEditDefinitionView = findViewById(R.id.edit_definition);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText()) || TextUtils.isEmpty(mEditDefinitionView.getText()) || mChecked==false ) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String word = mEditWordView.getText().toString();
                    word = word + mPartOfSpeech + mEditDefinitionView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        mChecked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.noun:
                if (mChecked) {
                    mPartOfSpeech = "(n.)";
                }
                break;
            case R.id.adjective:
                if (mChecked) {
                    mPartOfSpeech = "(adj.)";
                }
                break;
            case R.id.adverb:
                if (mChecked) {
                    mPartOfSpeech = "(adv.)";
                }
                break;
            case R.id.preposition:
                if (mChecked) {
                    mPartOfSpeech = "(prep.)";
                }
                break;
        }
    }
}
