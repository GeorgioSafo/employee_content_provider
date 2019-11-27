package ru.example.employeecontentprovider;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static ru.example.employeecontentprovider.EmployeeProvider.CONTENT_URI;
import static ru.example.employeecontentprovider.EmployeeProvider.URL;

/**
 * @author Gevork Safaryan on 26/11/2019
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(Employee.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(Employee.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        ContentResolver resolver = getContentResolver();

        Uri uri = resolver.insert(
                EmployeeProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onUpdate(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(Employee.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(Employee.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        ContentResolver resolver = getContentResolver();

        int value = resolver.update(EmployeeProvider.CONTENT_URI, values, EmployeeProvider._ID + "=?",new String[]{String.valueOf(employee.getId())});

        Toast.makeText(getBaseContext(),
                value, Toast.LENGTH_LONG).show();
        EmployeeDataObserver observer = new EmployeeDataObserver(new Handler());
        resolver.registerContentObserver(Uri.withAppendedPath(CONTENT_URI,Integer.toString(employee.getId())),false,observer);
        resolver.notifyChange(Uri.withAppendedPath(CONTENT_URI,Integer.toString(employee.getId())),null);
    }

    public void onDelete(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(Employee.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(Employee.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        ContentResolver resolver = getContentResolver();

        int value = resolver.delete(EmployeeProvider.CONTENT_URI, EmployeeProvider._ID + "=?",new String[]{String.valueOf(employee.getId())});

        Toast.makeText(getBaseContext(),
                value, Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        Uri students = Uri.parse(URL);

        Cursor c = getContentResolver().query(students, null, null, null, "name");
        try {
            if (c.moveToFirst()) {
                do {
                    Toast.makeText(this,
                            c.getString(c.getColumnIndex(EmployeeProvider._ID)) +
                                    ", " + c.getString(c.getColumnIndex(EmployeeProvider.NAME)) +
                                    ", " + c.getString(c.getColumnIndex(EmployeeProvider.GRADE)),
                            Toast.LENGTH_SHORT).show();


                    Log.d("Cursor", c.getString(c.getColumnIndex(EmployeeProvider._ID)) +
                            ", " + c.getString(c.getColumnIndex(EmployeeProvider.NAME)) +
                            ", " + c.getString(c.getColumnIndex(EmployeeProvider.GRADE)));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
    }
}
