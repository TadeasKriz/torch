package org.brightify.torch.action.save;

import org.brightify.torch.Key;
import org.brightify.torch.Torch;
import org.brightify.torch.util.async.AsyncRunner;
import org.brightify.torch.util.async.Callback;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

public class SaverImpl implements Saver, AsyncSaver {

    private final Torch torch;

    public SaverImpl(Torch torch) {
        this.torch = torch;
    }

    @Override
    public AsyncSaver async() {
        return this;
    }

    @Override
    public <ENTITY> Key<ENTITY> entity(ENTITY entity) {
        return entities(Collections.singleton(entity)).keySet().iterator().next();
    }

    @Override
    public <ENTITY> Map<Key<ENTITY>, ENTITY> entities(ENTITY... entities) {
        return entities(Arrays.asList(entities));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <ENTITY> Map<Key<ENTITY>, ENTITY> entities(Iterable<ENTITY> entities) {
        return torch.getFactory().getDatabaseEngine().save(entities);
    }

    @Override
    public <ENTITY> void entity(Callback<Key<ENTITY>> callback, final ENTITY entity) {
        AsyncRunner.submit(callback, new Callable<Key<ENTITY>>() {
            @Override
            public Key<ENTITY> call() throws Exception {
                return entity(entity);
            }
        });
    }

    @Override
    public <ENTITY> void entities(Callback<Map<Key<ENTITY>, ENTITY>> callback, final ENTITY... entities) {
        AsyncRunner.submit(callback, new Callable<Map<Key<ENTITY>,ENTITY>>() {
            @Override
            public Map<Key<ENTITY>, ENTITY> call() throws Exception {
                return entities(entities);
            }
        });
    }

    @Override
    public <ENTITY> void entities(Callback<Map<Key<ENTITY>, ENTITY>> callback, final Iterable<ENTITY> entities) {
        AsyncRunner.submit(callback, new Callable<Map<Key<ENTITY>, ENTITY>>() {
            @Override
            public Map<Key<ENTITY>, ENTITY> call() throws Exception {
                return entities(entities);
            }
        });
    }
}
