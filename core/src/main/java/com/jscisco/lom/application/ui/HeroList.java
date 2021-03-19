package com.jscisco.lom.application.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.domain.entity.Hero;

import java.util.List;

//TODO: Finish this for handling various lists of heroes
// TODO: Can this be genericized for lists of items, enemies, spells, etc.?
public class HeroList extends Table {

//    Table table = new Table(GameConfiguration.getSkin());
//        for (
//    Hero p : heroes) {
//        table.add(new HeroBlock(p)).top();
////            table.add(new Label(p.getName().getName(), table.getSkin(), "default")).top().center();
//        table.row();
//    }
//    Table scrollTable = new Table(GameConfiguration.getSkin());
//        scrollTable.setFillParent(true);
//    ScrollPane scroller = new ScrollPane(table, GameConfiguration.getSkin());
//        scroller.setFadeScrollBars(false);
//    float width = 350f;
//        scrollTable.add(scroller)
//            .width(width)
//                .expandY();
//        return scrollTable;

    private List<Hero> heroes;

    public HeroList(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
