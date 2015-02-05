package io.github.mikovali.android.util;

import android.os.Parcel;

/**
 * Utility to make working with nullable values and parcels easier.
 */
public final class ParcelUtil {

    private static final int NULL = -1;
    private static final int NOT_NULL = 0;

    public static void writeInteger(Integer value, Parcel dest) {
        if (value == null) {
            dest.writeInt(NULL);
        } else {
            dest.writeInt(NOT_NULL);
            dest.writeInt(value);
        }
    }

    public static Integer readInteger(Parcel in) {
        return in.readInt() == NULL ? null : in.readInt();
    }

    public static void writeLong(Long value, Parcel dest) {
        if (value == null) {
            dest.writeInt(NULL);
        } else {
            dest.writeInt(NOT_NULL);
            dest.writeLong(value);
        }
    }

    public static Long readLong(Parcel in) {
        return in.readInt() == NULL ? null : in.readLong();
    }
}
