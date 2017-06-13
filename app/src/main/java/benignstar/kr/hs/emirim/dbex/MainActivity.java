package benignstar.kr.hs.emirim.dbex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class MyDBHelper extends SQLiteOpenHelper{


        public MyDBHelper(Context context) {
            // idolDB라는 이름의 데이터베이스가 생성된다
            super(context, "idolDB", null, 1);
        }
        // idolTable이라는 이름의 테이블 생
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql="create table idolTable(idolName text not null primary key, idolCount integer)";
            sqLiteDatabase.execSQL(sql);
        }
        // 이미 idolTable이 존재한다면 기존의 테이블을 삭제하고 새로 테이블을 만들 때 호출
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exist idolTable");
            onCreate(sqLiteDatabase);
        }
    }
}
