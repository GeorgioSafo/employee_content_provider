package ru.example.employeecontentprovider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/**
 * @author Gevork Safaryan on 26/11/2019
 */
public class EmployeeDataObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public EmployeeDataObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
    }
}
