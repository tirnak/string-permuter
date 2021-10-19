package collations;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Locale;
import java.util.Map;
import lombok.val;

/**
 * Class with custom rules for string comparison.
 *
 * NB! This class must be mentioned, and therefore, loaded, before any string compairing classes are loaded.
 *
 * To add new collation - add new key and value to the CUSTOM_RULES.
 *
 * TODO rewrite the logic to import collations as files from resources folder.
 */
public class CustomCollationsBootstrap {
    private static Map<String, String> CUSTOM_RULES = Map.of(
        "es_ES", "< a,A < b,B < c,C " +
                "< ch, cH, Ch, CH " +
                "< d,D < e,E < f,F " +
                "< g,G < h,H < i,I < j,J < k,K < l,L " +
                "< ll, lL, Ll, LL " +
                "< m,M < n,N " +
                "< " + "\u00F1" /* small n tilde */ + "," + "\u00D1" /* capital  N tilde*/ + " " +
                "< o,O < p,P < q,Q < r,R " +
                "< s,S < t,T < u,U < v,V < w,W < x,X " +
                "< y,Y < z,Z"
    );

    @SuppressWarnings("unchecked")
    public static void loadExtraCollations() {
        try {
            Field field = Collator.class.getDeclaredField("cache");
            field.setAccessible(true);
            val cache = (Map<Locale, SoftReference<Collator>>) field.get(Collator.getInstance());

            for (val entry : CUSTOM_RULES.entrySet()) {
                val localeKeys= entry.getKey().split("_");
                val locale = new Locale(localeKeys[0], localeKeys[1]);
                cache.put(locale, new SoftReference<>(new RuleBasedCollator(entry.getValue())));
            }
        } catch (NoSuchFieldException | IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }
    }
}
