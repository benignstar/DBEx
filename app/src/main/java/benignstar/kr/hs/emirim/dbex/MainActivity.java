package benignstar.kr.hs.emirim.dbex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    MyDBHelper myHelper;
    EditText edit_group_name, edit_group_count, edit_result_name, edit_result_count;
    Button but_init, but_insert, but_select;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_group_name=(EditText)findViewById(R.id.edit_group_name);
        edit_group_count=(EditText)findViewById(R.id.edit_group_count);
        edit_result_name=(EditText)findViewById(R.id.edit_result_name);
        edit_result_count=(EditText)findViewById(R.id.edit_result_count);
        but_init=(Button)findViewById(R.id.but_init);
        but_insert=(Button)findViewById(R.id.but_insert);
        but_select=(Button)findViewById(R.id.but_select);

        myHelper=new MyDBHelper(this);
        but_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB=myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });
    }

    class MyDBHelper extends SQLiteOpenHelper{


        public MyDBHelper(Context context) {
            // idolDB라는 이름의 데이터베이스가 생성된다
            super(context, "idolDB", null, 1);
        }
        // idolTable이라는 이름의 테이블 생성
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
