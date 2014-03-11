package org.brightify.torch.action.load.async;

/**
 * @author <a href="mailto:tadeas@brightify.org">Tadeas Kriz</a>
 */
public interface AsyncLimitLoader<ENTITY> {

    AsyncOffsetListLoader<ENTITY> limit(int limit);

}
