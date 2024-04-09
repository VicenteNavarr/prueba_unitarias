package org.iesvdm.tddjava.asserts;

//Hay varias versiones de JUNIT: 3, 4 y 5

//JUNIT 5 --> Tiene soporte para JAVA 8 fundamentalmente LAMBDAS
import org.junit.jupiter.api.Test;

import java.util.*;

//alt+insert para constructores, equals, getters...

//IMPORT STATIC, ¿que hace? --> es código que no necesita de un objeto para instanciarlo - ejecutarse -
//En cierta forma tiene una naturaleza global.

//En este caso se está trayendo todo el código estático de la clase Assertions
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;




//Existe la convención de que las Clases se llaman con el sufijo test
//NombreCosaAterstearTest
public class AssertJTest {


    //La clase de Test tiene una seria de métodos que implementan pruebas parciales de interás
    // Es fundamental que el método esté anotado con la anotación @Test

    @Test
    void whenBooleanIsTrue()
    {
        //El assert es una afirmación que debe cumplirse para que este test sea válido
        assertTrue(1 == 1);
    }

    @Test
    void whenBooleanIsFalse() {
        boolean flag =false;
        assertFalse(flag);
    }

    @Test
    void whenObjectIsNull() {
        Object nullObj = null;
        // Comprueba que la referencia apunta a null
        assertNull(nullObj);

    }


    @Test
    void whenObjectIsNotNull() {
        Object obj = new Object();
        //Comprueba que la referencia no apunta a null
        assertNotNull(obj);

    }


    @Test
    void shouldBeEqual() {
        final Integer ACTUAL = 9;
        final Integer EXPECTED = 9;

        assertEquals(ACTUAL, EXPECTED);
        //Siempre que quiero comparar igualdad de objetos(no digo primitivos) habría que hacerlo con .Equals


    }

    @Test
    void shouldReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;
        //El same compara las referencias de Object
        assertSame(ACTUAL, EXPECTED);

    }

    @Test
    void shouldNotReferToSameObject() {
        final Object ACTUAL = 9;
        final Object EXPECTED = ACTUAL;

        assertNotSame(ACTUAL, EXPECTED);

    }


    @Test
    void shouldContainSameIntegers() {
        final int[] ACTUAL = new int[]{2, 5, 7};
        final int[] EXPECTED = new int[]{2, 5, 7};

        assertThat(ACTUAL).containsExactly(EXPECTED);

    }

    @Test
    void shouldContainTwoElements() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).hasSize(2);

    }

    @Test
    void shouldNotContainAnyElement() {

        List list = Arrays.asList();

        assertThat(list).isEmpty();

    }

    @Test
    void shouldContainCorrectElementsInGivenOrder() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).containsExactlyElementsOf(Arrays.asList(first, second));

    }

    @Test
    void shouldContainCorrectElementsInAnyOrder() {
        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        List list2 = Arrays.asList(second, first);


        assertThat(list).containsExactlyInAnyOrderElementsOf(list2);

    }

    @Test
    void shouldContainCorrectElementOnce() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).containsOnlyOnce(first);

    }

    @Test
    void shouldNotContainIncorrectElement() {

        Object first = new Object();
        Object second = new Object();

        List list = Arrays.asList(first, second);

        assertThat(list).doesNotContain(new Object());

    }

    @Test
    void shouldContainCorrectKey() {
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).containsKey(KEY);
    }

    @Test
    void shouldNotContainCorrectKey() {
        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";


        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).doesNotContainKeys(INCORRECT_KEY);

    }

    @Test
    void shouldContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).containsEntry(KEY, VALUE);

    }

    @Test
    void shouldNotContainGivenEntry() {

        final String INCORRECT_KEY = "incorrect_key";
        final String KEY = "key";
        final String VALUE = "value";

        final Map<String, String> map = new HashMap<>();
        map.put(KEY, VALUE);

        assertThat(map).doesNotContainEntry(INCORRECT_KEY, VALUE);

    }


}
