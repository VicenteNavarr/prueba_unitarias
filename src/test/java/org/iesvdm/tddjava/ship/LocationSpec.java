package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {

        assertThat(location.getX()).isEqualTo(x);

    }

    public void whenInstantiatedThenYIsStored() {

        assertThat(location.getY()).isEqualTo(y);

    }

    public void whenInstantiatedThenDirectionIsStored() {

        assertThat(location.getDirection()).isEqualTo(direction);

    }

    public void givenDirectionNWhenForwardThenYDecreases() {

        //Dirección norte -- COmienzo
        assertThat(location.getDirection()).isEqualTo(Direction.NORTH);//siempre pasa porque es un final = .north

        int antesY = location.getY(); // Guardamos la coordenada Y antes de avanzar


        assertThat(location.forward()).isEqualTo(true);//comprobación de avance adelante
        assertThat(location.getY()).isEqualTo(antesY - 1);//comprobación de y-1


    }

    public void givenDirectionSWhenForwardThenYIncreases() {

        //Dirección sur -- COmienzo
        location = new Location(new Point(x, y), Direction.SOUTH);//seteamos para sur()por lo del final direction...



        int antesY = location.getY(); // Guardamos la coordenada Y antes de avanzar


        assertThat(location.forward()).isEqualTo(true);//comprobación de avance adelante
        assertThat(location.getY()).isEqualTo(antesY + 1);//comprobación de y+1

    }

    public void givenDirectionEWhenForwardThenXIncreases() {

        //Repetimos los anteriores test cambiando valores...uff
        location = new Location(new Point(x, y), Direction.EAST);



        int antesX = location.getX(); // Guardamos la coordenada X antes de avanzar


        assertThat(location.forward()).isEqualTo(true);//comprobación de avance adelante
        assertThat(location.getX()).isEqualTo(antesX + 1);//comprobación de incrementox

    }

    public void givenDirectionWWhenForwardThenXDecreases() {

        location = new Location(new Point(x, y), Direction.WEST);



        int antesX = location.getX(); // Guardamos la coordenada X antes de avanzar


        assertThat(location.forward()).isEqualTo(true);//comprobación de avance adelante
        assertThat(location.getX()).isEqualTo(antesX - 1);
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {

        location = new Location(new Point(x, y), Direction.NORTH);



        int antesY = location.getY(); // Guardamos la coordenada X antes de avanzar


        assertThat(location.backward()).isEqualTo(true);//comprobación de avance atras
        assertThat(location.getY()).isEqualTo(antesY + 1);
    }



    public void givenDirectionSWhenBackwardThenYDecreases() {

        location = new Location(new Point(x, y), Direction.SOUTH);



        int antesY = location.getY(); // Guardamos la coordenada X antes de avanzar


        assertThat(location.backward()).isEqualTo(true);//comprobación de avance atras
        assertThat(location.getY()).isEqualTo(antesY - 1);

    }

    public void givenDirectionEWhenBackwardThenXDecreases() {

        location = new Location(new Point(x, y), Direction.EAST);



        int antesX = location.getX(); // Guardamos la coordenada X antes de avanzar


        assertThat(location.backward()).isEqualTo(true);//comprobación de avance atras
        assertThat(location.getX()).isEqualTo(antesX - 1);


    }

    public void givenDirectionWWhenBackwardThenXIncreases() {

        location = new Location(new Point(x, y), Direction.WEST);



        int antesX = location.getX(); // Guardamos la coordenada X antes de avanzar


        assertThat(location.backward()).isEqualTo(true);//comprobación de avance atras
        assertThat(location.getX()).isEqualTo(antesX + 1);

    }

    public void whenTurnLeftThenDirectionIsSet() {

        //Seteo direccion norte
        location = new Location(new Point(x, y), Direction.NORTH);

        // Girar a la izquierda
        location.turnLeft();

        // Si giro a la izquierda mirando norte, vamos a oeste
        assertThat(location.getDirection()).isEqualTo(Direction.WEST);


    }

    public void whenTurnRightThenDirectionIsSet() {

        //Seteo direccion norte -->seguimos con norte...
        location = new Location(new Point(x, y), Direction.NORTH);

        // Girar a la izquierda
        location.turnRight();

        // Si giro a la derecha mirando norte, vamos a este
        assertThat(location.getDirection()).isEqualTo(Direction.EAST);

    }

    public void givenSameObjectsWhenEqualsThenTrue() {

        //creo 2 objetos location iguales
        Location l1 = new Location(new Point(x, y), Direction.NORTH);
        Location l2 = new Location(new Point(x, y), Direction.NORTH);

        assertThat(l1.equals(l2)).isTrue();

    }

    public void givenDifferentObjectWhenEqualsThenFalse() {

        //creo 2 objetos location iguales
        Location l1 = new Location(new Point(x, y), Direction.NORTH);
        Location l2 = new Location(new Point(x, y), Direction.EAST);

        assertThat(l1.equals(l2)).isFalse();

    }

    public void givenDifferentXWhenEqualsThenFalse() {

        Location location1 = new Location(new Point(2, y), Direction.NORTH);
        Location location2 = new Location(new Point(5, y), Direction.NORTH);


        assertThat(location1.equals(location2)).isFalse();


    }

    public void givenDifferentYWhenEqualsThenFalse() {

        Location location1 = new Location(new Point(x, 32), Direction.NORTH);
        Location location2 = new Location(new Point(x, 33), Direction.NORTH);


        assertThat(location1.equals(location2)).isFalse();

    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {

        Location location1 = new Location(new Point(x, y), Direction.NORTH);
        Location location2 = new Location(new Point(x, y), Direction.EAST);


        assertThat(location1.equals(location2)).isFalse();

    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {

        Location location1 = new Location(new Point(5, 32), Direction.NORTH);
        Location location2 = new Location(new Point(5, 32), Direction.NORTH);


        assertThat(location1.equals(location2)).isTrue();

    }

    public void whenCopyThenDifferentObject() {

        //seteamos location1
        Location location1 = new Location(new Point(5, 32), Direction.NORTH);

        //copiamos
        Location locationCopia = location1.copy();

        assertThat(location1).isNotSameAs(locationCopia);

    }

    public void whenCopyThenEquals() {

        //seteamos location1
        Location location1 = new Location(new Point(5, 32), Direction.NORTH);

        //copiamos
        Location locationCopia = location1.copy();

        assertThat(location1.equals(locationCopia));

    }

    /*public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {

        // Set location with direction east and X at max (50)
        Location location = new Location(new Point(50, 32), Direction.EAST);

        // Move forward (should wrap around to 1)
        assertThat(location.forward()).isTrue();
        assertThat(location.getX()).isEqualTo(1);


    }

   /* public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {



    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {

    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {

    }

    public void givenObstacleWhenForwardThenReturnFalse() {



    }

    public void givenObstacleWhenBackwardThenReturnFalse() {

    }*/

}
