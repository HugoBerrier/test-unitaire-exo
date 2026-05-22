package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires de DiceScore")
class DiceScoreTest {

    @Mock
    private Ide de;

    private DiceScore diceScore;

    @BeforeEach
    void setUp() {
        diceScore = new DiceScore(de);
    }

    @Test
    @DisplayName("Deux dés identiques : valeur du dé * 2 + 10")
    void shouldReturnDoubleValuePlusTenWhenBothRollsAreEqual() {
        when(de.getRoll()).thenReturn(4, 4);

        int score = diceScore.getScore();

        assertEquals(18, score);
        verify(de, times(2)).getRoll();
    }

    @Test
    @DisplayName("Deux dés identiques à 6 : score de 30")
    void shouldReturnThirtyWhenBothRollsAreSix() {
        when(de.getRoll()).thenReturn(6, 6);

        int score = diceScore.getScore();

        assertEquals(30, score);
        verify(de, times(2)).getRoll();
    }

    @Test
    @DisplayName("Deux dés différents : valeur du plus haut des deux")
    void shouldReturnHighestRollWhenRollsAreDifferent() {
        when(de.getRoll()).thenReturn(2, 5);

        int score = diceScore.getScore();

        assertEquals(5, score);
        verify(de, times(2)).getRoll();
    }

    @Test
    @DisplayName("Deux dés différents (ordre inversé) : valeur du plus haut des deux")
    void shouldReturnHighestRollWhenFirstRollIsHigher() {
        when(de.getRoll()).thenReturn(5, 2);

        int score = diceScore.getScore();

        assertEquals(5, score);
        verify(de, times(2)).getRoll();
    }
}
