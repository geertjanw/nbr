package org.netbeans.r.execution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openide.cookies.LineCookie;
import org.openide.loaders.DataObject;
import org.openide.text.Line;
import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;

/**
 *
 * @author constantin
 */
public class ROutputListener implements OutputListener {

    public static final String PATTERN_STRING = "\\s*line\\s*([0-9]+)\\s*column\\s*([0-9]+)\\s*-\\s*([^ :]+):\\s+(.+)";
    public static final Pattern PATTERN = Pattern.compile(PATTERN_STRING);
    DataObject dataObject;

    public ROutputListener(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    @Override
    public void outputLineSelected(OutputEvent ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void outputLineAction(OutputEvent ev) {
        String lineString = ev.getLine();
        Matcher matcher = PATTERN.matcher(lineString);
        if (matcher.matches()) {
            int lineNumber = Integer.parseInt(matcher.group(1));
            int columnNumber = Integer.parseInt(matcher.group(2));
            LineCookie lc =  dataObject.getLookup().lookup(LineCookie.class);
            Line l = lc.getLineSet().getOriginal(lineNumber - 1);
            l.show(Line.ShowOpenType.NONE, Line.ShowVisibilityType.FRONT, columnNumber - 1);
        }
    }

    @Override
    public void outputLineCleared(OutputEvent ev) {
        
    }

}
