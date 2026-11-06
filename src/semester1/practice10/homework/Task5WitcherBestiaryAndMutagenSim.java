package semester1.practice10.homework;

interface FlyingMonster {
    boolean isAirborne();

    void knockDownToGround();
}

interface RegeneratingMonster {
    int regenerateHealth();

    void applyMoonDustBomb();

    boolean isRegenerationBlocked();
}

interface SpectralPhantom {
    boolean isIncorporeal();

    void bindWithYrdenSign();
}

abstract class AbstractMonster {
    private final String name;
    private final String bestiaryCategory;
    protected int hp;
    protected final int maxHp;

    public AbstractMonster(String name, String bestiaryCategory, int maxHp) {
        this.name = name;
        this.bestiaryCategory = bestiaryCategory;
        if (maxHp > 0) {
            this.maxHp = maxHp;
        } else {
            this.maxHp = 100;
        }
        this.hp = this.maxHp;
    }

    public String getName() {
        return name;
    }

    public String getBestiaryCategory() {
        return bestiaryCategory;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public abstract int calculateMonsterAttack();

    public abstract String getBattleCry();

    public final boolean isAlive() {
        return hp > 0;
    }

    public void receiveDamage(int dmg) {
        if (dmg > 0) {
            hp = Math.max(0, hp - dmg);
        }
    }

    private String buildHpBar() {
        int filled = hp * 10 / maxHp;
        String bar = "";
        for (int i = 0; i < 10; i++) {
            if (i < filled) {
                bar += "█";
            } else {
                bar += "░";
            }
        }
        return "[" + bar + "]";
    }

    @Override
    public String toString() {
        return String.format(
                "%s [%s | HP: %s %d/%d]",
                name,
                bestiaryCategory,
                buildHpBar(),
                hp,
                maxHp
        );
    }
}

class RoyalGriffin extends AbstractMonster implements FlyingMonster {
    private boolean airborne;

    public RoyalGriffin() {
        super("Королівський Грифон", "Гібриди", 400);
        this.airborne = true;
    }

    @Override
    public boolean isAirborne() {
        return airborne;
    }

    @Override
    public void knockDownToGround() {
        airborne = false;
    }

    @Override
    public int calculateMonsterAttack() {
        return 90;
    }

    @Override
    public String getBattleCry() {
        airborne = true;
        return "🦅 Грифон з криком рве Ґеральта кігтями і знову злітає в небо!";
    }

    @Override
    public void receiveDamage(int dmg) {
        if (airborne) {
            super.receiveDamage(dmg / 2);
        } else {
            super.receiveDamage(dmg);
        }
    }
}

class Noonwraith extends AbstractMonster implements SpectralPhantom {
    private boolean incorporeal;

    public Noonwraith() {
        super("Полудниця з Білого Саду", "Примари", 300);
        this.incorporeal = true;
    }

    @Override
    public boolean isIncorporeal() {
        return incorporeal;
    }

    @Override
    public void bindWithYrdenSign() {
        incorporeal = false;
    }

    @Override
    public int calculateMonsterAttack() {
        return 70;
    }

    @Override
    public String getBattleCry() {
        incorporeal = true;
        return "👻 Полудниця з виттям проходить крізь Ґеральта і знову стає безтілесною!";
    }

    @Override
    public void receiveDamage(int dmg) {
        if (incorporeal) {
            super.receiveDamage(dmg / 5);
        } else {
            super.receiveDamage(dmg);
        }
    }
}

class HigherVampire extends AbstractMonster implements FlyingMonster, RegeneratingMonster {
    private boolean airborne;
    private final int bloodRegen;
    private boolean regenBlocked;

    public HigherVampire() {
        super("Вищий Вампір Детлафф", "Вампіри", 650);
        this.airborne = true;
        this.bloodRegen = 120;
        this.regenBlocked = false;
    }

    @Override
    public boolean isAirborne() {
        return airborne;
    }

    @Override
    public void knockDownToGround() {
        airborne = false;
    }

    @Override
    public int regenerateHealth() {
        if (regenBlocked || !isAlive()) {
            return 0;
        }
        int before = hp;
        hp = Math.min(maxHp, hp + bloodRegen);
        return hp - before;
    }

    @Override
    public void applyMoonDustBomb() {
        regenBlocked = true;
    }

    @Override
    public boolean isRegenerationBlocked() {
        return regenBlocked;
    }

    @Override
    public int calculateMonsterAttack() {
        return 140;
    }

    @Override
    public String getBattleCry() {
        airborne = true;
        return "🦇 Детлафф розсипається на кажанів і пікірує з туману!";
    }

    @Override
    public void receiveDamage(int dmg) {
        if (airborne) {
            super.receiveDamage(dmg / 2);
        } else {
            super.receiveDamage(dmg);
        }
    }
}

final class AncientLeshen extends AbstractMonster implements RegeneratingMonster {
    private final int forestTotems;
    private boolean regenBlocked;

    public AncientLeshen() {
        super("Прадавній Лісовик", "Релікти", 600);
        this.forestTotems = 3;
        this.regenBlocked = false;
    }

    @Override
    public int regenerateHealth() {
        if (regenBlocked || !isAlive()) {
            return 0;
        }
        int before = hp;
        hp = Math.min(maxHp, hp + forestTotems * 30);
        return hp - before;
    }

    @Override
    public void applyMoonDustBomb() {
        regenBlocked = true;
    }

    @Override
    public boolean isRegenerationBlocked() {
        return regenBlocked;
    }

    @Override
    public int calculateMonsterAttack() {
        return 110;
    }

