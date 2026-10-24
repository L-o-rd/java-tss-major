public final class ScoreChecker {

    /**
     * Determină dacă o echipă poate obține exact un anumit punctaj
     * folosind un număr limitat de jocuri.
     *
     * Echipa poate obține 3 sau 5 puncte pentru fiecare joc.
     * Dacă parametrul {@code allowTwoPointPlay} este {@code true},
     * echipa poate obține și 2 puncte pentru un joc.
     *
     * Metoda primește punctajul țintă, numărul maxim de jocuri
     * permise și un indicator care precizează dacă sunt permise
     * jocurile de 2 puncte. Punctajul trebuie obținut exact,
     * utilizând cel mult numărul maxim de jocuri specificat.
     *
     * Parametrii numerici trebuie să respecte următoarele intervale:
     * {@code targetScore} trebuie să fie între 1 și 500, inclusiv, iar
     * {@code maxPlays} trebuie să fie între 1 și 100, inclusiv.
     * Dacă unul dintre parametri nu respectă aceste limite,
     * metoda returnează un mesaj care indică datele invalide.
     *
     * @param targetScore punctajul exact pe care echipa trebuie să îl obțină
     * @param maxPlays numărul maxim de jocuri permise
     * @param allowTwoPointPlay indică dacă sunt permise jocurile de 2 puncte
     * @return {@code "Da"} dacă punctajul poate fi obținut în condițiile date,
     *         {@code "Nu"} dacă acest lucru nu este posibil sau un mesaj
     *         de eroare dacă parametrii numerici sunt invalizi
     */
    public static String check(int targetScore, int maxPlays, boolean allowTwoPointPlay) {
        if ((targetScore < 1) || (targetScore > 500)) {
            return "Scorul nu este valid";
        } else if ((maxPlays < 1) || (maxPlays > 100)) {
            return "Numărul de jocuri nu este valid";
        }

        int[] scoringOptions;
        if (allowTwoPointPlay) {
            scoringOptions = new int[]{2, 3, 5};
        } else {
            scoringOptions = new int[]{3, 5};
        }

        /*
         * reachable[p][s] = scorul `s` poate fi atins
         * în maxim `p` jocuri.
         * */

        boolean[][] reachable =
                new boolean[maxPlays + 1][targetScore + 1];

        reachable[0][0] = true;
        int plays = 1;

        while (plays <= maxPlays) {
            for (int score = 0; score <= targetScore; score++) {
                if (reachable[plays - 1][score]) {
                    reachable[plays][score] = true;
                }

                for (int points : scoringOptions) {
                    int previousScore = score - points;
                    if ((previousScore < 0) || !reachable[plays - 1][previousScore]) {
                        continue;
                    }

                    reachable[plays][score] = true;
                    if (score == targetScore) {
                        break;
                    }
                }
            }

            if (reachable[plays][targetScore]) {
                return "Da";
            } else {
                plays++;
            }
        }

        return "Nu";
    }
}