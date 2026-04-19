package dao;

import java.io.*;
import model.Bank;

public class FileAccountDAO implements AccountDAO {
    @Override
    public void save(Bank bank) throws IOException {
        File file = new File(bank.getFilepath());
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(bank);
        }
    }

    @Override
    public Bank load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Bank loaded = (Bank) ois.readObject();
            Bank.replaceInstance(loaded);
            try { loaded.reinitializeTransients(); } catch(Exception ex) { /* ignore */ }
            return loaded;
        }
    }
}
