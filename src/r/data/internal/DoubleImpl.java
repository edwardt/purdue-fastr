package r.data.internal;

import r.*;
import r.data.*;

public class DoubleImpl extends ArrayImpl implements RDouble {

    double[] content;

    public DoubleImpl(double[] values, boolean doCopy) {
        if (doCopy) {
            content = new double[values.length];
            System.arraycopy(values, 0, content, 0, values.length);
        } else {
            content = values;
        }
    }
    public DoubleImpl(double[] values) {
        this(values, true);
    }

    public DoubleImpl(int size) {
        content = new double[size];
    }

    public DoubleImpl(RDouble d) {
        content = new double[d.size()];
        for (int i = 0; i < content.length; i++) {
            content[i] = d.getDouble(i);
        }
    }

    @Override
    public int size() {
        return content.length;
    }

    public Object get(int i) {
        return content[i];
    }

    public RAny boxedGet(int i) {
        return RDoubleFactory.getScalar(getDouble(i));
    }

    @Override
    public RArray set(int i, Object val) {
        return set(i, ((Double) val).doubleValue()); // FIXME better conversion
    }

    @Override
    public RArray set(int i, double val) {
        content[i] = val;
        return this;
    }

    public double getDouble(int i) {
        return content[i];
    }

    @Override
    public RInt asInt() {
        return new RDouble.RIntView(this);
    }

    @Override
    public RDouble asDouble() {
        return this;
    }

    public String pretty() {
        if (content.length == 0) {
            return RDouble.TYPE_STRING + "(0)";
        }
        String fst = Convert.double2string(content[0]);
        if (content.length == 1) {
            return fst;
        }
        StringBuilder str = new StringBuilder();
        str.append(fst);
        for (int i = 1; i < content.length; i++) {
            str.append(", ");
            str.append(Convert.double2string(content[i]));
        }
        return str.toString();
    }

    @Override
    public RLogical asLogical() {
        Utils.nyi();
        return null;
    }
}
