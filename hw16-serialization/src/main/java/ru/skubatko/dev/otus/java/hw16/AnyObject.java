package ru.skubatko.dev.otus.java.hw16;

import java.util.Arrays;
import java.util.Collection;

public class AnyObject {

    private final Integer anInteger;
    private final String aString;
    private final int[] anArray;
    private final Collection<Integer> aList;

    public AnyObject(Integer anInteger, String aString, int[] anArray, Collection<Integer> aList) {
        this.anInteger = anInteger;
        this.aString = aString;
        this.anArray = anArray;
        this.aList = aList;
    }

    public Integer getAnInteger() {
        return anInteger;
    }

    public String getaString() {
        return aString;
    }

    public int[] getAnArray() {
        return anArray;
    }

    public Collection<Integer> getaList() {
        return aList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnyObject)) {
            return false;
        }

        AnyObject anyObject = (AnyObject) o;

        if (anInteger != null ? !anInteger.equals(anyObject.anInteger) : anyObject.anInteger != null) {
            return false;
        }
        if (aString != null ? !aString.equals(anyObject.aString) : anyObject.aString != null) {
            return false;
        }
        if (!Arrays.equals(anArray, anyObject.anArray)) {
            return false;
        }
        return aList != null ? aList.equals(anyObject.aList) : anyObject.aList == null;
    }

    @Override
    public int hashCode() {
        int result = anInteger != null ? anInteger.hashCode() : 0;
        result = 31 * result + (aString != null ? aString.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(anArray);
        result = 31 * result + (aList != null ? aList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnyObject{" +
                       "anInteger=" + anInteger +
                       ", aString='" + aString + '\'' +
                       ", anArray=" + Arrays.toString(anArray) +
                       ", aList=" + aList +
                       '}';
    }
}
