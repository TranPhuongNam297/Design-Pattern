package com.example.app_cnpmnc_da_hethongatm.Observer;

public class Invoker {
    private Command command;
    public Invoker(Command command) {
        this.command = command;
    }
    public void execute() {
        if (command != null) {
            command.execute();
        }
    }
}

