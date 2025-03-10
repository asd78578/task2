package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util util = new Util();
        util.getConnection();
        UserService userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль (User с именем — name добавлен в базу данных)
        userService.saveUser("Ivan1","Ivanov",(byte) 5);
        userService.saveUser("Ivan2","Ivanov",(byte) 6);
        userService.saveUser("Ivan3","Ivanov",(byte) 7);
        userService.saveUser("Ivan4 ","Ivanov",(byte) 8);

        //Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        userService.getAllUsers();

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}
