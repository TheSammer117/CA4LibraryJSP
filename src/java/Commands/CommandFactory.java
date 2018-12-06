/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import com.sun.xml.registry.common.tools.bindings_v3.Command;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 *
 * @author Haiyun Yu d00188956
 */
public class CommandFactory {

    public static Command createCommand(String action) {
        Command command = null;

        if (action != null) {
            switch (action) {
                case "login":
                    command = new LogginCommand();
                    break;
                default:
                    command = new NoValidActionCommand();
                    break;
            }
        } else {
            command = new NoActionSuppliedCommand();
        }
        return command;
    }
}
