package com.cecilsoftwares.reussoftfrontend.essential;



/**
 * @author Plamedi L. Lusembo
 */
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JCustomTextField extends JTextField {

    protected int maxLength = -1;
    //default regex, accept everything
    protected String regexCheck = ".*";
    protected boolean isBlocked = false;

    public JCustomTextField() {
        super();
    }

    public JCustomTextField(int cols) {
        super(cols);
    }

    public JCustomTextField(int cols, int maxLength) {
        super(cols);
        setMaximumLength(maxLength);
    }

    @Override
    protected Document createDefaultModel() {
        return new RegexDocument();
    }

    public int getMaximumLength() {
        return maxLength;
    }

    public void setMaximumLength(int max) {
        maxLength = max;
    }

    public String getRegexFilter() {
        return String.valueOf(regexCheck);
    }

    public void setRegexFilter(String regex) {
        regexCheck = regex;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean bool) {
        isBlocked = bool;
    }

    private final class RegexDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            if (maxLength < 0) {
                if (str.matches(regexCheck)) {
                    if (!isBlocked) {
                        super.insertString(offs, str, a);
                    } else {
                        //send event
                        //Max limit not reached
                        //but invalid input
                    }
                }
            } else {
                if (offs < maxLength && offs >= 0
                        && str.matches(regexCheck)
                        && getLength() < maxLength) {
                    if (!isBlocked) {
                        super.insertString(offs, str, a);
                    } else {
                        //send event
                        //Max limit reached
                    }
                }
            }
        }
    }
}
