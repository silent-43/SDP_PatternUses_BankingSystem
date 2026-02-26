package dao;

import model.Bank;

public interface AccountDAO {
    void save(Bank bank) throws Exception;
    Bank load(String path) throws Exception;
}
