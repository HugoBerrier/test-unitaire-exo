package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Tests unitaires de RechercheVille")
class RechercheVilleTest {

    private static final List<String> VILLES = List.of(
            "Paris", "Budapest", "Skopje", "Rotterdam", "Valence", "Vancouver",
            "Amsterdam", "Vienne", "Sydney", "New York", "Londres", "Bangkok",
            "Hong Kong", "Dubaï", "Rome", "Istanbul"
    );

    private RechercheVille rechercheVille;

    @BeforeEach
    void setUp() {
        rechercheVille = new RechercheVille(VILLES);
    }

    @Test
    @DisplayName("Etape 1 - moins de 2 caracteres : NotFoundException")
    void shouldThrowNotFoundExceptionWhenSearchTextHasLessThanTwoCharacters() {
        assertThrows(NotFoundException.class, () -> rechercheVille.rechercher(""));
        assertThrows(NotFoundException.class, () -> rechercheVille.rechercher("a"));
        assertThrows(NotFoundException.class, () -> rechercheVille.rechercher(null));
    }

    @Test
    @DisplayName("Etape 2 - villes commencant par le texte de recherche")
    void shouldReturnCitiesStartingWithSearchText() {
        assertEquals(List.of("Valence", "Vancouver"), rechercheVille.rechercher("Va"));
    }

    @Test
    @DisplayName("Etape 3 - recherche insensible a la casse")
    void shouldReturnCitiesIgnoringCase() {
        assertEquals(List.of("Valence", "Vancouver"), rechercheVille.rechercher("va"));
    }

    @Test
    @DisplayName("Etape 4 - recherche sur une partie du nom de ville")
    void shouldReturnCitiesContainingSearchText() {
        assertEquals(List.of("Budapest"), rechercheVille.rechercher("ape"));
    }

    @Test
    @DisplayName("Etape 5 - asterisque renvoie toutes les villes")
    void shouldReturnAllCitiesWhenSearchTextIsAsterisk() {
        assertEquals(VILLES, rechercheVille.rechercher("*"));
    }
}
