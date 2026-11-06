package semester1.practice09.homework;

class BioMonitor {
    private int hp;
    private final int maxHp;
    private int humanity;
    private boolean cyberpsychosis;

    public BioMonitor(int maxHp) {
        if (maxHp > 0) {
            this.maxHp = maxHp;
        } else {
            this.maxHp = 100;
        }
        this.hp = this.maxHp;
        this.humanity = 100;
        this.cyberpsychosis = false;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHumanity() {
        return humanity;
    }

    public boolean isCyberpsychosis() {
        return cyberpsychosis;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void reduceHumanity(int cost) {
        if (cost <= 0) {
            return;
        }
        humanity = Math.max(0, humanity - cost);
        if (humanity < 20) {
            cyberpsychosis = true;
        }
    }

    public void injectImmunoblockers(int restoreAmount) {
        if (restoreAmount <= 0) {
            return;
        }
        humanity = Math.min(100, humanity + restoreAmount);
        if (humanity >= 20) {
            cyberpsychosis = false;
        }
    }

    public int applyDamage(int rawDamage, int totalArmor) {
        int damage = Math.max(5, rawDamage - totalArmor);
        hp = Math.max(0, hp - damage);
        return damage;
    }

    public int applyCyberpsychosisBurn() {
        if (!cyberpsychosis) {
            return 0;
        }
        int burn = maxHp / 10;
        hp = Math.max(0, hp - burn);
        return burn;
    }

    private String buildBar(int value, int max) {
        int filled = value * 10 / max;
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
        String state;
        if (cyberpsychosis) {
            state = "🔥 CYBERPSYCHOSIS";
        } else {
            state = "Стабільний";
        }
        return String.format(
                "HP %s %d/%d | Humanity: %s %d/100 [%s]",
                buildBar(hp, maxHp),
                hp,
                maxHp,
                buildBar(humanity, 100),
                humanity,
                state
        );
    }
}

abstract class AbstractCyberImplant {
    private final String name;
    private final String slot;
    private final int humanityCost;

    public AbstractCyberImplant(String name, String slot, int humanityCost) {
        this.name = name;
        this.slot = slot;
        if (humanityCost >= 0) {
            this.humanityCost = humanityCost;
        } else {
            this.humanityCost = 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getSlot() {
        return slot;
    }

    public int getHumanityCost() {
        return humanityCost;
    }

    public abstract int getAttackBonus();

    public abstract int getArmorBonus();

    public abstract String activateSpecialAbility();

    @Override
    public String toString() {
        return String.format(
                "[%s] %s (+%d ATK, +%d DEF, -%d Hum)",
                slot,
                name,
                getAttackBonus(),
                getArmorBonus(),
                humanityCost
        );
    }
}

class SandevistanOs extends AbstractCyberImplant {
    private final int timeSlowPercent;

    public SandevistanOs() {
        super("Militech Sandevistan Apogee", "Нейро-ОС", 35);
        this.timeSlowPercent = 85;
    }

    @Override
    public int getAttackBonus() {
        return 90;
    }

    @Override
    public int getArmorBonus() {
        return 20;
    }

    @Override
    public String activateSpecialAbility() {
        return "⚡ САНДЕВІСТАН АКТИВОВАНО: Час сповільнено на " + timeSlowPercent
                + "%! Вороги завмерли на місці!";
    }
}

class MantisBlades extends AbstractCyberImplant {
    private final int thermalDamage;

    public MantisBlades() {
        super("Термальні Клинки Богомола", "Руки", 25);
        this.thermalDamage = 110;
    }

    @Override
    public int getAttackBonus() {
        return thermalDamage;
    }

    @Override
    public int getArmorBonus() {
        return 5;
    }

    @Override
    public String activateSpecialAbility() {
        return "🔥 Вихор розпечених клинків Богомола розтинає броню ворога!";
    }
}

class SubdermalTitaniumArmor extends AbstractCyberImplant {
    private final int armorPlating;

    public SubdermalTitaniumArmor() {
        super("Підшкірна Титанова Броня", "Шкіра", 20);
        this.armorPlating = 65;
    }

    @Override
    public int getAttackBonus() {
        return 10;
    }

    @Override
    public int getArmorBonus() {
        return armorPlating;
    }

    @Override
    public String activateSpecialAbility() {
        return "🛡️ Нано-пластини поглинають кінетичний удар!";
    }
}

class KiroshiOptics extends AbstractCyberImplant {
    private final int zoomLevel;

    public KiroshiOptics() {
        super("Оптика Kiroshi Sentinel", "Оптика", 15);
        this.zoomLevel = 8;
    }

    @Override
    public int getAttackBonus() {
        return 25;
    }

    @Override
    public int getArmorBonus() {
        return 0;
    }

    @Override
    public String activateSpecialAbility() {
        return "👁️ Оптика Kiroshi (x" + zoomLevel + "): слабкі місця ворога підсвічено!";
    }
}

class CyberMercenary {
    private final String nickname;
    private final int baseAttack;
    private final int baseArmor;
    private final BioMonitor bioMonitor;
    private final AbstractCyberImplant[] implants;
    private int implantCount;

    public CyberMercenary(String nickname, int baseAttack, int baseArmor, int maxHp) {
        this.nickname = nickname;
        this.baseAttack = Math.max(0, baseAttack);
        this.baseArmor = Math.max(0, baseArmor);
        this.bioMonitor = new BioMonitor(maxHp);
        this.implants = new AbstractCyberImplant[4];
        this.implantCount = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public BioMonitor getBioMonitor() {
        return bioMonitor;
    }

    public boolean installImplant(AbstractCyberImplant implant) {
        if (implant == null) {
            System.out.println("❌ Помилка: імплант не передано!");
            return false;
        }
        if (implantCount >= implants.length) {
            System.out.println("❌ Помилка: усі слоти для хрому зайняті!");
            return false;
        }
        for (int i = 0; i < implantCount; i++) {
            if (implants[i].getSlot().equals(implant.getSlot())) {
                System.out.println("❌ Помилка слота: Слот [" + implant.getSlot()
                        + "] вже зайнятий імплантом '" + implants[i].getName() + "'!");
                return false;
            }
        }
        implants[implantCount] = implant;
        implantCount++;
        bioMonitor.reduceHumanity(implant.getHumanityCost());
        System.out.println("🔧 Встановлено [" + implant.getSlot() + "]: " + implant.getName()
                + " (-" + implant.getHumanityCost() + " Людяності -> залишок: "
                + bioMonitor.getHumanity() + ")");
        if (bioMonitor.isCyberpsychosis()) {
            System.out.println("⚠️ [КРИТИЧНЕ ПОПЕРЕДЖЕННЯ BIO-MONITOR]: Людяність впала до "
                    + bioMonitor.getHumanity()
                    + "/100! Стан: КІБЕРПСИХОЗ (Атака x1.5, саморуйнування!)");
        }
        return true;
    }

    public int getImplantsAttack() {
        int sum = 0;
        for (int i = 0; i < implantCount; i++) {
            sum += implants[i].getAttackBonus();
        }
        return sum;
    }

    public int getTotalAttack() {
        int total = baseAttack + getImplantsAttack();
        if (bioMonitor.isCyberpsychosis()) {
            total = (int) (total * 1.5);
        }
        return total;
    }

    public int getTotalArmor() {
        int total = baseArmor;
        for (int i = 0; i < implantCount; i++) {
            total += implants[i].getArmorBonus();
        }
        return total;
    }

    public void activateAllImplants() {
        for (int i = 0; i < implantCount; i++) {
            System.out.println(implants[i].activateSpecialAbility());
        }
    }

    public int takeHit(int rawDamage) {
        return bioMonitor.applyDamage(rawDamage, getTotalArmor());
    }

    public void endTurn() {
        int burn = bioMonitor.applyCyberpsychosisBurn();
        if (burn > 0) {
            System.out.println("🧠 Перевантаження нервової системи! " + nickname
                    + " втрачає " + burn + " HP через кіберпсихоз.");
        }
    }

    public void useImmunoblockers(int amount) {
        bioMonitor.injectImmunoblockers(amount);
        String result;
        if (bioMonitor.isCyberpsychosis()) {
            result = "Кіберпсихоз не знято!";
        } else {
            result = "Нейро-стабільність відновлено!";
        }
        System.out.println("💉 Введено Імуноблокатор (+" + amount + " Людяності -> "
                + bioMonitor.getHumanity() + "/100). " + result);
    }

    @Override
    public String toString() {
        String rage = "";
        if (bioMonitor.isCyberpsychosis()) {
            rage = ", лють x1.5";
        }
        String result = String.format(
                "CyberMercenary{nick='%s', TotalATK=%d (База: %d + Імпланти: %d%s), TotalDEF=%d}%n",
                nickname,
                getTotalAttack(),
                baseAttack,
                getImplantsAttack(),
                rage,
                getTotalArmor()
        );
        result += "  ├─ BioMonitor: " + bioMonitor + "\n";
        result += "  └─ Встановлений Хром (" + implantCount + "/" + implants.length + "):";
        for (int i = 0; i < implantCount; i++) {
            result += "\n       " + (i + 1) + ". " + implants[i];
        }
        return result;
    }
}

class ArasakaBoss {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attack;
    private final int armor;

    public ArasakaBoss(String name, int maxHp, int attack, int armor) {
        this.name = name;
        this.maxHp = Math.max(1, maxHp);
        this.hp = this.maxHp;
        this.attack = Math.max(0, attack);
        this.armor = Math.max(0, armor);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int receiveDamage(int rawDamage) {
        int damage = Math.max(5, rawDamage - armor);
        hp = Math.max(0, hp - damage);
        return damage;
    }
}

public class Task5CyberpunkRipperdocSim {
    public static void main(String[] args) {
        System.out.println("================ 🦾 CYBERPUNK 2077: КЛІНІКА РІППЕРДОКА 🦾 ================");
        CyberMercenary david = new CyberMercenary("Девід Мартінес", 120, 10, 1000);
        System.out.println("Пацієнт: " + david.getNickname() + " | BioMonitor: " + david.getBioMonitor());

        System.out.println();
        System.out.println("[ХІРУРГІЧНЕ ВСТАНОВЛЕННЯ ІМПЛАНТІВ]:");
        david.installImplant(new SandevistanOs());
        david.installImplant(new MantisBlades());
        david.installImplant(new SandevistanOs());
        david.installImplant(new SubdermalTitaniumArmor());
        david.installImplant(new KiroshiOptics());

        System.out.println();
        System.out.println("[ДОСЬЄ НАЙМАНЦЯ ПЕРЕД БОЄМ]:");
        System.out.println(david);

        System.out.println();
        System.out.println("--- ⚔️ БІЙ НА ВЕРШИНІ ARASAKA TOWER ПРОТИ АДАМА СМЕШЕРА ⚔️ ---");
        ArasakaBoss smasher = new ArasakaBoss("Адам Смешер", 1400, 300, 80);

        int round = 1;
        while (round <= 4 && smasher.isAlive() && david.getBioMonitor().isAlive()) {
            System.out.println();
            System.out.println("[РАУНД " + round + "]");
            if (round == 1) {
                david.activateAllImplants();
            }

            int attack = david.getTotalAttack();
            int dealt = smasher.receiveDamage(attack);
            System.out.println("💥 " + david.getNickname() + " завдає " + attack + " шкоди! "
                    + smasher.getName() + " втрачає " + dealt + " HP! (Залишок: "
                    + smasher.getHp() + "/" + smasher.getMaxHp() + ")");

            if (smasher.isAlive()) {
                int received = david.takeHit(smasher.getAttack());
                System.out.println("🤖 " + smasher.getName() + " б'є з ракетниці! "
                        + david.getNickname() + " втрачає " + received + " HP.");
            }

            david.endTurn();

            if (round == 2 && david.getBioMonitor().isCyberpsychosis()) {
                david.useImmunoblockers(30);
            }

            System.out.println("   " + david.getBioMonitor());
            round++;
        }

        System.out.println();
        if (!smasher.isAlive()) {
            System.out.println("🏆 " + smasher.getName() + " переможений! Найт-Сіті запам'ятає легенду: "
                    + david.getNickname() + "!");
        } else if (!david.getBioMonitor().isAlive()) {
            System.out.println("☠️ " + david.getNickname() + " загинув у бою...");
        } else {
            System.out.println("⏳ Бій триває: " + smasher.getName() + " ще тримається ("
                    + smasher.getHp() + " HP).");
        }
        System.out.println("===========================================================================");
    }
}
