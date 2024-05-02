package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import static org.assertj.core.api.Assertions.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {

        //Comprobación que el getSHORTNAME del N es North
        assertThat(Direction.getFromShortName('N')).isEqualTo(Direction.NORTH);

    }

    public void whenGetFromShortNameWThenReturnDirectionW() {


        //Comprobación que el getSHORTNAME del W es WEST
        assertThat(Direction.getFromShortName('W')).isEqualTo(Direction.WEST);

    }

    public void whenGetFromShortNameBThenReturnNone() {

        //Comprobación que el getSHORTNAME del B es nulo/none
        assertThat(Direction.getFromShortName('B')).isEqualTo(Direction.NONE);

    }

    public void givenSWhenLeftThenE() {

        //Si en S giro a la izquierda, vamos al este
        assertThat(Direction.getFromShortName('S').turnLeft()).isEqualTo(Direction.EAST);

    }

    public void givenNWhenLeftThenW() {

        //Si en n giro a la izquierda, vamos al OESTE
        assertThat(Direction.getFromShortName('N').turnLeft()).isEqualTo(Direction.WEST);

    }

    public void givenSWhenRightThenW() {

        //Si en s giro a la derecha, vamos al OESTE
        assertThat(Direction.getFromShortName('S').turnRight()).isEqualTo(Direction.WEST);

    }

    public void givenWWhenRightThenN() {

        //Si en W giro a la derecha, vamos al norte
        assertThat(Direction.getFromShortName('W').turnRight()).isEqualTo(Direction.NORTH);

    }

}

