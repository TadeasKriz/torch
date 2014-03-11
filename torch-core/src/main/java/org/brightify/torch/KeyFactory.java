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

    public static <ENTITY> Key<ENTITY> create(ENTITY entity) {
        Class<ENTITY> entityClass = (Class<ENTITY>) entity.getClass();
        EntityMetadata<ENTITY> metadata = TorchService.factory().getEntities().getMetadata(entityClass);

        return metadata.createKey(entity);
    }

}
