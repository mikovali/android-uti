package io.github.mikovali.android.util;

import android.accounts.Account;
import android.os.Parcel;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelUtilTest extends TestCase {

    // Integer

    public void testWriteAndReadInteger() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeInteger(3, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readInteger(parcel)).isEqualTo(3);

        parcel.recycle();
    }

    public void testWriteAndReadNullInteger() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeInteger(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readInteger(parcel)).isNull();

        parcel.recycle();
    }

    // Long

    public void testWriteAndReadLong() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeLong(3L, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readLong(parcel)).isEqualTo(3L);

        parcel.recycle();
    }

    public void testWriteAndReadNullLong() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeLong(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readLong(parcel)).isNull();

        parcel.recycle();
    }

    // Float

    public void testWriteAndReadFloat() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeFloat(3.5f, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readFloat(parcel)).isEqualTo(3.5f);

        parcel.recycle();
    }

    public void testWriteAndReadNullFloat() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeFloat(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readFloat(parcel)).isNull();

        parcel.recycle();
    }

    // Double

    public void testWriteAndReadDouble() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeDouble(3.5, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readDouble(parcel)).isEqualTo(3.5);

        parcel.recycle();
    }

    public void testWriteAndReadNullDouble() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeDouble(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readDouble(parcel)).isNull();

        parcel.recycle();
    }

    // Parcelable

    public void testWriteAndReadParcelable() {
        final Account input = new Account("name", "type");

        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeParcelable(input, parcel, 0);
        parcel.setDataPosition(0);

        final Account output = ParcelUtil.readParcelable(Account.CREATOR, parcel);

        assertThat(output).isNotSameAs(input);
        assertThat(output.name).isEqualTo(input.name);
        assertThat(output.type).isEqualTo(input.type);

        parcel.recycle();
    }

    @SuppressWarnings("ConstantConditions")
    public void testWriteAndReadNullParcelable() {
        final Account input = null;
        final Parcel parcel = Parcel.obtain();
        ParcelUtil.writeParcelable(input, parcel, 0);
        parcel.setDataPosition(0);

        assertThat(ParcelUtil.readParcelable(Account.CREATOR, parcel)).isNull();

        parcel.recycle();
    }
}
