package io.github.mikovali.android.util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Utility to make working with nullable values and parcels easier.
 */
public final class ParcelUtil {

    private static final int NULL = -1;
    private static final int NOT_NULL = 0;

    // Integer

    public static void writeInteger(Integer value, Parcel dest) {
        if (!setNull(value, dest)) {
            dest.writeInt(value);
        }
    }

    public static Integer readInteger(Parcel in) {
        return isNull(in) ? null : in.readInt();
    }

    // Long

    public static void writeLong(Long value, Parcel dest) {
        if (!setNull(value, dest)) {
            dest.writeLong(value);
        }
    }

    public static Long readLong(Parcel in) {
        return isNull(in) ? null : in.readLong();
    }

    // Float

    public static void writeFloat(Float value, Parcel dest) {
        if (!setNull(value, dest)) {
            dest.writeFloat(value);
        }
    }

    public static Float readFloat(Parcel in) {
        return isNull(in) ? null : in.readFloat();
    }

    // Double

    public static void writeDouble(Double value, Parcel dest) {
        if (!setNull(value, dest)) {
            dest.writeDouble(value);
        }
    }

    public static Double readDouble(Parcel in) {
        return isNull(in) ? null : in.readDouble();
    }

    // Parcelable

    public static <T extends Parcelable> void writeParcelable(T value, Parcel dest, int flags) {
        if (!setNull(value, dest)) {
            value.writeToParcel(dest, flags);
        }
    }

    public static <T extends Parcelable> T readParcelable(Parcelable.Creator<T> creator,
                                                          Parcel in) {
        return isNull(in) ? null : creator.createFromParcel(in);
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
