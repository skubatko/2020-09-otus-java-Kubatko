package ru.skubatko.dev.otus.java.hw16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ru.skubatko.dev.otus.java.hw16.serializer.MyGson;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@DisplayName("Мой сериализатор")
class MyGsonTest {

    private final int anInteger = 22;
    private final String aString = "test";
    private final List<Integer> aList = List.of(3, 5, 7, 12);
    private final int[] anArray = new int[]{10, 12, 24, 78};

    private final MyGson myGson = new MyGson();
    private final Gson gson = new Gson();

    @DisplayName("должен сериализовать полный объект")
    @Test
    void shouldSerializeFullObject() {
        AnyObject obj = new AnyObject(anInteger, aString, anArray, aList);

        String myJson = myGson.toJson(obj);

        assertMyJson(obj, myJson);
    }

    @DisplayName("должен сериализовать нулевой объект")
    @Test
    void shouldSerializeNullObject() {
        AnyObject obj = null;

        String myJson = myGson.toJson(obj);

        assertMyJson(obj, myJson);
    }

    @DisplayName("должен сериализовать объект без Integer")
    @Test
    void shouldSerializeObjectWithNoInteger() {
        AnyObject obj = new AnyObject(null, aString, anArray, aList);

        String myJson = myGson.toJson(obj);

        assertMyJson(obj, myJson);
    }

    @DisplayName("должен сериализовать объект без String")
    @Test
    void shouldSerializeObjectWithNoString() {
        AnyObject obj = new AnyObject(anInteger, null, anArray, aList);

        String myJson = myGson.toJson(obj);

        assertMyJson(obj, myJson);
    }

    @DisplayName("должен сериализовать объект без массива")
    @Test
    void shouldSerializeObjectWithNoArray() {
        AnyObject obj = new AnyObject(anInteger, aString, null, aList);

        String myJson = myGson.toJson(obj);

        assertMyJson(obj, myJson);
    }

    @DisplayName("должен сериализовать объект без списка")
    @Test
    void shouldSerializeObjectWithNoList() {
        AnyObject obj = new AnyObject(anInteger, aString, anArray, null);

        String myJson = myGson.toJson(obj);

        assertMyJson(obj, myJson);
    }

    private void assertMyJson(AnyObject obj, String myJson) {
        AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
        assertThat(obj).isEqualTo(obj2);
    }

    @DisplayName("должен сериализовать различные типы объектов")
    @Test
    void shouldSerializeDifferentObjectTypes() {
        var gson = new Gson();
        MyGson serializer = new MyGson();
        assertEquals(gson.toJson(null), serializer.toJson(null));
        assertEquals(gson.toJson((byte)1), serializer.toJson((byte)1));
        assertEquals(gson.toJson((short)1f), serializer.toJson((short)1f));
        assertEquals(gson.toJson(1), serializer.toJson(1));
        assertEquals(gson.toJson(1L), serializer.toJson(1L));
        assertEquals(gson.toJson(1f), serializer.toJson(1f));
        assertEquals(gson.toJson(1d), serializer.toJson(1d));
        assertEquals(gson.toJson("aaa"), serializer.toJson("aaa"));
        assertEquals(gson.toJson('a'), serializer.toJson('a'));
        assertEquals(gson.toJson(new int[] {1, 2, 3}), serializer.toJson(new int[] {1, 2, 3}));
        assertEquals(gson.toJson(List.of(1, 2 ,3)), serializer.toJson(List.of(1, 2 ,3)));
        assertEquals(gson.toJson(Collections.singletonList(1)), serializer.toJson(Collections.singletonList(1)));
    }
}
