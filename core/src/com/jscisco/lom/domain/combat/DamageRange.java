package com.jscisco.lom.domain.combat;

public class DamageRange {
    int minimum;
    int maximum;
    Damage.Type type;

    private DamageRange(int minimum, int maximum, Damage.Type type) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.type = type;
    }

    public static DamageRange between(int minimum, int maximum, Damage.Type type) {
        return new DamageRange(minimum, maximum, type);
    }

    public Damage getDamage() {
        return new Damage(this.maximum + this.minimum / 2, this.type);
    }

}
