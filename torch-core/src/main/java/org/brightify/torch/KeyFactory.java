package org.brightify.torch;

/**
 * @author <a href="mailto:tadeas@brightify.org">Tadeas Kriz</a>
 */
public class KeyFactory {

    /**
     * @param entityClass
     * @param id
     * @param <ENTITY>
     * @return
     */
    public static <ENTITY> Key<ENTITY> create(Class<ENTITY> entityClass, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot create Key with null ID!");
        }

        return new Key<ENTITY>(entityClass, id);
    }

    public static <ENTITY> Key<ENTITY> create(EntityDescription<ENTITY> description, ENTITY entity) {
        return create(description.getEntityClass(), description.getEntityId(entity));
    }

    @SuppressWarnings("unchecked")
    public static <ENTITY> Key<ENTITY> create(ENTITY entity) {
        Class<ENTITY> entityClass = (Class<ENTITY>) entity.getClass();
        EntityDescription<ENTITY> metadata = TorchService.factory().getEntities().getDescription(entityClass);
        return create(metadata, entity);
    }

}
