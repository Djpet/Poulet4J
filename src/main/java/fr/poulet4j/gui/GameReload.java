package fr.poulet4j.gui;

import java.io.File;
import java.util.List;

public interface GameReload<T> {

    List<T> loadFile(final File file);
}
