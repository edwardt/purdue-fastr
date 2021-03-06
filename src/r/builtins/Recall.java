package r.builtins;

import r.data.*;
import r.errors.*;
import r.nodes.ast.*;
import r.nodes.exec.*;
import r.nodes.exec.FunctionCall.DotsInfo;
import r.runtime.*;

final class Recall extends CallFactory {

    static final CallFactory _ = new Recall("Recall", new String[]{"..."}, new String[]{});

    private Recall(String name, String[] parameters, String[] required) {
        super(name, parameters, required);
    }

    @Override public RNode create(ASTNode call, RSymbol[] names, RNode[] exprs) {
        return new Uninitialized(call, names, exprs);
    }

    public static class Uninitialized extends AbstractCall {
        public Uninitialized(ASTNode orig, RSymbol[] argNames, RNode[] argExprs) {
            super(orig, argNames, argExprs);
        }

        @Override
        public final Object execute(Frame frame) {
            try {
                throw new SpecializationException(null);
            } catch (SpecializationException e) {
                if (frame == null) {
                    throw RError.getRecallCalledOutsideClosure(ast);
                }
                RFunction function = frame.function();
                if (function == null) {
                    throw RError.getRecallCalledOutsideClosure(ast);
                }
                Fixed fn = new Fixed(ast, argNames, argExprs, function);
                replace(fn, "install Fixed from Recall.Uninitialized");
                return fn.execute(frame);
            }
        }
    }

    // NOTE: this will only work when the Recall is NOT within eval/language object; for eval/language object will need another implementation
    // FIXME: could probably make this faster by subclassing AbstractCall directly
    public static class Fixed extends AbstractCall {
        final RFunction function;
        final DotsInfo functionDotsInfo = new DotsInfo();
        final int[] argPositions;
        final int nparams;
        final int dotsIndex;

        public Fixed(ASTNode orig, RSymbol[] argNames, RNode[] argExprs, RFunction function) {
            super(orig, argNames, argExprs);
            this.function = function;
            argPositions = computePositions(function, functionDotsInfo);
            nparams = function.nparams();
            dotsIndex = function.dotsIndex();
        }

        @Override
        public final Object execute(Frame frame) {
            // TODO: can we do something smarter here?
            Frame newFrame = function.createFrame(frame);
            placeArgs(frame, newFrame, argPositions, functionDotsInfo, dotsIndex);
            return function.call(newFrame);
        }
    }

}
