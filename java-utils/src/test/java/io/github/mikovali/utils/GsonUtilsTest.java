package io.github.mikovali.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO valid tests
 */
@RunWith(DataProviderRunner.class)
public class GsonUtilsTest {

    public static final String MEMBER = "member";

    // Integer

    @Test
    public void getAsIntegerWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsInteger(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsIntegerWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsInteger(object, MEMBER)).isNull();
    }

    // Long

    @Test
    public void getAsLongWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsLong(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsLongWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsLong(object, MEMBER)).isNull();
    }

    // Short

    @Test
    public void getAsShortWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsShort(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsShortWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsShort(object, MEMBER)).isNull();
    }

    // Float

    @Test
    public void getAsFloatWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsFloat(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsFloatWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsFloat(object, MEMBER)).isNull();
    }

    // Double

    @Test
    public void getAsDoubleWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsDouble(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsDoubleWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsDouble(object, MEMBER)).isNull();
    }

    // Byte

    @Test
    public void getAsByteWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsByte(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsByteWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsByte(object, MEMBER)).isNull();
    }

    // Number

    @Test
    public void getAsNumberWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsNumber(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notNumberProvider")
    public void getAsNumberWhereMemberIsNotNumber(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsNumber(object, MEMBER)).isNull();
    }

    // Boolean

    @Test
    public void getAsBooleanWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsBoolean(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notBooleanProvider")
    public void getAsBooleanWhereMemberIsNotBoolean(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsBoolean(object, MEMBER)).isNull();
    }

    // Character

    @Test
    public void getAsCharacterWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsCharacter(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notCharacterProvider")
    public void getAsCharacterWhereMemberIsNotCharacter(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsCharacter(object, MEMBER)).isNull();
    }

    // String

    @Test
    public void getAsStringWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsString(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notStringProvider")
    public void getAsStringWhereMemberIsNotString(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsString(object, MEMBER)).isNull();
    }

    // JsonPrimitive

    @Test
    public void getAsJsonPrimitiveWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsJsonPrimitive(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notJsonPrimitiveProvider")
    public void getAsJsonPrimitiveWhereMemberIsNotJsonPrimitive(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsJsonPrimitive(object, MEMBER)).isNull();
    }

    // JsonNull

    @Test
    public void getAsJsonNullWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsJsonNull(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notJsonNullProvider")
    public void getAsJsonNullWhereMemberIsNotJsonNull(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsJsonNull(object, MEMBER)).isNull();
    }

    // JsonArray

    @Test
    public void getAsJsonArrayWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsJsonArray(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notJsonArrayProvider")
    public void getAsJsonArrayWhereMemberIsNotJsonArray(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsJsonArray(object, MEMBER)).isNull();
    }

    // JsonObject

    @Test
    public void getAsJsonObjectWhereMemberDoesNotExist() {
        assertThat(GsonUtils.getAsJsonObject(new JsonObject(), MEMBER)).isNull();
    }

    @Test
    @UseDataProvider("notJsonObjectProvider")
    public void getAsJsonObjectWhereMemberIsNotJsonObject(JsonElement member) {
        final JsonObject object = new JsonObject();
        object.add(MEMBER, member);

        assertThat(GsonUtils.getAsJsonObject(object, MEMBER)).isNull();
    }

    // providers

    @DataProvider
    public static Object[][] notNumberProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isNumber();
            }
        });
    }

    @DataProvider
    public static Object[][] notBooleanProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isBoolean();
            }
        });
    }

    @DataProvider
    public static Object[][] notCharacterProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()
                        || element.getAsString().length() != 1;
            }
        });
    }

    @DataProvider
    public static Object[][] notStringProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString();
            }
        });
    }

    @DataProvider
    public static Object[][] notJsonPrimitiveProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonPrimitive();
            }
        });
    }

    @DataProvider
    public static Object[][] notJsonNullProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonNull();
            }
        });
    }

    @DataProvider
    public static Object[][] notJsonArrayProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonArray();
            }
        });
    }

    @DataProvider
    public static Object[][] notJsonObjectProvider() {
        return getJsonElementsAsProvider(new Condition() {
            @Override
            public boolean valid(JsonElement element) {
                return !element.isJsonObject();
            }
        });
    }

    // helpers

    private static JsonElement[] ALL_JSON_ELEMENTS = new JsonElement[] {
            new JsonPrimitive(Integer.MIN_VALUE),
            new JsonPrimitive(Integer.MAX_VALUE),
            new JsonPrimitive(Long.MIN_VALUE),
            new JsonPrimitive(Long.MAX_VALUE),
            new JsonPrimitive(Short.MIN_VALUE),
            new JsonPrimitive(Short.MAX_VALUE),
            new JsonPrimitive(Float.MIN_VALUE),
            new JsonPrimitive(Float.MAX_VALUE),
            new JsonPrimitive(Double.MIN_VALUE),
            new JsonPrimitive(Double.MAX_VALUE),
            new JsonPrimitive(Byte.MIN_VALUE),
            new JsonPrimitive(Byte.MAX_VALUE),
            new JsonPrimitive(Boolean.TRUE),
            new JsonPrimitive(Boolean.FALSE),
            new JsonPrimitive(Character.MIN_VALUE),
            new JsonPrimitive(Character.MAX_VALUE),
            new JsonPrimitive(""),
            new JsonPrimitive("a"),
            new JsonPrimitive("aaa"),
            JsonNull.INSTANCE,
            new JsonArray(),
            new JsonObject()
    };

    private static Object[][] getJsonElementsAsProvider(Condition condition) {
        final List<JsonElement> elements = new ArrayList<>();
        for (final JsonElement element : ALL_JSON_ELEMENTS) {
            if (condition.valid(element)) {
                elements.add(element);
            }
        }
        final Object[][] value = new Object[elements.size()][1];
        for (int i = 0; i < value.length; i++) {
            value[i][0] = elements.get(i);
        }
        return value;
    }

    private interface Condition {
        boolean valid(JsonElement element);
    }
}
