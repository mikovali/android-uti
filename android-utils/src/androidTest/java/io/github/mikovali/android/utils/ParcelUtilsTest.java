package io.github.mikovali.android.utils;

import android.accounts.Account;
import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class ParcelUtilsTest {

    // Integer

    @Test
    public void writeAndReadInteger() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeInteger(3, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readInteger(parcel)).isEqualTo(3);

        parcel.recycle();
    }

    @Test
    public void writeAndReadNullInteger() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeInteger(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readInteger(parcel)).isNull();

        parcel.recycle();
    }

    // Long

    @Test
    public void writeAndReadLong() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeLong(3L, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readLong(parcel)).isEqualTo(3L);

        parcel.recycle();
    }

    @Test
    public void writeAndReadNullLong() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeLong(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readLong(parcel)).isNull();

        parcel.recycle();
    }

    // Float

    @Test
    public void writeAndReadFloat() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeFloat(3.5f, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readFloat(parcel)).isEqualTo(3.5f);

        parcel.recycle();
    }

    @Test
    public void writeAndReadNullFloat() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeFloat(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readFloat(parcel)).isNull();

        parcel.recycle();
    }

    // Double

    @Test
    public void writeAndReadDouble() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeDouble(3.5, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readDouble(parcel)).isEqualTo(3.5);

        parcel.recycle();
    }

    @Test
    public void writeAndReadNullDouble() {
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeDouble(null, parcel);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readDouble(parcel)).isNull();

        parcel.recycle();
    }

    // Parcelable

    @Test
    public void writeAndReadParcelable() {
        final Account input = new Account("name", "type");

        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeParcelable(input, parcel, 0);
        parcel.setDataPosition(0);

        final Account output = ParcelUtils.readParcelable(Account.CREATOR, parcel);

        assertThat(output).isNotSameAs(input);
        assertThat(output.name).isEqualTo(input.name);
        assertThat(output.type).isEqualTo(input.type);

        parcel.recycle();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void writeAndReadNullParcelable() {
        final Account input = null;
        final Parcel parcel = Parcel.obtain();
        ParcelUtils.writeParcelable(input, parcel, 0);
        parcel.setDataPosition(0);

        assertThat(ParcelUtils.readParcelable(Account.CREATOR, parcel)).isNull();

        parcel.recycle();
    }
}
