package com.example.ProjectSpring;

import java.io.*;
import java.nio.file.*;

public class IdGenerator {
    private static final String ID_FILE = "last_id.txt";

    public int getLastId() {
        try {
            String lastId = Files.readString(Paths.get(ID_FILE));
            return Integer.parseInt(lastId);
        } catch (IOException e) {
            return 0;
        }
    }

    public void saveLastId(int id) {
        try {
            Files.writeString(Paths.get(ID_FILE), String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int generateId() {
        int lastId = getLastId();
        int newId = lastId + 1;
        saveLastId(newId);
        return newId;
    }
}