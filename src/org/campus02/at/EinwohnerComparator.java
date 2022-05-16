package org.campus02.at;

import java.util.Comparator;

public class EinwohnerComparator implements Comparator<Einwohner> {
    @Override
    public int compare(Einwohner e1, Einwohner e2) {
        int result = Integer.compare(e2.getGeburtsjahr(), e1.getGeburtsjahr());
        if(result == 0){
            result = e1.getName().compareTo(e2.getName());
        }
        return result;
    }
}
