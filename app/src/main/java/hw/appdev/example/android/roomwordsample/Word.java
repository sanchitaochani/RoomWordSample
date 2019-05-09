package hw.appdev.example.android.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    //private String mDefinition;

    public Word(@NonNull String word) {
        this.mWord = word;
        //this.mDefinition = definition;
    }

    public String getWord(){
        return this.mWord;
    }


}

