package com.twu.biblioteca;

import com.twu.biblioteca.com.twu.biblioteca.util.Options;
import com.twu.biblioteca.com.twu.biblioteca.util.StringUtils;

/**
 * Created by xuzhang on 3/4/15.
 */
public class MainMenu {

    public void show() {
        System.out.println(StringUtils.MAIN_MENU_OPTION_CHOOSE_PROMPT);
        for(Options option: Options.values()) {
            if(option != Options.UNKNOW_OPTION) {
                System.out.println(option.showOptionMsg());
            }
        }
    }

    public void invalidOptionPromptMessage() {
        System.out.println(StringUtils.MAIN_MENU_INVALID_INPUT_PROMPT);
    }
}
