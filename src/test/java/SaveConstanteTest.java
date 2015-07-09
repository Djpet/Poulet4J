import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import fr.poulet4j.save.SaveConstante;

public class SaveConstanteTest {

    @Test
    public void testCellFormat() {
        assertEquals(1, SaveConstante.TOP);
        assertEquals(2, SaveConstante.BOTTOM);
        assertEquals(4, SaveConstante.LEFT);
        assertEquals(8, SaveConstante.RIGHT);

        assertEquals(3, SaveConstante.TOP | SaveConstante.BOTTOM);
        assertEquals(5, SaveConstante.TOP | SaveConstante.LEFT);
        assertEquals(9, SaveConstante.TOP | SaveConstante.RIGHT);
        assertEquals(7, SaveConstante.TOP | SaveConstante.BOTTOM | SaveConstante.LEFT);
        assertEquals(11, SaveConstante.TOP | SaveConstante.BOTTOM | SaveConstante.RIGHT);
        assertEquals(13, SaveConstante.TOP | SaveConstante.LEFT | SaveConstante.RIGHT);

        assertEquals(6, SaveConstante.BOTTOM | SaveConstante.LEFT);
        assertEquals(10, SaveConstante.BOTTOM | SaveConstante.RIGHT);
        assertEquals(14, SaveConstante.BOTTOM | SaveConstante.LEFT | SaveConstante.RIGHT);

        assertEquals(12, SaveConstante.LEFT | SaveConstante.RIGHT);

        assertEquals(15, SaveConstante.TOP | SaveConstante.BOTTOM | SaveConstante.LEFT | SaveConstante.RIGHT);
    }

    @Test
    public void testToHexa() {
        System.out.println(StringUtils.leftPad(Integer.toHexString(1 << 1), SaveConstante.SIZE / 4, '0'));

    }
}
