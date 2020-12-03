package ru.skubatko.dev.otus.java.hw16;

import javax.json.Json;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyGson {

    public String toJson(AnyObject obj) {
        if (obj == null) {
            return null;
        }

        var jsonObjectBuilder = Json.createObjectBuilder();

        var anInteger = obj.getAnInteger();
        if (Objects.nonNull(anInteger)) {
            jsonObjectBuilder.add("anInteger", anInteger);
        }

        var aString = obj.getaString();
        if (Objects.nonNull(aString)) {
            jsonObjectBuilder.add("aString", aString);
        }

        var anArray = obj.getAnArray();
        if (Objects.nonNull(anArray)) {
            jsonObjectBuilder.add("anArray", Json.createArrayBuilder(
                    Arrays.stream(anArray).boxed().collect(Collectors.toList())));
        }

        var aList = obj.getaList();
        if (Objects.nonNull(aList)) {
            jsonObjectBuilder.add("aList", Json.createArrayBuilder(aList));
        }

        var jsonObject = jsonObjectBuilder.build();

        System.out.println("jsonObject:" + jsonObject + "\n");

        return jsonObject.toString();
    }
}
