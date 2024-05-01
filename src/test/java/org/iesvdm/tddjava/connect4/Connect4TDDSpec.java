package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {

        assertThat(tested.getNumberOfDiscs()).isEqualTo(0).withFailMessage("The number of disc must be 0");

    }


    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {

        assertThatThrownBy(() -> {tested.putDiscInColumn(8);})
                .isInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> {tested.putDiscInColumn(-1);})
                .isInstanceOf(RuntimeException.class);


    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {

        tested.putDiscInColumn(1);//ponemos primer disco en columna 1
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);

    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {

                tested.putDiscInColumn(1);//ponemos segundo disco en columna 1
                assertThat(tested.putDiscInColumn(1)).isEqualTo(1);

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {

        for (int i=0; i<7; i++){
            tested.putDiscInColumn(i);//ponemos disco en columna
        assertThat(tested.getNumberOfDiscs()).isEqualTo(i+1);//verificamos incremento

        }

    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        //Si se inserta uno  mas de 42(numMax) --> mensaje
       if (tested.getNumberOfDiscs()>42){

           assertThatThrownBy(() -> {tested.putDiscInColumn(1);})
                   .isInstanceOf(RuntimeException.class).hasMessage("No more room in column %d");;

       }



    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {


        assertThat(tested.getCurrentPlayer()).isEqualTo("R");

    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {

        tested.putDiscInColumn(1);//después del primer jugador
        assertThat(tested.getCurrentPlayer()).isEqualTo("G");

    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {

        String currentPlayer = tested.getCurrentPlayer();
        String mensajeOutput = String.format("Player %s turn%n", currentPlayer);
        assertThat(output.toString()).isEqualTo(mensajeOutput);

    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {

            tested.putDiscInColumn(0);
//            System.out.println(output.toString());

            String expected = """ 
                              | | | | | | | | 
                              | | | | | | | | 
                              | | | | | | | | 
                              | | | | | | | | 
                              | | | | | | | | 
                              |R| | | | | | | 
                              """;

            assertThat(output.toString()).isEqualTo(expected);


        tested.putDiscInColumn(0);

        String expected2 = """ 
                              | | | | | | | | 
                              | | | | | | | | 
                              | | | | | | | | 
                              | | | | | | | | 
                              |G| | | | | | | 
                              |R| | | | | | | 
                              """;

        assertThat(output.toString()).isEqualTo(expected+expected2);


    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {



            assertThat(tested.isFinished()).isEqualTo(false);




    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {


            assertThat(tested.isFinished()).isEqualTo(true);


    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {

        //Red es el primero en poner siempre -- Hago que gane R

        String currentPlayer = tested.getCurrentPlayer();//Para luego hacer el equals

        // simulo partida

        tested.putDiscInColumn(0);

        tested.putDiscInColumn(1);//switch

        tested.putDiscInColumn(0);

        tested.putDiscInColumn(2);

        tested.putDiscInColumn(0);

        tested.putDiscInColumn(2);

        tested.putDiscInColumn(0);


        // ganador
        assertThat(tested.getWinner()).isEqualTo(currentPlayer).isEqualTo("R");





    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {

        //Red es el primero en poner siempre -- Hago que gane R

        String currentPlayer = tested.getCurrentPlayer();//Para luego hacer el equals

        // simulo partida

        tested.putDiscInColumn(0);

        tested.putDiscInColumn(0);//switch

        tested.putDiscInColumn(1);

        tested.putDiscInColumn(1);

        tested.putDiscInColumn(2);

        tested.putDiscInColumn(2);

        tested.putDiscInColumn(3);


        // ganador
        assertThat(tested.getWinner()).isEqualTo(currentPlayer).isEqualTo("R");


    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {

        //Red es el primero en poner siempre -- Hago que gane R

        String currentPlayer = tested.getCurrentPlayer();//Para luego hacer el equals

        // simulo partida


        tested.putDiscInColumn(0);


        tested.putDiscInColumn(1);


        tested.putDiscInColumn(1);


        tested.putDiscInColumn(2);


        tested.putDiscInColumn(2);


        tested.putDiscInColumn(3);


        tested.putDiscInColumn(2);


        tested.putDiscInColumn(3);


        tested.putDiscInColumn(3);


        tested.putDiscInColumn(1);


        tested.putDiscInColumn(3);

    // ganador
        assertThat(tested.getWinner()).isEqualTo(currentPlayer).isEqualTo("R");


    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {


        //Red es el primero en poner siempre -- Hago que gane R

        String currentPlayer = tested.getCurrentPlayer();//Para luego hacer el equals

        // simulo partida


        tested.putDiscInColumn(0);


        tested.putDiscInColumn(1);


        tested.putDiscInColumn(1);


        tested.putDiscInColumn(2);


        tested.putDiscInColumn(2);


        tested.putDiscInColumn(3);


        tested.putDiscInColumn(2);


        tested.putDiscInColumn(3);


        tested.putDiscInColumn(3);


        tested.putDiscInColumn(1);


        tested.putDiscInColumn(3);

        // ganador
        assertThat(tested.getWinner()).isEqualTo(currentPlayer).isEqualTo("R");
    }
    }
