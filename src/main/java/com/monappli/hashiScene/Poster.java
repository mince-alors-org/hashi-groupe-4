package com.monappli.hashiScene;

import com.monappli.handlers.*;

abstract interface Poster {
    abstract void paste(String name) throws Exception;
    abstract void pasteAndHandle(String name, Handler hand) throws Exception;
}
