package org.brightify.torch.filter;

import java.util.List;

/**
 * @author <a href="mailto:tadeas@brightify.org">Tadeas Kriz</a>
 */
public class EntityFilter {

    protected final EntityFilter mPreviousEntityFilter;
    protected final FilterType mFilterType;

    public EntityFilter(EntityFilter previousEntityFilter, FilterType filterType) {
        mPreviousEntityFilter = previousEntityFilter;
        mFilterType = filterType;
    }

    protected FilterType getFilterType() {
        return mFilterType;
    }

    public String toSQL(List<String> args) {
        StringBuilder builder = new StringBuilder();
        toSQL(args, builder);
        return builder.toString();
    }

    public void toSQL(List<String> args, StringBuilder builder) {
        if(mPreviousEntityFilter != null) {
            mPreviousEntityFilter.toSQL(args, builder);
        }
        builder.append(mFilterType.toSQL(args));
    }

    public EntityFilter or(String condition, Object... params) {
        EntityFilter orFilter = new EntityFilter(this, new OrFilterType());

        return new EntityFilter(orFilter, new ConditionFilterType().setCondition(condition).setArgs(params));
    }

    public EntityFilter or(EntityFilter nestedFilter) {
        EntityFilter orFilter = new EntityFilter(this, new OrFilterType());

        return new EntityFilter(orFilter, new NestedFilterType().setEntityFilter(nestedFilter));
    }

    public EntityFilter and(String condition, Object... params) {
        EntityFilter andFilter = new EntityFilter(this, new AndFilterType());

        return new EntityFilter(andFilter, new ConditionFilterType().setCondition(condition).setArgs(params));
    }

    public EntityFilter and(EntityFilter nestedFilter) {
        EntityFilter andFilter = new EntityFilter(this, new AndFilterType());

        return new EntityFilter(andFilter, new NestedFilterType().setEntityFilter(nestedFilter));
    }

    public static EntityFilter filter(String condition, Object... params) {
        return new EntityFilter(null, new ConditionFilterType().setCondition(condition).setArgs(params));
    }

    public static EntityFilter filter(EntityFilter nestedFilter) {
        return new EntityFilter(null, new NestedFilterType().setEntityFilter(nestedFilter));
    }

    public interface FilterType {
        String toSQL(List<String> args);
    }

    public static class OrFilterType implements FilterType {
        @Override
        public String toSQL(List<String> args) {
            return " OR ";
        }
    }

    public static class AndFilterType implements FilterType {
        @Override
        public String toSQL(List<String> args) {
            return " AND ";
        }
    }

    public static class ConditionFilterType implements FilterType {
        private String mCondition;
        private Object[] mArgs;

        public String getCondition() {
            return mCondition;
        }

        public ConditionFilterType setCondition(String condition) {
            mCondition = condition;
            return this;
        }

        public Object[] getArgs() {
            return mArgs;
        }

        public ConditionFilterType setArgs(Object... args) {
            mArgs = args;
            return this;
        }

        @Override
        public String toSQL(List<String> args) {
            for(Object arg : mArgs) {
                args.add(arg.toString());
            }
            return mCondition;
        }


    }

    public static class NestedFilterType implements FilterType {
        private EntityFilter mEntityFilter;

        @Override
        public String toSQL(List<String> args) {
            return "(" + mEntityFilter.toSQL(args) + ")";
        }

        public EntityFilter getEntityFilter() {
            return mEntityFilter;
        }

        public NestedFilterType setEntityFilter(EntityFilter entityFilter) {
            mEntityFilter = entityFilter;
            return this;
        }
    }
}
