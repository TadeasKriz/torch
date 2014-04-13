package org.brightify.torch.action.load.async;

import org.brightify.torch.filter.Property;

/**
 * @author <a href="mailto:tadeas@brightify.org">Tadeas Kriz</a>
 */
public interface AsyncOrderLoader<ENTITY> {

    AsyncOrderDirectionLimitListLoader<ENTITY> orderBy(Property<?> property);

}
