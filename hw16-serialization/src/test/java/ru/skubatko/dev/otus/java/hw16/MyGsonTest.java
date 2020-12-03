package ru.skubatko.dev.otus.java.hw16;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
