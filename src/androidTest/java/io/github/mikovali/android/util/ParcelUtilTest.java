package io.github.mikovali.android.util;

import android.accounts.Account;
import android.os.Parcel;

import junit.framework.TestCase;

public class ParcelUtilTest extends TestCase {

    // Integer

    public void testWriteAndReadInteger() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeInteger(3, parcel);
        parcel.setDataPosition(0);
        assertEquals(Integer.valueOf(3), ParcelUtil.readInteger(parcel));
        parcel.recycle();
    }

    public void testWriteAndReadNullInteger() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeInteger(null, parcel);
        parcel.setDataPosition(0);
        assertEquals(null, ParcelUtil.readInteger(parcel));
        parcel.recycle();
    }

    // Long

    public void testWriteAndReadLong() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeLong(3L, parcel);
        parcel.setDataPosition(0);
        assertEquals(Long.valueOf(3), ParcelUtil.readLong(parcel));
        parcel.recycle();
    }

    public void testWriteAndReadNullLong() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeLong(null, parcel);
        parcel.setDataPosition(0);
        assertEquals(null, ParcelUtil.readLong(parcel));
        parcel.recycle();
    }

    // Float

    public void testWriteAndReadFloat() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeFloat(3.5f, parcel);
        parcel.setDataPosition(0);
        assertEquals(3.5f, ParcelUtil.readFloat(parcel));
        parcel.recycle();
    }

    public void testWriteAndReadNullFloat() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeFloat(null, parcel);
        parcel.setDataPosition(0);
        assertEquals(null, ParcelUtil.readFloat(parcel));
        parcel.recycle();
    }

    // Double

    public void testWriteAndReadDouble() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeDouble(3.5, parcel);
        parcel.setDataPosition(0);
        assertEquals(3.5, ParcelUtil.readDouble(parcel));
        parcel.recycle();
    }

    public void testWriteAndReadNullDouble() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeDouble(null, parcel);
        parcel.setDataPosition(0);
        assertEquals(null, ParcelUtil.readDouble(parcel));
        parcel.recycle();
    }

    // Parcelable

    public void testWriteAndReadParcelable() {
        final Account input = new Account("name", "type");

        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeParcelable(input, parcel, 0);
        parcel.setDataPosition(0);
        final Account output = ParcelUtil.readParcelable(Account.CREATOR, parcel);

        assertNotSame(input, output);
        assertEquals(input.name, output.name);
        assertEquals(input.type, output.type);
        parcel.recycle();
    }

    public void testWriteAndReadNullParcelable() {
        final Account input = null;

        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeParcelable(input, parcel, 0);
        parcel.setDataPosition(0);
        final Account output = ParcelUtil.readParcelable(Account.CREATOR, parcel);

        assertNull(output);
    }
}
