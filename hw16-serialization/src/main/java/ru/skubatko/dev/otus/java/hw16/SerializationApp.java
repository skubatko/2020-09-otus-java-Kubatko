package ru.skubatko.dev.otus.java.hw16;

import com.google.gson.Gson;

import java.util.List;

public class SerializationApp {

    public static void main(String[] args) {
        MyGson myGson = new MyGson();
        AnyObject obj = new AnyObject(22, "test", new int[]{10, 12, 24, 78}, List.of(3, 5, 7, 12));
        String myJson = myGson.toJson(obj);

        Gson gson = new Gson();
        AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
        System.out.println(obj.equals(obj2));
    }
}