    @Override
    public String getBattleCry() {
        return "🌲 Лісовик кличе зграю вовків і б'є корінням з-під землі!";
    }
}

class WitcherHunter {
    private final String name;
    private final String schoolName;
    private final int silverSwordAtk;
    private final int signIntensity;
    private int hp;
    private final int maxHp;

    public WitcherHunter(String name, String schoolName, int silverSwordAtk, int signIntensity, int maxHp) {
        this.name = name;
        this.schoolName = schoolName;
        this.silverSwordAtk = Math.max(1, silverSwordAtk);
        this.signIntensity = Math.max(0, signIntensity);
        this.maxHp = Math.max(1, maxHp);
        this.hp = this.maxHp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public final boolean isAlive() {
        return hp > 0;
    }

    public void receiveDamage(int dmg) {
        if (dmg > 0) {
            hp = Math.max(0, hp - dmg);
        }
    }

    public void applyBestiaryTactics(AbstractMonster target) {
        if (target instanceof FlyingMonster && target instanceof RegeneratingMonster) {
            System.out.println("🦇 Множинний контракт (FlyingMonster + RegeneratingMonster)!");
        }
        if (target instanceof SpectralPhantom sp && sp.isIncorporeal()) {
            System.out.println("👻 Примара нематеріальна (поглинає 80% ударів).");
            sp.bindWithYrdenSign();
            System.out.println("🟣 [instanceof SpectralPhantom] " + name
                    + " креслить на землі магічне коло Знаку ІРДЕН! Примару матеріалізовано!");
        }
        if (target instanceof FlyingMonster fm && fm.isAirborne()) {
            System.out.println("🪽 Монстр у повітрі! Удар мечем без підготовки завдав би лише 50% шкоди.");
            fm.knockDownToGround();
            System.out.println("💨 [instanceof FlyingMonster] " + name + " складає пальці у Знак ААРД ("
                    + signIntensity + "%)! Монстра збито на землю!");
        }
        if (target instanceof RegeneratingMonster rm && !rm.isRegenerationBlocked()) {
            rm.applyMoonDustBomb();
            System.out.println("💣 [instanceof RegeneratingMonster] " + name
                    + " підриває бомбу 'Місячний Пил'! Срібні ошурки блокують регенерацію!");
        }
    }

    private int chooseOilBonus(String category) {
        if (category.equals("Гібриди")) {
            return 30;
        } else if (category.equals("Примари")) {
            return 40;
        } else if (category.equals("Вампіри")) {
            return 110;
        } else if (category.equals("Релікти")) {
            return 50;
        }
        return 0;
    }

    public final int executeSilverStrike(AbstractMonster target) {
        if (target == null || !target.isAlive()) {
            return 0;
        }
        applyBestiaryTactics(target);
        int strike = silverSwordAtk + chooseOilBonus(target.getBestiaryCategory());
        int before = target.getHp();
        target.receiveDamage(strike);
        int dealt = before - target.getHp();
        System.out.println("⚔️ [final executeSilverStrike] Срібний меч із мастилом проти '"
                + target.getBestiaryCategory() + "' завдає " + dealt + " шкоди! (Залишок HP: "
                + target.getHp() + "/" + target.getMaxHp() + ")");
        return dealt;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Школа %s | Срібний меч: %d ATK | Інтенсивність Знаків: %d%% | HP: %d/%d)",
                name,
                schoolName,
                silverSwordAtk,
                signIntensity,
                hp,
                maxHp
        );
    }
}

public class Task5WitcherBestiaryAndMutagenSim {
    public static void main(String[] args) {
        System.out.println("================ 🐺 ВІДЬМАК 3: БЕСТІАРІЙ ТА ПОЛІМОРФНЕ ПОЛЮВАННЯ 🐺 ================");
        WitcherHunter geralt = new WitcherHunter("Ґеральт із Рівії", "Вовка", 220, 150, 1000);
        System.out.println("Мисливець: " + geralt);

        AbstractMonster[] contracts = {
                new RoyalGriffin(),
                new Noonwraith(),
                new HigherVampire(),
                new AncientLeshen()
        };

        int completed = 0;
        for (int i = 0; i < contracts.length; i++) {
            AbstractMonster monster = contracts[i];
            System.out.println();
            System.out.println("--- КОНТРАКТ №" + (i + 1) + ": " + monster + " ---");

            int round = 1;
            while (monster.isAlive() && geralt.isAlive() && round <= 5) {
                System.out.println("[Раунд " + round + "]");
                geralt.executeSilverStrike(monster);

                if (monster.isAlive()) {
                    System.out.println(monster.getBattleCry());
                    int monsterAttack = monster.calculateMonsterAttack();
                    geralt.receiveDamage(monsterAttack);
                    System.out.println("🩸 " + geralt.getName() + " втрачає " + monsterAttack
                            + " HP (залишок: " + geralt.getHp() + ")");

                    if (monster instanceof RegeneratingMonster rm) {
                        int healed = rm.regenerateHealth();
                        if (healed > 0) {
                            System.out.println("🌿 " + monster.getName() + " регенерує +" + healed + " HP!");
                        }
                    }
                }
                round++;
            }

            if (!monster.isAlive()) {
                completed++;
                System.out.println("🏆 Контракт виконано: " + monster.getName() + " — ціль знищено!");
            } else {
                System.out.println("❌ Контракт провалено: " + monster.getName() + " вижив.");
            }
        }

        System.out.println();
        System.out.println("Підсумок полювання: виконано " + completed + "/" + contracts.length + " контрактів.");
        System.out.println("Стан мисливця: " + geralt);
        System.out.println("====================================================================================");
    }
}
