package com.example;

import java.util.ArrayList;
import java.util.List;

public class RechercheVille {

    private final List<String> villes;

    public RechercheVille(List<String> villes) {
        this.villes = villes;
    }

    public List<String> rechercher(String mot) {
        if ("*".equals(mot)) {
            return new ArrayList<>(villes);
        }
        if (mot == null || mot.length() < 2) {
            throw new NotFoundException();
        }

        String search = mot.toLowerCase();
        List<String> result = new ArrayList<>();
        for (String ville : villes) {
            if (ville.toLowerCase().contains(search)) {
                result.add(ville);
            }
        }
        return result;
    }
}
