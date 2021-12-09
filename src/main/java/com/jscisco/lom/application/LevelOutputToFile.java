package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Floor;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.StairsDown;
import com.jscisco.lom.domain.zone.StairsUp;
import com.jscisco.lom.domain.zone.Tile;
import com.jscisco.lom.domain.zone.Wall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelOutputToFile {

    private static final Logger logger = LoggerFactory.getLogger(LevelOutputToFile.class);

    public static void outputToFile(Level level, String filename) {
        // write line
        FileHandle handle = Gdx.files.external(filename);
        StringBuilder sb = new StringBuilder();
        for (int j = level.getHeight() - 1; j >= 0; j--) {
            for (int i = 0; i < level.getWidth(); i++) {
                Tile t = level.getTileAt(Position.of(i, j));
                if (t.getFeature() instanceof Wall) {
                    sb.append("#");
                }
                if (t.getFeature() instanceof Floor) {
                    sb.append(".");
                }
                if (t.getFeature() instanceof StairsUp) {
                    sb.append("<");
                }
                if (t.getFeature() instanceof StairsDown) {
                    sb.append(">");
                }
            }
            sb.append("\n");
        }
        handle.writeString(sb.toString(), false);
    }
}
