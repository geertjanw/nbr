package org.netbeans.lexer;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.Interval;
import org.netbeans.spi.lexer.LexerInput;

public class AntlrCharStream implements CharStream {

    private class CharStreamState {

        int index;
        int line;
        int charPositionInLine;
    }

    private int line = 1;
    private int charPositionInLine = 0;
    private LexerInput input;
    private String name;
    private int index = 0;
    private List<CharStreamState> markers;
    private int markDepth = 0;
    private int lastMarker;
    private boolean ignoreCase = false;
    
    public AntlrCharStream(LexerInput input, String name) {
        this.input = input;
        this.name = name;
    }

    public AntlrCharStream(LexerInput input, String name, boolean ignoreCase) {
        this.input = input;
        this.name = name;
        this.ignoreCase  = ignoreCase; 
    }
    
    public String substring(int start, int stop) {
        return input.readText(start, stop).toString();
    }
    
    @Override
    public String getText(Interval intrvl) {
      return null; 
    }

    public int LT(int i) {
        return LA(i);
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setCharPositionInLine(int pos) {
        this.charPositionInLine = pos;
    }

    public int getCharPositionInLine() {
        return charPositionInLine;
    }

    @Override
    public void consume() {
        int c = input.read();
        index++;
        charPositionInLine++;

        if (c == '\n') {
            line++;
            charPositionInLine = 0;
        }
    }

    @Override
    public int LA(int i) {
        if (i == 0) {
            return 0; // undefined
        }

        int c = 0;
        for (int j = 0; j < i; j++) {
            c = read();
        }
        backup(i);
        return c;
    }

    @Override
    public int mark() {
        if (markers == null) {
            markers = new ArrayList<CharStreamState>();
            markers.add(null); // depth 0 means no backtracking, leave blank
        }
        markDepth++;
        CharStreamState state = null;
        if (markDepth >= markers.size()) {
            state = new CharStreamState();
            markers.add(state);
        } else {
            state = markers.get(markDepth);
        }
        state.index = index;
        state.line = line;
        state.charPositionInLine = charPositionInLine;
        lastMarker = markDepth;

        return markDepth;
    }

    @Override
    public void release(int i) {
        markDepth = i;
        markDepth--;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public void seek(int i) {
        if (index < this.index) {
            backup(this.index - index);
            this.index = index; // just jump; don't update stream state (line, ...)
            return;
        }

        // seek forward, consume until p hits index
        while (this.index < index) {
            consume();
        }
    }

    @Override
    public int size() {
        return -1;
    }

    @Override
    public String getSourceName() {
        return name;
    }

    private int read() {
        int result = input.read();
        if (result == LexerInput.EOF) {
            result = CharStream.EOF;
        }

        return result;
    }

    private void backup(int count) {
        input.backup(count);
    }

}
