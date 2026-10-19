package semester1.practice05.homework;

public class Task4DotaMatchStats {
    public static void main(String[] args) {
        String[] heroes = {"Pudge", "Invoker", "Juggernaut", "Crystal Maiden", "Earthshaker"};
        int[] kills = {8, 12, 10, 2, 4};
        int[] deaths = {6, 3, 2, 9, 5};
        int[] assists = {14, 9, 8, 18, 16};
        int[] netWorth = {14500, 21200, 24800, 9800, 12300};
        double[] kdaRatios = new double[heroes.length];
        int totalKills = 0, totalWorth = 0;
        for (int i = 0; i < heroes.length; i++) {
            totalKills += kills[i];
            totalWorth += netWorth[i];
        }
        int mvp = 0;
        System.out.println("=== DOTA 2: POST-MATCH REPORT ===");
        System.out.printf("%-17s | %-10s | %10s | %8s | %8s%n", "Hero", "K / D / A", "Net Worth", "KDA", "KP%");
        for (int i = 0; i < heroes.length; i++) {
            int divisor = deaths[i] > 0 ? deaths[i] : 1;
            kdaRatios[i] = (double) (kills[i] + assists[i]) / divisor;
            if (kdaRatios[i] > kdaRatios[mvp]) mvp = i;
            double kp = totalKills == 0 ? 0 : (double) (kills[i] + assists[i]) / totalKills * 100;
            System.out.printf("%-17s | %2d/%2d/%2d   | %10d | %8.2f | %7.2f%%%n",
                    heroes[i], kills[i], deaths[i], assists[i], netWorth[i], kdaRatios[i], kp);
        }
        System.out.println("Командні вбивства: " + totalKills);
        System.out.println("Командний Net Worth: " + totalWorth);
        System.out.printf("MVP: %s (KDA %.2f)%n", heroes[mvp], kdaRatios[mvp]);
    }
}
