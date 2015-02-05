package io.github.mikovali.android.util;

import android.os.Parcel;

/**
 * Utility to make working with nullable values and parcels easier.
 */
public final class ParcelUtil {

    private static final int NULL = -1;
    private static final int NOT_NULL = 0;

    public static void writeInteger(Integer value, Parcel dest) {
        if (!setNull(value, dest)) {
            dest.writeInt(value);
        }
    }

    public static Integer readInteger(Parcel in) {
        return isNull(in) ? null : in.readInt();
    }

    public static void writeLong(Long value, Parcel dest) {
        if (!setNull(value, dest)) {
            dest.writeLong(value);
        }
    }

    public static Long readLong(Parcel in) {
        return isNull(in) ? null : in.readLong();
    }

    // helpers

    private static boolean setNull(Object value, Parcel dest) {
        final boolean isNull = value == null;
        dest.writeInt(isNull ? NULL : NOT_NULL);
        return isNull;
    }

    private static boolean isNull(Parcel in) {
        return in.readInt() == NULL;
    }
}
