package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import static org.assertj.core.api.Assertions.*;
@Test
public class PointSpec {

    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeMethod
    public void beforeTest() {
        point = new Point(x, y);
    }

    public void whenInstantiatedThenXIsSet() {

    assertThat(point.getX()).isEqualTo(x);

    }

    public void whenInstantiatedThenYIsSet() {

        assertThat(point.getY()).isEqualTo(y);

    }

}
