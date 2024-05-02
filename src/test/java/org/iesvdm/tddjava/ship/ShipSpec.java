package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {

        assertThat(ship.getLocation()).isEqualTo(location);
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);

    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {

        //movemos el barco
        ship.moveForward();
        //y=13
        assertThat(ship.getLocation().getPoint().getY()).isEqualTo(12);

    }

    public void whenMoveBackwardThenBackward() {

        ship.moveBackward();
        assertThat(ship.getLocation().getPoint().getY()).isEqualTo(14);

    }

    public void whenTurnLeftThenLeft() {
        //Cuando giramos izquierda, mirando al norte -->oeste
        ship.turnLeft();
        assertThat(ship.getLocation().getDirection()).isEqualTo(Direction.WEST);

    }

    public void whenTurnRightThenRight() {

        //Cuando giramos derecha, mirando al norte -->este
        ship.turnRight();
        assertThat(ship.getLocation().getDirection()).isEqualTo(Direction.EAST);

    }

    public void whenReceiveCommandsFThenForward() {

        //damos orden F --> adelante
        ship.receiveCommands("f");//minúscula, si no no vale
        assertThat(ship.getLocation().getPoint().getY()).isEqualTo(12);

    }

    public void whenReceiveCommandsBThenBackward() {

        //damos orden B --> adelante
        ship.receiveCommands("b");//minúscula, si no no vale
        assertThat(ship.getLocation().getPoint().getY()).isEqualTo(14);
    }

    public void whenReceiveCommandsLThenTurnLeft() {

        //reutilizamos código
        ship.receiveCommands("l");
        assertThat(ship.getLocation().getDirection()).isEqualTo(Direction.WEST);


    }

    public void whenReceiveCommandsRThenTurnRight() {

        //reutilizamos código
        ship.receiveCommands("r");
        assertThat(ship.getLocation().getDirection()).isEqualTo(Direction.EAST);

    }

    public void whenReceiveCommandsThenAllAreExecuted() {

    }

    public void whenInstantiatedThenPlanetIsStored() {

        assertThat(ship.getPlanet()).isEqualTo(planet);
//        Point max = new Point(50, 50);
//        Planet planet = new Planet(max);
//        ship = new Ship(location, planet);

    }

   /* public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {

        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(50);

        ship.receiveCommands("F");

        assertThat(ship.getLocation().getPoint().getX()).isEqualTo(1);

    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {

    }

    public void whenReceiveCommandsThenStopOnObstacle() {
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {

    }*/

}
