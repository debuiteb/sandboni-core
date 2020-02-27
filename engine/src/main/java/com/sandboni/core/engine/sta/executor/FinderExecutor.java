package com.sandboni.core.engine.sta.executor;

import com.sandboni.core.engine.contract.Finder;
import com.sandboni.core.engine.sta.Context;
import com.sandboni.core.engine.sta.graph.Link;

import java.util.function.Function;

public class FinderExecutor extends AbstractParallelExecutor<Finder, Void> {

    private final Context context;

    public FinderExecutor(Context context) {
        this.context = context;
    }

    @Override
    Function<Finder, Void> getMappingFunction() {
        return finder -> {
            Context localContext = context.getLocalContext();
            finder.findSafe(localContext);
            context.addLinks(localContext.getLinks().toArray(Link[]::new));
            return null;
        };
    }
}
