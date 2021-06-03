package fixtures;

import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Hero;

public class EntityFactory {

    public static Hero testHero() {
        Hero h = new Hero.Builder()
                .withPosition(Position.of(1, 1))
                .withName(Name.of("Test Hero"))
                .build();
        return h;
    }

}
