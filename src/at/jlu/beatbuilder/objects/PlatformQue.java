package at.jlu.beatbuilder.objects;

import java.util.ArrayList;

public class PlatformQue {
    public ArrayList<PlatformDynamic> que;

    public PlatformQue() {
        que = new ArrayList<PlatformDynamic>();
    }

    public void que(PlatformDynamic p) {
        que.add(p);
    }

    /*
    public PlatformDynamic getNext() {

    }
    */

    /*
    public PlatformDynamic remove() {

    }
    */
}
