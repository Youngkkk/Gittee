// IBookManager.aidl
package com.young.cm.aidltest.aidl;

import com.young.cm.aidltest.aidl.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    List<Book> getBooks();

    void setBook(in Book book);

}
