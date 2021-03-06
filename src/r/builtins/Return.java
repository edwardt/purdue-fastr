package r.builtins;

import r.*;
import r.data.*;
import r.nodes.ast.*;
import r.nodes.exec.*;
import r.runtime.*;

/**
 * "rep"
 *
 * <pre>
 * value -- An expression.
 * </pre>
 */
public final class Return extends CallFactory {

    static final CallFactory _ = new Return("return", new String[]{"value"}, new String[]{});

    Return(String name, String[] params, String[] required) {
        super(name, params, required);
    }

    public static final class ReturnException extends RuntimeException {
        public static ReturnException instance = new ReturnException();
        private static final long serialVersionUID = -9147675462255551205L;
    }

    @Override public RNode create(ASTNode call, RSymbol[] names, RNode[] exprs) {
        if (exprs.length == 0) { return new Builtin.Builtin0(call, names, exprs) {
            @Override public RAny doBuiltIn(Frame frame) {
                frame.returnValue(RNull.getNull());
                throw ReturnException.instance;
            }
        }; }
        if (exprs.length == 1) { return new Builtin.Builtin1(call, names, exprs) {
            @Override public RAny doBuiltIn(Frame frame, RAny param) {
                frame.returnValue(param);
                throw ReturnException.instance;
            }
        }; }
        throw Utils.nyi("unreachable");
    }
}
