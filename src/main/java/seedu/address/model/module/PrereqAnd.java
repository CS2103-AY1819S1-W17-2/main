package seedu.address.model.module;

import java.util.Arrays;

public class PrereqAnd {
    private String[] or;

    public String[] getOr ()
    {
        return or;
    }

    public void setOr (String[] or)
    {
        this.or = or;
    }

    public void setOr (String or) {
        String[] tmpOr = new String[]{or};
        this.or = tmpOr;
    }

    @Override
    public String toString() {
        if (or != null) {
            return Arrays.toString(or);
        } else {
            return "";
        }
    }
}
