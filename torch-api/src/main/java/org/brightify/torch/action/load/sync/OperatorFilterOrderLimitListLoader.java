package org.brightify.torch.action.load.sync;

/**
 * @author <a href="mailto:tadeas@brightify.org">Tadeas Kriz</a>
 */
public interface OperatorFilterOrderLimitListLoader<ENTITY> extends OperatorFilterLoader<ENTITY>, OrderLoader<ENTITY>,
        LimitLoader<ENTITY>, ListLoader<ENTITY>, Countable {
}
