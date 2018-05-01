package com.cecilsystems.mistersoftbackend.util;

import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import javafx.stage.Stage;

/**
 * @author Plamedi L. Lusembo
 */
public class FormMenu {

    private MenuEnum menum;
    private Stage stage;

    public FormMenu(MenuEnum menum, Stage stage) {
        this.menum = menum;
        this.stage = stage;
    }

    public MenuEnum getMenum() {
        return menum;
    }

    public void setMenum(MenuEnum menum) {
        this.menum = menum;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
