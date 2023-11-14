package edu.hw6.DirectoryFilter;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public abstract class AbstractFilter implements DirectoryStream.Filter<Path> {

    protected AbstractFilter next;

    public AbstractFilter() {
        this.next = null;
    }

    public AbstractFilter and(AbstractFilter filter) {
        filter.next = this;
        return filter;
    }
}
