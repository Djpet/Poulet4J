package fr.poulet4j.gui;

/**
 * Retourne les infos des ias
 * @param <T>
 */
public interface InfosIaBuilder<T> {

    /**
     * Return un tableau de 3 Strings avec les infos des IA.
     * @param t : objet du tour courant
     * @return 3 strings (ia 1, ia 2, poulet)
     */
    public String[] getInfos(T t);
}
