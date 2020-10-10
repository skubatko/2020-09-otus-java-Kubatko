package ru.skubatko.dev.otus.java.hw03;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@DisplayName("Моя имплементация интерфейса List")
class DIYArrayListTest {

    List<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DIYArrayList<>();
    }

    @DisplayName("должна добавлять ожидаемые элементы при помощи Collections.addAll")
    @Test
    void shouldAddExpectedItemsByCollectionsAddAll() {
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, -1, -2, -3, -4, -5, -6, -7, 8, -9, 0);

        assertThat(list).hasSize(24);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(23)).isEqualTo(0);
    }

    @DisplayName("должна копировать листы при помощи Collections.copy для четырех элементов")
    @Test
    void shouldCopyListsByCollectionsCopyForFourItems() {
        Collections.addAll(list, 1, 2, 3, 4);

        List<Integer> dest = new DIYArrayList<>();
        Collections.addAll(dest, 0, 0, 0, 0);

        Collections.copy(dest, list);

        assertThat(dest).containsExactlyElementsOf(list);
    }

    @DisplayName("должна копировать листы при помощи Collections.copy для двадцати элементов")
    @Test
    void shouldCopyListsByCollectionsCopyForTwentyItems() {
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        List<Integer> dest = new DIYArrayList<>();
        Collections.addAll(dest, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        Collections.copy(dest, list);

        assertThat(dest).containsExactlyElementsOf(list);
    }

}
