package r.data;

import r.*;
import r.Convert.*;
import r.nodes.*;
import r.nodes.truffle.*;

public final class RNull implements RAny, RAttributes {

    private static RNull instance = new RNull();

    private RNull() {
    }

    @Override
    public RAttributes getAttributes() {
        return this;
    }

    @Override
    public String pretty() {
        return "NULL";
    }

    @Override
    public Object get(int i) {
        return this; // FIXME: should return null ?
    }

    @Override
    public RAny boxedGet(int i) {
        return this;
    }

    @Override
    public boolean isNAorNaN(int i) {
        return false;
    }

    @Override
    public RArray set(int i, Object val) {
        return this;
    }

    @Override
    public RArray subset(RAny keys) {
        return this;
    }

    @Override
    public RArray subset(RInt index) {
        return this;
    }

    @Override
    public RArray subset(RString names) {
        return this;
    }

    @Override
    public RInt asInt() {
        return RInt.EMPTY;
    }

    @Override
    public RLogical asLogical() {
        return RLogical.EMPTY;
    }

    @Override
    public RDouble asDouble() {
        return RDouble.EMPTY;
    }

    @Override
    public RString asString() {
        return RString.EMPTY;
    }

    @Override
    public RList asList() {
        return RList.EMPTY;
    }

    @Override
    public RArray materialize() {
        return this;
    }

    public static RNull getNull() {
        return instance;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public <T extends RNode> T callNodeFactory(OperationFactory<T> factory) {
        return factory.fromNull();
    }

    @Override
    public int[] dimensions() {
        return null;
    }

    @Override
    public RArray setDimensions(int[] dimensions) {
        Utils.nyi();
        return null;
    }

    @Override
    public int index(int i, int j) {
        Utils.nyi();
        return 0;
    }

    @Override
    public void ref() {
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public String prettyMatrixElement() {
        return pretty();
    }

    @Override
    public RNull stripAttributes() {
        return this;
    }

    @Override
    public Object getRef(int i) {
        return get(i);
    }

    @Override
    public RLogical asLogical(NAIntroduced naIntroduced) {
        Utils.nyi();
        return null;
    }

    @Override
    public RInt asInt(NAIntroduced naIntroduced) {
        Utils.nyi();
        return null;
    }

    @Override
    public RDouble asDouble(NAIntroduced naIntroduced) {
        Utils.nyi();
        return null;
    }

    @Override
    public RString asString(NAIntroduced naIntroduced) {
        Utils.nyi();
        return null;
    }
}
