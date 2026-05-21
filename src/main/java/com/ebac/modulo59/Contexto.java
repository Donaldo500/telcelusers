package com.ebac.modulo59;
import java.sql.Connection;
import java.sql.SQLException;
import com.ebac.modulo59.dto.Address;
import com.ebac.modulo59.dto.User;
import com.ebac.modulo59.dto.Phone;
import com.ebac.modulo59.model.UserModel;
import com.ebac.modulo59.model.AddressModel;
import com.ebac.modulo59.model.PhoneModel;

public class Contexto {
    static Connection connection;
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Telcel";
        String user = "root";
        String password = "root";

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection(url, user, password);

        operationWithUsers();
        operationWithAddresses();
        operationWithPhones();

        connection.close();
    }

    static void operationWithUsers() throws SQLException {
        System.out.println("------------------Operación con usuarios------------------");
        System.out.println("------------------SAVE------------------");
        User usuarioMaria = createUser("Maria", 25);
        User usuarioJuan = createUser("Juan", 30);

        UserModel userModel = new UserModel(connection);
        User maria = userModel.save(usuarioMaria);
        User juan = userModel.save(usuarioJuan);

        System.out.println(maria);
        System.out.println(juan);
        System.out.println("------------------------------------------------------------");
        
        System.out.println("------------------GET------------------");
        User usuario1EnDB = userModel.getById(1);
        User usuario2EnDB = userModel.getById(2);
        System.out.println(usuario1EnDB);
        System.out.println(usuario2EnDB);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------Update------------------");
        User newMaria = updateUser(usuario1EnDB, "Ana", "Maria", 27);
        User newJuan = updateUser(usuario2EnDB, "Luis", "Gonzalez", 32);
        User mariaActualizada = userModel.updateById(newMaria);
        User juanActualizado = userModel.updateById(newJuan);
        System.out.println(mariaActualizada);
        System.out.println(juanActualizado);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------DELETE------------------");
        /*userModel.deleteById(2);
        User usuario2Eliminado = userModel.getById(2);
        System.out.println(usuario2Eliminado);*/
        System.out.println("------------------------------------------------------------");
    }

    static void operationWithAddresses() throws SQLException {
        System.out.println("------------------Operación con direcciones------------------");
        System.out.println("------------------SAVE------------------");
        UserModel userModel = new UserModel(connection);
        AddressModel addressModel = new AddressModel(connection);
        User usuario1 = userModel.getById(1);

        Address address1 = createAddress(
                usuario1.getIdUser(),
                324,
                "Veracruz",
                "Calle 5"
        );

        Address address1Save = addressModel.save(address1);

        User usuario2 = userModel.getById(2);

        Address address2 = createAddress(
                usuario2.getIdUser(),
                32454,
                "Veracruz",
                "Calle 54"
        );
        
        Address address2Save = addressModel.save(address2);

        System.out.println(address1Save);
        System.out.println(address2Save);
        System.out.println("------------------------------------------------------------");
           
        System.out.println("------------------GET------------------");
        Address address1EnDB = addressModel.getById(address1.getIdUser());
        Address address2EnDB = addressModel.getById(address2.getIdUser());
        System.out.println(address1EnDB);
        System.out.println(address2EnDB);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------Update------------------");
        Address newAddress1 = updateAddress(address1EnDB, 123, "Estado de Mexico", "Calle 55");
        Address newAddress2 = updateAddress(address2EnDB, 543, "Ciudad de Mexico", "Calle 123");
        Address address1Actualizada = addressModel.updateById(newAddress1);
        Address address2Actualizada = addressModel.updateById(newAddress2);
        System.out.println(address1Actualizada);
        System.out.println(address2Actualizada);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------DELETE------------------");
        addressModel.deleteById(2);
        Address address2Eliminada = addressModel.getById(2);
        System.out.println(address2Eliminada);
        System.out.println("------------------------------------------------------------");
    }

    static void operationWithPhones() throws SQLException {
        System.out.println("------------------Operación con teléfonos------------------");
        System.out.println("------------------SAVE------------------");
        UserModel userModel = new UserModel(connection);
        PhoneModel phoneModel = new PhoneModel(connection);
        User usuario1 = userModel.getById(1);

        Phone phone1 = createPhone(
                usuario1.getIdUser(),
                "555-1234",
                "Celular"
        );

        Phone phone1Save = phoneModel.save(phone1);
        User usuario2 = userModel.getById(2);

        Phone phone2 = createPhone(
                usuario2.getIdUser(),
                "555-5678",
                "Fijo"
        );

        Phone phone2Save = phoneModel.save(phone2);
        System.out.println(phone1Save);
        System.out.println(phone2Save);
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------GET------------------");
        Phone phone1EnDB = phoneModel.getById(phone1.getIdUser());
        Phone phone2EnDB = phoneModel.getById(phone2.getIdUser());
        System.out.println(phone1EnDB);
        System.out.println(phone2EnDB);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------Update------------------");
        Phone newPhone1 = updatePhone(phone1EnDB, "555-4321", "Fijo");
        Phone newPhone2 = updatePhone(phone2EnDB, "555-8765", "Celular");
        Phone phone1Actualizada = phoneModel.updateById(newPhone1);
        Phone phone2Actualizada = phoneModel.updateById(newPhone2);
        System.out.println(phone1Actualizada);
        System.out.println(phone2Actualizada);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------DELETE------------------");
        phoneModel.deleteById(2);
        Phone phone2Eliminada = phoneModel.getById(2);
        System.out.println(phone2Eliminada);
        System.out.println("------------------------------------------------------------");
    }

    private static User createUser(String name,int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    private static User updateUser(User user, String newName, String newLastName, int newAge) {
        user.setName(newName);
        user.setLastName(newLastName);
        user.setAge(newAge);
        return user;
    }

    private static Phone createPhone(int idUser, String phoneNum, String type){
        Phone phone = new Phone();
        phone.setIdUser(idUser);
        phone.setPhone(phoneNum);
        phone.setType(type);
        return phone;
    }

    private static Phone updatePhone(Phone phone, String phoneNum, String type) {
        phone.setPhone(phoneNum);
        phone.setType(type);
        return phone;
    }

    private static Address createAddress(int idUser, int number, String state,String atreet){
        Address address = new Address();
        address.setIdUser(idUser);
        address.setNumber(number);
        address.setState(state);
        address.setStreet(atreet);
        return address;
    }

    private static Address updateAddress(Address address, int number, String state, String street) {
        address.setNumber(number);
        address.setState(state);
        address.setStreet(street);
        return address;
    }
}